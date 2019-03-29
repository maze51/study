package basic;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxmlLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// fxml파일을 읽어와 현재의 Stage에 적용하는 방법
		
		// fxml문서를 읽어오는 방법1(1번 방법이 편해서 일반적으로 쓴다. 필요할 때 2번을 쓴다)
		VBox root = FXMLLoader.load(getClass().getResource("FxmlLayout.fxml")); // 기본적인 방법
		// 현재 클래스를 기준으로. 여기 없다면(다른 위치에 있다면)NullPointerException : Location is Required라고 뜬다
		// 그럴 때는? ../application/FxmlLayout.fxml 식으로 지정해줘야 한다.
		
		//VBox root = FXMLLoader.load(FxmlLayout.class.getResource("FxmlLayout.fxml")); // 이렇게 쓸 수도 있다. 상속이 복잡하면 class명을 써주는 게 좋다
		//Parent root = FXMLLoader.load(getClass().getResource("FxmlLayout.fxml")); // Root 컨테이너가 뭔지 잘 모르겠다 싶으면 조상인 Parent만 써줘도 잘 된다
		
		// fxml문서를 읽어오는 방법2
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlLayout.fxml")); // Loader객체를 생성해서 읽어오는 방법
//		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fxml문서를 이용한 레이아웃");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
