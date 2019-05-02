package student.client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StudentMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/StudentMain.fxml"));
			Parent root = loader.load();
			
			// Fxml문서에 설정할 Controller객체 구하기
			StudentMainController mainController = loader.getController();
			mainController.setPrimaryStage(primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("학생 성적표");
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
