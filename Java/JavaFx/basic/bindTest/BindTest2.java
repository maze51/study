package basic.bindTest;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BindTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		
		Circle circle1 = new Circle(50, 50, 50);
		Circle circle2 = new Circle(300, 300, 100);
		
		circle1.setFill(Color.BLUE);
		circle2.setFill(Color.RED);
		
		pane.getChildren().addAll(circle1, circle2);
		
		Scene scene = new Scene(pane);
		
		// bind()메서드의 계산에 사용되는 메서드는
		// Bindings객체의 정적 메서드로 지원한다.
		// 빨간원의 중심 좌표 이동하기
		circle2.centerXProperty().bind(
				Bindings.divide(scene.widthProperty(), 2) // 나눈 값을 앞에 적용하라는 의미. 계산해서 Bind에 넣으면 처리해 준다
				);
		circle2.centerYProperty().bind(
				Bindings.divide(scene.heightProperty(), 2)
				);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Binding연습2");
		primaryStage.setWidth(600);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
