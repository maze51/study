package basic.controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;

public class GugudanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> combo;

    @FXML
    private Button confirm;

    @FXML
    private TextArea taResult;

    @FXML
    void showResult(ActionEvent event) {
    	
    	if(combo.getValue()!=null) {
    		int dan = combo.getValue();
	//    	int dan = combo.getSelectionModel().getSelectedItem();
    		
    		taResult.setText("*** " + dan + "단 *** \n");
        	for (int i = 1; i < 10; i++) {
        		taResult.appendText(dan + " * " + i + " = " + (dan*i) + "\n");
    		}
    	}
    }

    @FXML
    void initialize() {
        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'Gugudan.fxml'.";
        assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'Gugudan.fxml'.";
        assert taResult != null : "fx:id=\"taResult\" was not injected: check your FXML file 'Gugudan.fxml'.";
        
        
    	combo.getItems().addAll(1,2,3,4,5,6,7,8,9);
		
        // 화면에 나타나는 셀의 내용을 변경하는 부분
        combo.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
			
			@Override
			public ListCell<Integer> call(ListView<Integer> param) {
				return new ListCell<Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						} else {
							setText(item + "단");
						}
					}
				};
			}
		});
        
        // 선택된 내용이 버튼 영역에 나타날 때, 값을 변경해 주는 부분
        combo.setButtonCell(new ListCell<Integer>() {
        	@Override
        	protected void updateItem(Integer item, boolean empty) {
        		super.updateItem(item, empty);
        		if(empty) {
					setText(null);
				} else {
					setText(item + "단");
				}
        	}
        });
    } // end of initialize
}
