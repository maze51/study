package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneTest extends Application {
	// FlowPane ==> 객체들을 순서대로 배치하는 컨테이너
	// AnchorPane ==> 객체들의 좌표를 이용해서 배치하는 컨테이너

	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		root.setPrefSize(300, 150);
		
		Label lblId = new Label("아 이 디 : ");
		lblId.setLayoutX(62); // x좌표값 설정
		lblId.setLayoutY(26); // y좌표값 설정
		
		Label lblPass = new Label("패스워드 : ");
		lblPass.setLayoutX(62);
		lblPass.setLayoutY(72);
		
		TextField tfId = new TextField();
		tfId.setLayoutX(132);
		tfId.setLayoutY(22);
		
		PasswordField pfPass = new PasswordField();
		pfPass.setLayoutX(132);
		pfPass.setLayoutY(68);
		
		Button btnLogin = new Button(" 로 그 인 ");
		btnLogin.setLayoutX(88);
		btnLogin.setLayoutY(114);
		
		Button btnCancel = new Button(" 취 소 ");
		btnCancel.setLayoutX(180);
		btnCancel.setLayoutY(114);
		
		root.getChildren().addAll(lblId, lblPass, tfId, pfPass, btnLogin, btnCancel);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("AnchorPane 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
