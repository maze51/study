package basic.controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		String[] names = new String[] {"Security", "Project", "Chart"};
		Image[] images = new Image[names.length];
		ImageView[] icons = new ImageView[names.length];
		CheckBox[] chkboxs = new CheckBox[names.length];
		
		Rectangle rect = new Rectangle(90, 30);
		rect.setArcHeight(10);
		rect.setArcWidth(10); // 둥근 모서리 만들기 (꼭지점 모따기)
		rect.setFill(Color.rgb(41, 41, 41));
		
		for (int i = 0; i < names.length ; i++) {
			final Image img = images[i] = new Image(
					getClass().getResourceAsStream("../../images/" + names[i] + ".png"));
			
			final ImageView icon = icons[i] = new ImageView();
			chkboxs[i] = new CheckBox(names[i]);
			
			
			// CheckBox의 상태값(check여부)을 감시하고 이 값이 변경되었을 때 처리
			chkboxs[i].selectedProperty().addListener(
					// 익명 구현체 구현 부분
					new ChangeListener<Boolean>() {

						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
								Boolean newValue) {
							// oldValue ==> 변경되기 전 상태값
							// newValue ==> 변경된 후의 상태값
							icon.setImage(newValue==true ? img : null);
							
						}
					});
			
			
			/*
			// CheckBox를 클릭했을 때 처리 -- 이벤트 처리
			chkboxs[i].setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					CheckBox chk = (CheckBox) event.getSource(); // 이벤트가 최초로 발생한 객체 반환
					icon.setImage(chk.isSelected()==true ? img : null);
				}
			});
			*/
		} // end of for
		
		Button btn0k = new Button("확인");
		btn0k.setOnAction(e->{
			// CheckBox의 체크 여부는 isSelected()메서드의 반환값이 true이면 체크된 상태이고
			// 반환값이 false이면 체크가 해제된 상태를 말한다.
			//System.out.println( chkboxs[0].isSelected() );
			
			// setSelected()메서드에 true를 주면 체크되고, false를 주면 체크가 해제된다.
			chkboxs[1].setSelected(false);
			
			if(chkboxs[2].isSelected()==true){
				System.out.println("선택됨");
			} else {
				System.out.println("해제됨");
			}
		});
		
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(chkboxs);	// 체크박스를 담음
		vbox.getChildren().add(btn0k);
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(icons);
		hbox.setPadding(new Insets(0,0,0,5));
		
		StackPane stack = new StackPane();
		stack.getChildren().addAll(rect, hbox);
		StackPane.setAlignment(rect, Pos.TOP_CENTER);
		
		HBox root = new HBox();
		root.setPadding(new Insets(20,10,10,20));
		root.setSpacing(40);
		root.getChildren().addAll(vbox, stack);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("CheckBox 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
