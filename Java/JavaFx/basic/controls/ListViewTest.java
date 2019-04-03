package basic.controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListViewTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		ListView<String> list = new ListView<String>();
		
		// ListView에서 사용할 데이터는 ObservableList로 만든다.
		ObservableList<String> data =
				FXCollections.observableArrayList(
					"green", "gold", "blue", "red", "black", "brown",
					"pink", "blueviolet", "chocolate"
				);
		
		Label lbl = new Label();
		lbl.setFont(Font.font(20));
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(list, lbl);
		
		// ListView에 데이터 세팅하기
		list.setItems(data);
		
		// ListView에서 값을 선택했을 때 처리
		list.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) { // oldValue는 선택 전 값, newValue는 현재 선택값
						lbl.setText(newValue); // 현재 선택한 값을 Label에 출력하기
						lbl.setTextFill(Color.web(newValue)); // Label의 글자색 변경
					}
				}
			);
		
		// ListView의 Cell에 출력되는 데이터의 원본은 변하지 않고 
		// 화면에 나타나는 내용을 변경하는 방법					여기저기서 사용됨. 숙지!!
		list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty); // 이 줄은 삭제하지 않는다.
						Rectangle rect = new Rectangle(100, 20);
						if(!empty){ // 셀이 비었는지 여부 검사
							rect.setFill(Color.web(item));
							setText("==> " + item + " <=="); // 변경된 값 적용하기 (문자열일 경우 사용)
							//setGraphic(rect); // 변경된 값 적용하기 (문자열이 아닌 경우에 사용)
						}
					}
				};
			}
		});
		
		Scene scene = new Scene(vbox);
		primaryStage.setTitle("ListView 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
