package basic.childWinTest;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWin extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// 이 방법으로는 Controller객체를 구할 수 없다
			//Parent root = FXMLLoader.load(getClass().getResource("MainWin.fxml"));
			
			// Fxml문서에 설정된 Controller 객체를 구하는 방법
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWin.fxml"));
			Parent root = loader.load(); // Fxml문서의 내용을 객체화한다.
			
			// 위의 loader객체변수를 이용하여 Controller객체를 구한다.
			MainWinController mainController = loader.getController();
			mainController.setMainStage(primaryStage); // 현재의 Stage정보를 Controller에 전달한다(setter를 통해 부모창의 primaryStage값을 넘겨준다).
			// (다른 것을 보낼 때도 이런 식으로 처리할 수 있다)
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("자식창 연습");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
