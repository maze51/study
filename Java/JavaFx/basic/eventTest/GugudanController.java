package basic.eventTest;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GugudanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputDan;

    @FXML
    private Button btn;

    @FXML
    private TextArea result;

    @FXML
    void btn1Click(ActionEvent event) {
    	String txtDan = inputDan.getText(); // 단 값 읽기
    	
    	if(txtDan.isEmpty()){ // 값이 없는지 검사
    		result.setText("출력할 단을 입력하세요.");
    		inputDan.requestFocus(); // 단을 입력할 TextField에 포커스 추가
    		return;
    	}
    	
    	// 입력한 값이 정수인지 여부 검사 (정규식으로 검사)
    	if(!Pattern.matches("^[0-9]+$", txtDan)){
    		result.setText("정수가 아닙니다. 다시 입력하세요");
    		//inputDan.setText(""); // 내용 지우는 방법 1
    		inputDan.clear(); // 내용 지우는 방법 2
    		inputDan.requestFocus();
    		return;
    	}
    	int dan = Integer.parseInt(txtDan);
    	result.setText("---" + dan + "단---\n");
    	for (int i = 1; i < 10; i++) {
			result.appendText( dan + " * " + i + " = " + (dan*i) + "\n");
		}
    }

    @FXML
    void initialize() {
        assert inputDan != null : "fx:id=\"inputDan\" was not injected: check your FXML file 'GuguEvent.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'GuguEvent.fxml'.";
        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'GuguEvent.fxml'.";

    }
}
