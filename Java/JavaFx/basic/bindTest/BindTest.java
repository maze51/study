package basic.bindTest;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BindTest extends Application {
	
	/*
		- Binding은 한 쪽의 내용이 변경되면 이와 연결된 다른 쪽의 내용도 같이 변경되는 기술을 말한다.
		- 값이 변할 때 영향을 받는 쪽에서 명령을 처리한다.
		
		- Binding처리를 하는 명령
		 	bindBidirectional()메서드와 bind()메서드가 있다
		 	1) bind()메서드 ==> 단방향
		 	2) bindBidirectional()메서드 ==> 양방향
	 */
	@Override
	public void start(Stage primaryStage) {
		TextArea txt1 = new TextArea();
		TextArea txt2 = new TextArea();
		
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		
		Label lbl1 = new Label("첫번째");
		Label lbl2 = new Label("두번째");
		
		// Binding 처리하기
//		 txt2.textProperty().bind(txt1.textProperty()); // 단방향
		
		// 양뱡향
//		 txt2.textProperty().bindBidirectional(txt1.textProperty()); // 방법 1
		Bindings.bindBidirectional(txt1.textProperty(), txt2.textProperty()); // 방법 2
		
		root.getChildren().addAll(lbl1, txt1, lbl2, txt2);
		
		Scene scene = new Scene(root, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Binding 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
