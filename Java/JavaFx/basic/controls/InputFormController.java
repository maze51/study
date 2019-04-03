package basic.controls;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import util.AlertUtil;
import javafx.scene.control.Alert.AlertType;

public class InputFormController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField nameField;

	@FXML
	private RadioButton maleB;

	@FXML
	private ToggleGroup gr;

	@FXML
	private RadioButton femaleB;

	@FXML
	private CheckBox travel;

	@FXML
	private CheckBox jk;

	@FXML
	private CheckBox baduk;

	@FXML
	private CheckBox read;

	@FXML
	private CheckBox movie;

	@FXML
	private CheckBox climb;

	@FXML
	private Button btn;

	@FXML
	private TextArea tArea;

	@FXML
	void btnClick(ActionEvent event) {

		String name = nameField.getText();
		if(name.isEmpty()) {
//			tArea.setText("이름을 입력하세요");
			AlertUtil.warning("<<경고창>>", "<<입력값 누락>>", "이름을 입력하세요");
			
			/*
			Alert warning = new Alert(AlertType.WARNING);
			warning.setTitle("경고창");
			warning.setHeaderText("입력값 누락");
			warning.setContentText("이름을 입력하세요");
			warning.showAndWait();
			*/
			nameField.requestFocus();
			return;
		}
		
		tArea.setText(name + "씨! \n");
		
//		String sung = "";
		if(maleB.isSelected()) {
//			sung = "남자";
			tArea.appendText("당신은 " + maleB.getText() + "자이고 \n");
		} else if(femaleB.isSelected()) {
//			sung = "여자";
			tArea.appendText("당신은 " + femaleB.getText() + "자이고 \n");
		} else {
			tArea.appendText("성별이 선택되지 않음\n");
		}
//		tArea.setText(sung);
		
		String hobbyList = "";
		CheckBox[] hobby = new CheckBox[] {travel, read, baduk, jk, movie, climb};
		for (int i = 0; i < hobby.length; i++) {
			if(hobby[i].isSelected()) {
				if(!hobbyList.equals("")) {
					hobbyList += ", ";
				}
				hobbyList += hobby[i].getText();
			}
		}
		
		if(hobbyList.equals("")) { // 삼항연산자로 간단하게 가능
			hobbyList = "없습니다";
		} else {
			hobbyList += "입니다";
		}
		
		tArea.appendText("취미는 " + hobbyList);
	} // end of btnClick
	
//	CheckBox[] checks;
	
	@FXML
	void initialize() {
		assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert maleB != null : "fx:id=\"maleB\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert gr != null : "fx:id=\"gr\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert femaleB != null : "fx:id=\"femaleB\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert travel != null : "fx:id=\"travel\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert jk != null : "fx:id=\"jk\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert baduk != null : "fx:id=\"baduk\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert read != null : "fx:id=\"read\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert movie != null : "fx:id=\"movie\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert climb != null : "fx:id=\"climb\" was not injected: check your FXML file 'InputForm.fxml'.";
		assert tArea != null : "fx:id=\"tArea\" was not injected: check your FXML file 'InputForm.fxml'.";
		
//		checks = new CheckBox(체크박스1, 2, 3,...);
	}
}
