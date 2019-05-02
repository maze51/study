package prodInfo.client;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import prodInfo.service.IProdService;
import prodInfo.vo.ProdVO;

public class ProdInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> selectlprod;

    @FXML
    private ComboBox<String> selectprod;

    @FXML
    private TableView<ProdVO> table;

    @FXML
    private TableColumn<?, ?> prod_id;

    @FXML
    private TableColumn<?, ?> prod_name;

    @FXML
    private TableColumn<?, ?> prod_lgu;

    @FXML
    private TableColumn<?, ?> prod_buyer;

    @FXML
    private TableColumn<?, ?> prod_cost;

    @FXML
    private TableColumn<?, ?> prod_price;

    @FXML
    private TableColumn<?, ?> prod_sale;

    @FXML
    private TableColumn<?, ?> prod_outline;

    @FXML
    private TableColumn<?, ?> prod_detail;

    private IProdService service;
    
    private List<String> lList = new ArrayList<String>();
    private List<String> pList = new ArrayList<String>();
    private List<ProdVO> prodvoList = new ArrayList<ProdVO>();
    private ObservableList<ProdVO> oList;
    @FXML
    void initialize() {
        assert selectlprod != null : "fx:id=\"selectlprod\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert selectprod != null : "fx:id=\"selectprod\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_id != null : "fx:id=\"prod_id\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_name != null : "fx:id=\"prod_name\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_lgu != null : "fx:id=\"prod_lgu\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_buyer != null : "fx:id=\"prod_buyer\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_cost != null : "fx:id=\"prod_cost\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_price != null : "fx:id=\"prod_price\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_sale != null : "fx:id=\"prod_sale\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_outline != null : "fx:id=\"prod_outline\" was not injected: check your FXML file 'ProdInfo.fxml'.";
        assert prod_detail != null : "fx:id=\"prod_detail\" was not injected: check your FXML file 'ProdInfo.fxml'.";

        // 컬럼 설정
        prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
        prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
        prod_lgu.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
        prod_buyer.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
        prod_cost.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
        prod_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
        prod_sale.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
        prod_outline.setCellValueFactory(new PropertyValueFactory<>("prod_outline"));
        prod_detail.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));
        
     // service객체 구하기
        try {
			Registry reg = LocateRegistry.getRegistry("localhost", 9999);
			service = (IProdService) reg.lookup("prodInfoService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
        
        try {
			lList =service.viewLprod();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        
        for (int i = 0; i < lList.size(); i++) {
    		selectlprod.getItems().addAll(lList.get(i));
		}
        
        selectlprod.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, 
					String oldValue, String newValue) {
				
				selectprod.getItems().clear(); // lprod가 바뀔 때 마다 prod 콤보박스 내용을 초기화
				
				String lprodnm = selectlprod.getValue();
				try {
					pList = service.searchProd(lprodnm);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < pList.size(); i++) {
					selectprod.getItems().addAll(pList.get(i)); // 초기화는 setAll()로 할 수도 있다
				}
			}
		});
        
        selectprod.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, 
					String oldValue, String newValue) {
				
				String prodnm = selectprod.getSelectionModel().getSelectedItem();
				try {
					prodvoList = service.viewprod(prodnm);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				oList = FXCollections.observableArrayList(prodvoList);
				
				table.setItems(oList);
				
			}
		});
        
        
    }
    // alert창
    public void alert(String msg) {
    	Alert alertWarning = new Alert(AlertType.WARNING);
    	alertWarning.setTitle("경고");
    	alertWarning.setContentText(msg);
    	alertWarning.showAndWait();
    }
}
