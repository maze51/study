package basic.childWinTest;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SMChildController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameText;

    @FXML
    private TextField korText;

    @FXML
    private TextField mathText;

    @FXML
    private TextField engText;

    @FXML
    private Button saveButton;

    @FXML
    private Button CancelButton;

    @FXML
    void cancel(ActionEvent event) {
    	SMMainController.childStage.close();
    }

    @FXML
    void save(ActionEvent event) {
    	ObservableList<Score> list =SMMainController.getDataList();
    	
    	String name = nameText.getText();
    	int korS = Integer.parseInt(korText.getText());
    	int mathS = Integer.parseInt(mathText.getText());
    	int engS = Integer.parseInt(engText.getText());
    	
    	Score s = new Score(name, korS, mathS, engS);
    	list.add(s);
    	SMMainController.childStage.close();
    	
    }

    @FXML
    void initialize() {
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'SMChild.fxml'.";
        assert korText != null : "fx:id=\"korText\" was not injected: check your FXML file 'SMChild.fxml'.";
        assert mathText != null : "fx:id=\"mathText\" was not injected: check your FXML file 'SMChild.fxml'.";
        assert engText != null : "fx:id=\"engText\" was not injected: check your FXML file 'SMChild.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'SMChild.fxml'.";
        assert CancelButton != null : "fx:id=\"CancelButton\" was not injected: check your FXML file 'SMChild.fxml'.";

    }
}
