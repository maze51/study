package basic.eventTest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventTest extends Application {
	TextArea txtArea = new TextArea();
	TextField tfMsg = new TextField();

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10); // 숫자는 컨트롤 간 간격
		root.setPadding(new Insets(10));
		
		HBox hbox = new HBox(15);
		hbox.setPadding(new Insets(10));
		
		Button btn1 = new Button("첫번째");
		// 이벤트 설정 및 처리방법
		// 객체.setOn이벤트명()메서드에 EventHandler인터페이스를 익명구현체 형식으로 구현한다.
		// (이 인터페이스에는 handle()메서드가 있는데 이 메서드에 처리할 내용을 기술한다)
		// (이것은 람다식으로 작성할 수 있다)
		/*
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 처리할 내용을 이곳에 기술한다
				// setText(메시지) ==> 원래 있는 내용은 지우고 '메시지'를 출력한다.
				txtArea.setText("첫번째 버튼을 눌렀습니다.");
			}
		});
		*/
		/*
		btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				txtArea.setText("첫번째 버튼을 눌렀습니다. - MouseClicked 이벤트");
			}
		});
		*/
		// 람다식
		btn1.setOnAction( event -> {
			tfMsg.setText("람다식을 이용한 이벤트 처리");
		});
			
		// 여기까지가 첫번째 방법
		
		Button btn2 = new Button("두번째");
		// 방법 2
		// 객체.addEventHandler()메서드를 사용한다.
		// 이 메서드의 첫번째 매개변수로 이벤트 종류를 지정하고,
		// 두번째 매개변수는 EventHandler인터페이스를 구현한 객체를 지정한다.
		btn2.addEventHandler(
				ActionEvent.ACTION, // 이벤트 종류
				/*
				new EventHandler<Event>() { // EventHandler를 구현한 내용

				@Override
				public void handle(Event event) {
					//txtArea.setText("두번째 입니다"); // 그 전 내용은 다 지우고 이 내용을 출력한다
					String temp = tfMsg.getText(); // 값 읽기
					//txtArea.setText(temp);
					txtArea.appendText(temp + "\n"); // 기존의 내용 뒤에 추가한다
				}
			}
		*/
				e -> {
					txtArea.setText("람다식 두번째 버튼\n"); 
					txtArea.appendText("이어서 출력하기\n");
				}
		);
		//==============================================================
		
		Button btn3 = new Button("세번째");
		// 방법 3-2
		// setOn이벤트명()메서드나 addEventHandler() 메서드의 매개변수로
		// EventHandler인터페이스를  구현한 inner클래스의 객체의 인스턴스를 넣어준다
		btn3.setOnAction(new MyEventHandler());
		
		//==============================================================
		
		Button btn4 = new Button("네번째");
		// 방법 4-2
		btn4.addEventHandler(ActionEvent.ACTION, new MyEventHandler2(txtArea, tfMsg));
		
		//==============================================================
		hbox.getChildren().addAll(btn1, btn2, btn3, btn4);
		root.getChildren().addAll(hbox, tfMsg, txtArea);
		
		Scene scene = new Scene(root,500,350);
		
		primaryStage.setTitle("Event 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// 방법 3-1
	// EventHandler 인터페이스를 구현하는 inner클래스를 작성한다.
	class MyEventHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			txtArea.setText("세번째 이벤트 처리 - inner클래스 이용");
		}
	}
}

// 방법 4-1
// 외부의 독립된 형태의 객체를 이용하는 방법
// 이 객체도 EventHandler인터페이스를 구현한다
class MyEventHandler2 implements EventHandler<ActionEvent>{

	TextArea txt2;
	TextField tf2;
	
	// 생성자
	public MyEventHandler2(TextArea txt2, TextField tf2) {
		super();
		this.txt2 = txt2;
		this.tf2 = tf2;
	}
	
	@Override
	public void handle(ActionEvent event) {
		txt2.setText("네번째 독립된 외부 클래스를 이용하는 방법입니다\n");
		String tmp = tf2.getText();
		txt2.appendText(tmp + "\n");
		
	}

	
	
}
