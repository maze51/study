package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/*
	CUI : Character User Interface
	GUI : Graphic User Interface
	
	Java의 GUI 발전 순서 : AWT  -->  Swing  -->  JavaFx
	
	JavaFx에서 사용되는 용어 및 객체
		- Stage 객체 (무대) --> Windows 창을 객체화한 것
		- Scene 객체 (장면) --> 무대에 장면을 배치하여 나타낸다.
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
