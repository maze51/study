package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFxProgramLayout extends Application {
	/*
		JavaFx에서 사용되는 객체 분류
	1. 컨테이너 객체 ==> 다른 컨테이너 객체나 컨트롤 객체를 가지고 있는 객체
	2. 컨트롤 객체 ==> 사용자가 직접 사용하는 객체 (예: 버튼, 체크박스, 리스트박스 등)
	
		프로그래밍 작업 순서
	컨테이너객체 작성 ==> 컨트롤객체들을 작성 (여기까지는 순서무관) ==> 컨트롤객체들을 컨테이너객체에 배치한다
	==> 루트 컨테이너객체를 Scene객체에 추가 ==> Scene객체를 Stage에 추가 ==> show()메서드 호출
	
	 */
	
	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox();	// VBox 레이아웃 객체 생성 (컨테이너)
		root.setPrefWidth(650); // VBox의 너비
		root.setPrefHeight(150); // VBox의 높이
		
		root.setAlignment(Pos.CENTER); // 배치될 컨트롤들을 가운데 정렬 시킨다
		root.setSpacing(20); // 컨트롤 객체들 간의 간격
		
		// 안쪽 여백 ==> Insets객체를 이용해서 설정한다
		// Insets객체는 위, 오른쪽, 아래, 왼쪽 순으로 값을 설정해 준다
		// root.setPadding(new Insets(10, 10, 10, 10));
		root.setPadding(new Insets(10));
		
		Label label = new Label(); // Label객체 생성
		label.setText("안녕하세요 JavaFx입니다");
		label.setFont(new Font(20)); // Font객체를 이용하여 글자 크기 설정
		
		Button btnClose = new Button();
		btnClose.setText("종료");
		
		// 버튼을 클릭했을 때 처리 (이벤트 처리하기)
		btnClose.setOnAction(new EventHandler<ActionEvent>() { // 나중에 람다식으로 고칠 수 있다
			
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
				//primaryStage.close();
				//System.exit(0);
			}
		});
		
		HBox hbox = new HBox(); // 컨트롤들을 가로로 배치하는 컨테이너
		hbox.setPadding(new Insets(10, 10, 10, 10)); // 안쪽 여백
		hbox.setSpacing(10); // 컨트롤 객체간의 간격
		hbox.setAlignment(Pos.CENTER);
		
		// TextField객체 생성
		TextField txtField = new TextField();
		txtField.setPrefWidth(300); // TextField의 가로 크기 설정
		
		Button btnOk = new Button(" 확 인 "); // Button객체 생성
		
		// HBox의 ObservableList개체 구하기
		ObservableList<Node> list = hbox.getChildren();
		list.addAll(txtField, btnOk); // 생성된 컨트롤들을 HBox에 배치(추가)한다.
		
		
		
		// 컨테이너 객체인 VBox에 컨트롤들을 추가한다.
		root.getChildren().add(label);
		root.getChildren().add(hbox); // 컨테이너 안에는 보통 컨트롤을 넣지만, 이처럼 다른 컨테이너도 넣을 수 있다.
		root.getChildren().add(btnClose);
		
		// 루트컨테이너를 지정하여 Scene객체를 생성한다.
		Scene scene = new Scene(root);
		
		// Stage객체에 Scene객체를 추가한다
		primaryStage.setScene(scene);
		primaryStage.setTitle("첫번째 창 연습"); // 창 제목 설정
		
		primaryStage.show(); // 윈도우창 보이기
	}

	public static void main(String[] args) {
		launch(args);
	}
}
