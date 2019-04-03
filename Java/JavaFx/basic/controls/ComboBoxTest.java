package basic.controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		HBox hbox = new HBox();
		TextArea taResult = new TextArea();
		// 방법2 (ListView도 이렇게 두 방법이 있다)
				ObservableList<String> fruitList = FXCollections.observableArrayList(
					"사과","배","딸기","포도","감"
				);
				ComboBox<String> combo2 = new ComboBox<String>(fruitList);
				combo2.setValue(combo2.getItems().get(0));
		
		// 콤보박스에 들어갈 데이터를 세팅하는 방법1
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().addAll("한강","금강","영산강","낙동강");
		combo.setValue("금강"); // 초기에 선택된 값으로 보여줄 데이터 설정
		
		// 콤보박스에서 선택된 값이 변경될 때의 처리 ==> change이벤트 처리
		combo.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, 
					String oldValue, String newValue) {
				//taResult.setText("선택된 강 이름: " + newValue);
				int selIndex = -1;
				for(int i=0 ; i<combo.getItems().size() ; i++) {
					if(combo.getItems().get(i).equals(newValue)) {
						selIndex = i;
						break;
					}
				}
				
				combo2.setValue(combo2.getItems().get(selIndex));
			}
		});
		
		Button btn = new Button("확인");
		
		btn.setOnAction(e->{
			if(combo.getValue()!=null && combo2.getValue()!=null) {
				taResult.setText(combo.getValue() + "지역의 과일은 " + combo2.getValue() + "가 유명합니다.");
			}
		});
		
		hbox.setSpacing(10);
		hbox.getChildren().addAll(combo, combo2, btn);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
				
		root.setTop(hbox);
		root.setCenter(taResult);
		
		Scene scene = new Scene(root, 500, 400);
		
		primaryStage.setTitle("ComboBox 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
