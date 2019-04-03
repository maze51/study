package basic.controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 라디오버튼들을 묶음으로 처리할 객체
		ToggleGroup group = new ToggleGroup();
		
		ImageView icon = new ImageView();
		
		RadioButton rb1 = new RadioButton("Home");
		rb1.setToggleGroup(group); // 그룹설정
		rb1.setUserData("Home"); // 선택했을 때 값을 나타내기 위해 설정
		
		RadioButton rb2 = new RadioButton("Calendar");
		rb2.setToggleGroup(group);
		rb2.setUserData("Calendar");
		
		RadioButton rb3 = new RadioButton("Contacts"); // 괄호 안 글자는 radio버튼 옆에 같이 띄워주는 글자
		rb3.setToggleGroup(group);
		rb3.setUserData("Contacts");
		
		// ToggleGroup내에서 RadioButton중 하나가 선택되었을 때 처리하기
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				/*
				// 설정한 userData값을 이용하기
				if (group.getSelectedToggle().getUserData()!=null) {
					// userData값 가져오기
					String tmp = group.getSelectedToggle().getUserData().toString();
					
					Image img = new Image(getClass().getResourceAsStream("../../images/" + tmp + ".jpg"));
					icon.setImage(img);
				}
				*/
				
				// 선택한 RadioButton객체를 직접 사용하는 방법
				
				// Toggle객체는 RadioButton객체로 변환 가능하다.
				// newValue는 현재 선택한 RadioButton객체가 된다.
				
				String tmp = ((RadioButton)newValue).getText();
				
				Image img = new Image(getClass().getResourceAsStream("../../images/" + tmp + ".jpg"));
				icon.setImage(img);
				
			}
			
		});
		
		rb1.setSelected(true); // RadioButton을 체크한다 (시작부터 체크된 상태로 설정한다)
		
		/*
			RadioButton의 isSelected() 메서드와 setSelected() 메서드는
			CheckBox의 메서드와 같은 기능이다. 
		 */
		Button btn = new Button("확인");
		btn.setOnAction(e->{
			System.out.println("Home 선택여부 : " + rb1.isSelected());
		});
		
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(rb1, rb2, rb3, btn);
		
		HBox hbox = new HBox(50);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(vbox, icon);
		
		Scene scene = new Scene(hbox, 250, 150);
		
		primaryStage.setTitle("RadioButton  연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
