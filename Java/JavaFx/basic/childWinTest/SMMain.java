package basic.childWinTest;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SMMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader load = new FXMLLoader(getClass().getResource("SMMain.fxml"));
			Parent root = load.load();
			
			SMMainController mainController = load.getController();
			mainController.setSMMainStage(primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("AppMain");
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
