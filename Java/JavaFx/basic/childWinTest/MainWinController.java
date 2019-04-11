package basic.childWinTest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

public class MainWinController {
	private Stage mainStage;

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<Sample> table;
    
    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> addrCol;

    @FXML
    private Button btnAdd;
    
    // MainWin창의 '데이터추가'버튼 클릭 이벤트 처리
    @FXML
    void btnAddClicked(ActionEvent event) {
    	try {
    		Stage childStage = new Stage(StageStyle.DECORATED);
//    		childStage.initModality(Modality.APPLICATION_MODAL);
    		childStage.initModality(Modality.WINDOW_MODAL);
    		childStage.initOwner(mainStage);
    		
    		Parent childRoot = FXMLLoader.load(getClass().getResource("ChildWin.fxml"));
    		Scene scene = new Scene(childRoot);
    		
    		childStage.setTitle("데이터 추가");
    		childStage.setScene(scene);
    		childStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private ObservableList<Sample> dataList;
    private List<Sample> aa = new ArrayList<Sample>();
    
    @FXML
    void initialize() {
    	assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'MainWin.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'MainWin.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'MainWin.fxml'.";
        assert addrCol != null : "fx:id=\"addrCol\" was not injected: check your FXML file 'MainWin.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'MainWin.fxml'.";
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));
        
        dataList = FXCollections.observableArrayList();
        
        table.setItems(dataList);
    }
}
