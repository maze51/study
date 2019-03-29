package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StackPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox();
		
		// StackPane은 객체를 한 곳에 계속 쌓는 형식으로 배치한다.
		
		StackPane stack = new StackPane(); // StackPane객체 생성
		
		ImageView imgView = new ImageView(); // 이미지를 보여주는 ImageView객체 생성
		
		// 화면에 나타날 Image객체 생성
		Image img = new Image(getClass().getResourceAsStream("../images/test.jpg"));
		
		imgView.setImage(img); // ImageView에 Image객체 넣기
		
		Button btnTest = new Button("연 습 용 버 튼");
		
		// StackPane에 이미지뷰와 버튼 배치
		stack.getChildren().addAll(imgView, btnTest);
		
		root.getChildren().add(stack);
		root.setPadding(new Insets(15));
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 400, 300); // scene에서 창의 크기를 설정할 수도 있다
		
		primaryStage.setTitle("StackPane 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} // Stack에 버튼을 먼저 넣고 이미지를 넣으면 버튼은 보이지 않는다(출력됐지만). 반대로 넣으면 잘 보인다

	public static void main(String[] args) {
		launch(args);
	}
}
