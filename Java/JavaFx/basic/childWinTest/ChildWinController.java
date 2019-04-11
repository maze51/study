package basic.childWinTest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChildWinController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAddr;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    @FXML
    void btnAddClicked(ActionEvent event) {

    }

    @FXML
    void btnCancelClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert tfId != null : "fx:id=\"tfId\" was not injected: check your FXML file 'ChildWin.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'ChildWin.fxml'.";
        assert tfAddr != null : "fx:id=\"tfAddr\" was not injected: check your FXML file 'ChildWin.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'ChildWin.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'ChildWin.fxml'.";

    }
}
