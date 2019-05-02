package zipSearch.client;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.AlertUtil;
import zipSearch.service.IZipSearchService;
import zipSearch.vo.ZipSearchVO;

public class ZipSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cBox;

    @FXML
    private TextField searchText;

    @FXML
    private Button searchB;

    @FXML
    private TableView<ZipSearchVO> table;

    @FXML
    private TableColumn<?, ?> zipcode;

    @FXML
    private TableColumn<?, ?> sido;

    @FXML
    private TableColumn<?, ?> gugun;

    @FXML
    private TableColumn<?, ?> dong;

    @FXML
    private TableColumn<?, ?> bunji;
    
    // 검색
    @FXML
    void search(ActionEvent event) {
    	//String selectCategory = cBox.getSelectionModel().getSelectedItem();
    	String selectCategory = cBox.getValue();
    	
    	if (selectCategory == null) {
			
		} else if (selectCategory.equals("동이름")) { // 항목값을 해당 컬럼명으로 변환한다
			selectCategory = "dong";
		} else if (selectCategory.equals("우편번호")) {
			selectCategory = "zipcode";
		} 
    	
    	String keyword = searchText.getText();
    	
    	if(keyword.isEmpty() || selectCategory == null) {
    		AlertUtil.warning("주의", "검색 오류", "검색할 항목을 선택하고 검색어를 입력해 주세요");
    		return;
    	} else {
    		try {
				ArrayList<ZipSearchVO> boardList = (ArrayList<ZipSearchVO>) 
						service.searchZip(selectCategory, keyword);
				data = FXCollections.observableArrayList(boardList);
				table.setItems(data);
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    }
    
    private IZipSearchService service;
    private ObservableList<ZipSearchVO> data;
    
    @FXML
    void initialize() {
    	
    	try {
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			service = (IZipSearchService) reg.lookup("zipService");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
    	sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
    	gugun.setCellValueFactory(new PropertyValueFactory<>("gugun"));
    	dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
    	bunji.setCellValueFactory(new PropertyValueFactory<>("bunji"));
    	
    	cBox.getItems().addAll("동이름", "우편번호");
    	
        assert cBox != null : "fx:id=\"cBox\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert searchB != null : "fx:id=\"searchB\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert zipcode != null : "fx:id=\"zipcode\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert sido != null : "fx:id=\"sido\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert gugun != null : "fx:id=\"gugun\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert dong != null : "fx:id=\"dong\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert bunji != null : "fx:id=\"bunji\" was not injected: check your FXML file 'ZipSearch.fxml'.";

    }
}
