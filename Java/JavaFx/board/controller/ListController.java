package board.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import board.service.IJdbcBoardService;
import board.service.JdbcBoardServiceImpl;
import board.vo.JdbcBoardVO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newP;

    @FXML
    private ComboBox<String> searchOption;

    @FXML
    private TextField searchKeyword;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<JdbcBoardVO> table;
    
    @FXML
    private TableColumn<JdbcBoardVO, Integer> boardNo;

    @FXML
    private TableColumn<JdbcBoardVO, String> boardTitle;

    @FXML
    private TableColumn<JdbcBoardVO, String> boardWriter;

    @FXML
    private TableColumn<JdbcBoardVO, String> boardDate;

    @FXML
    private TableColumn<JdbcBoardVO, Integer> boardCnt;
    
    @FXML
    private Pagination pagination;
    
    static JdbcBoardVO selectItem; // 테이블에서 현재 선택한 게시글 정보를 저장
    
    static public JdbcBoardVO getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(JdbcBoardVO selectItem) {
		this.selectItem = selectItem;
	}

	// 새 게시물 작성
    @FXML
    void newPost(ActionEvent event) {
    	
    	try {
    		Parent newPost = FXMLLoader.load(getClass().getResource("NewPost.fxml"));
    		Scene scene = new Scene(newPost);
    		Stage newPostStage = (Stage) newP.getScene().getWindow(); // 현재 윈도우 가져오기
    		newPostStage.setScene(scene);
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}

    }

    //Pagination의 on key pressed???
    @FXML
    void pageClick(KeyEvent event) {
    	
    }
    
    // 게시물 검색
    @FXML
    void search(ActionEvent event) {
    	String selectCategory = searchOption.getSelectionModel().getSelectedItem(); // 콤보박스에 현재 선택된 항목값을 가져온다
    	if(selectCategory.equals("이름")) { // 항목값을 해당 컬럼명으로 변환한다
    		selectCategory = "board_writer";
    	} else if(selectCategory.equals("제목")) {
    		selectCategory = "board_title";
    	} else {
    		selectCategory = "board_content";
    	}
    	
    	String keyword = searchKeyword.getText();
    	
    	if(keyword=="") { // 검색 내용을 입력하지 않고 검색버튼을 누르면 전체 데이터가 나오도록 한다
    		ArrayList<JdbcBoardVO> boardList = (ArrayList<JdbcBoardVO>)service.getAllBoardList();
    		data = FXCollections.observableArrayList(boardList);
        	table.setItems(data);
    	} else {
    		ArrayList<JdbcBoardVO> boardList = (ArrayList<JdbcBoardVO>)service.getSearchBoardList(selectCategory, keyword);
    		data = FXCollections.observableArrayList(boardList);
        	table.setItems(data);
    	}
    }
    
    // 게시물 내용 확인
    @FXML
    void showPost(MouseEvent event) {
    	selectItem = table.getSelectionModel().getSelectedItem();
    	try {
    		Parent showPost = FXMLLoader.load(getClass().getResource("ShowPost.fxml"));
    		Scene scene = new Scene(showPost);
    		Stage showPostStage = (Stage) newP.getScene().getWindow(); // 현재 윈도우 가져오기
    		showPostStage.setScene(scene);
    		
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    
    private IJdbcBoardService service;
    private ObservableList<JdbcBoardVO> data;
    private int rowsPerPage = 15; // 한 화면에 보여줄 데이터(레코드) 수
    
    public void changeTableView(int index) {
		int fromIndex = index * rowsPerPage; // 시작위치
		int toIndex = Math.min(fromIndex + rowsPerPage, data.size()); // 종료위치
		
		table.setItems(FXCollections.observableArrayList(
				data.subList(fromIndex, toIndex)
		));
	}
    
    @FXML
    void initialize() {
    	
    	service = JdbcBoardServiceImpl.getInstance();
    	
    	boardNo.setCellValueFactory(new PropertyValueFactory<>("board_no"));
    	boardTitle.setCellValueFactory(new PropertyValueFactory<>("board_title"));
    	boardWriter.setCellValueFactory(new PropertyValueFactory<>("board_writer"));
    	boardDate.setCellValueFactory(new PropertyValueFactory<>("board_date"));
    	boardCnt.setCellValueFactory(new PropertyValueFactory<>("board_cnt"));
    	
    	ArrayList<JdbcBoardVO> boardList = (ArrayList<JdbcBoardVO>) service.getAllBoardList();
    	
    	data = FXCollections.observableArrayList(boardList);
    	
    	table.setItems(data);
    	
    	// 콤보박스 초기화
    	searchOption.getItems().addAll("이름", "제목", "내용");
    	
        assert newP != null : "fx:id=\"newP\" was not injected: check your FXML file 'List.fxml'.";
        assert searchOption != null : "fx:id=\"searchOption\" was not injected: check your FXML file 'List.fxml'.";
        assert searchKeyword != null : "fx:id=\"searchKeyword\" was not injected: check your FXML file 'List.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'List.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'List.fxml'.";
        assert boardNo != null : "fx:id=\"boardNo\" was not injected: check your FXML file 'List.fxml'.";
        assert boardTitle != null : "fx:id=\"boardTitle\" was not injected: check your FXML file 'List.fxml'.";
        assert boardWriter != null : "fx:id=\"boardWriter\" was not injected: check your FXML file 'List.fxml'.";
        assert boardDate != null : "fx:id=\"boardDate\" was not injected: check your FXML file 'List.fxml'.";
        assert boardCnt != null : "fx:id=\"boardCnt\" was not injected: check your FXML file 'List.fxml'.";
        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'List.fxml'.";
        
        pagination.setPageCount((int)Math.ceil( (double)data.size()/rowsPerPage) ); // 전체 페이지 수 설정
        pagination.setCurrentPageIndex(0); // 처음 선택될 페이지의 index값
		pagination.setMaxPageIndicatorCount(10); // 한 화면에 보여줄 Pagination의 페이지 수. 기본값은 10, 보통 쓰는 것도 10
		changeTableView(0); // 1페이지(0번index) 데이터를 TableView에 세팅
		
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				changeTableView(newValue.intValue()); // 선택한 페이지의 index값을 인수값으로 changeTableView()메서드 호출
			}
		});
		
    }
}
