package basic.eventTest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private TextField tfMsg;

    @FXML
    private TextArea txtArea;

    // Fxml문서에서 설정한 이벤트처리할 메서드명 --> 첫번째 버튼을 눌렀을 때
    @FXML
    void btn1Click(ActionEvent event) {
    	String temp = tfMsg.getText();
    	txtArea.appendText("첫번째 버튼 처리 : " + temp + "\n");
    }
    @FXML
    void initialize() { // Fxml문서가 load된 후  제일 먼저 실행되는 메서드이다
    	
    	// 이곳은 초기화 작업이 필요할 때 기술하는 영역으로 이벤트 설정을 자바 코드로 할 경우 이곳에 기술한다.
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'FxmlEvent.fxml'.";
        assert btn2 != null : "fx:id=\"btn2\" was not injected: check your FXML file 'FxmlEvent.fxml'.";
        assert tfMsg != null : "fx:id=\"tfMsg\" was not injected: check your FXML file 'FxmlEvent.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'FxmlEvent.fxml'.";
        
        // 자바코드로 이벤트 설정하기
        btn2.setOnAction(e -> {
        	String temp = tfMsg.getText();
        	txtArea.appendText("두번째 버튼 처리 ===> " + temp + "\n");
        });
    }
}
