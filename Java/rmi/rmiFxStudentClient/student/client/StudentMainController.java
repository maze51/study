package student.client;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import student.service.IStudentService;
import student.vo.StudentVO;

public class StudentMainController {
	private Stage primaryStage; // 부모 스테이지를 가져오기 위해 생성
	
    public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentVO> stdTable;

    @FXML
    private TableColumn<?, ?> stdNameCol;

    @FXML
    private TableColumn<?, ?> stdKorCol;

    @FXML
    private TableColumn<?, ?> stdEngCol;

    @FXML
    private TableColumn<?, ?> stdMatCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnViewBarChart;
    
    // 추가버튼 클릭 이벤트
    @FXML
    void btnAddClick(ActionEvent event) {
    	try {
			Stage addStage = new Stage(StageStyle.UTILITY);
			addStage.initModality(Modality.WINDOW_MODAL);
			addStage.initOwner(primaryStage); // 부모 스테이지가 들어가야 한다
			addStage.setTitle("자료추가");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/StudentAddForm.fxml"));
			Parent addRoot = loader.load();
			
			// StudentAddFormController 객체에 현재 객체(StudentMainController==>this)를 넘겨준다.
			StudentAddFormController addController = loader.getController();
			addController.setStdMaincomtroller(this);
			
			Scene scene = new Scene(addRoot);
			addStage.setScene(scene);
			addStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    // 학생별 막대그래프 버튼 클릭 이벤트
    @FXML
    void btnViewBarChartClick(ActionEvent event) {
    	Stage barChartStage = new Stage(StageStyle.UTILITY);
    	barChartStage.initModality(Modality.WINDOW_MODAL);
    	barChartStage.initOwner(primaryStage);
    	barChartStage.setTitle("막대 그래프");
    	
    	BarChartForm barForm = new BarChartForm(stdList); // barForm은 Pane이라는 컨테이너
    	
    	Scene scene = new Scene(barForm, 600, 500);
    	barChartStage.setScene(scene);
    	barChartStage.show();
    	
    }
    
    private ObservableList<StudentVO> stdList;
    private List<StudentVO> dbStdList;
    private IStudentService service;
    
    
    @FXML
    void initialize() {
        assert stdTable != null : "fx:id=\"stdTable\" was not injected: check your FXML file 'StudentMain.fxml'.";
        assert stdNameCol != null : "fx:id=\"stdNameCol\" was not injected: check your FXML file 'StudentMain.fxml'.";
        assert stdKorCol != null : "fx:id=\"stdKorCol\" was not injected: check your FXML file 'StudentMain.fxml'.";
        assert stdEngCol != null : "fx:id=\"stdEngCol\" was not injected: check your FXML file 'StudentMain.fxml'.";
        assert stdMatCol != null : "fx:id=\"stdMatCol\" was not injected: check your FXML file 'StudentMain.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'StudentMain.fxml'.";
        assert btnViewBarChart != null : "fx:id=\"btnViewBarChart\" was not injected: check your FXML file 'StudentMain.fxml'.";
        
        // Service객체 생성
        // RMI에 등록된 MemberService객체 가져오기
        Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost", 7777);
			service = (IStudentService) reg.lookup("studentService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
        
        // TableView의 각 컬럼과 VO의 멤버변수 매칭하기
        stdNameCol.setCellValueFactory(new PropertyValueFactory<>("std_name"));
        stdKorCol.setCellValueFactory(new PropertyValueFactory<>("std_kor"));
        stdEngCol.setCellValueFactory(new PropertyValueFactory<>("std_eng"));
        stdMatCol.setCellValueFactory(new PropertyValueFactory<>("std_mat"));
        
        setStudentData();
        
        // TableView를 클릭했을 때 이벤트 처리
        stdTable.setOnMouseClicked(ee->{
        	StudentVO stdVo = stdTable.getSelectionModel().getSelectedItem();
        	if(stdVo!=null) {
        		try {
					Stage chartForm = new Stage(StageStyle.UTILITY);
					chartForm.initModality(Modality.WINDOW_MODAL);
					chartForm.initOwner(primaryStage);
					chartForm.setTitle("원형 그래프");
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SudentPieChartForm.fxml"));
					Parent chartRoot = loader.load(); // 이 때 해당 Controller객체가 생성된다.
					
					// StudentPieChartFormController객체 구하기
					SudentPieChartFormController chartController = loader.getController();
					chartController.setStdVo(stdVo); // 선택한 데이터를 넘겨준다
					chartController.showChart(); // 넘겨준 데이터를 이용하여 Chart를 그려주는 메서드를 호출한다.
					
					Scene scene = new Scene(chartRoot);
					chartForm.setScene(scene);
					chartForm.show();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	
        });

    }
    
    public void setStudentData() { // 데이터 추가 후 테이블에 데이터를 다시 세팅할 때, 
    								// 반복작업이므로 initialize블럭에서 별도 메서드로 뺐다.
    	// DB에서 전체 데이터 가져오기 ==> TableView에 가져온 데이터 세팅하기
        try {
			dbStdList = service.getAllStudent();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        stdList = FXCollections.observableArrayList(dbStdList);
        
        stdTable.setItems(stdList);
    }
}
