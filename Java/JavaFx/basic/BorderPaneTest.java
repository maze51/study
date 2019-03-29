package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneTest extends Application {
	// 화면을 여러 영역으로 나눈다. top, left, center, right, bottom --> 기본 틀
	// 하나만 쓴다? 나머지 영역은 자동으로 사라진다. 둘만 쓴다? 나머지는(없는 곳은) 자동으로 사라진다(둘이, 셋이 딱 붙는다)
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 200);
		
		ToolBar toolbar = new ToolBar( // 버튼을 모아둔 것
			new Button("첫번째"), 
			new Button("두번째")
		);
		
		TextArea taMsg = new TextArea();
		
		BorderPane bottom = new BorderPane();
		TextField tfTest = new TextField();
		Button btn1 = new Button("확인");
		bottom.setCenter(tfTest);
		bottom.setRight(btn1); // bottom의 나머지 영역은 사라진 것. 다른 영역도 이와 같다
		
		root.setTop(toolbar); // toolbar가 top에
		root.setCenter(taMsg); // textarea가 center에
		root.setBottom(bottom); // borderpane을 하나 더 만들어서 bottom에
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("BorderPane 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
