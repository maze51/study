package basic.animation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private BorderPane login;

    @FXML
    private Button btnMain;

    @FXML
    void initialize() {
    	assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
        assert btnMain != null : "fx:id=\"btnMain\" was not injected: check your FXML file 'login.fxml'.";
        
        // 로그인 화면에서 메인 화면으로 전환
        btnMain.setOnAction(e->{
        	try {
				StackPane root = (StackPane) btnMain.getScene().getRoot();
				//root.getChildren().remove(login); // 애니메이션 효과 X
				
//				login.setTranslateX(0); // 시작값
				login.setRotate(720);
				
				login.setScaleX(1);
				login.setScaleY(1);
				
				KeyValue keyValue = new KeyValue(login.rotateProperty(), 1);
				KeyValue keyValue2 = new KeyValue(login.scaleXProperty(), 0);
				KeyValue keyValue3 = new KeyValue(login.scaleYProperty(), 0);
				
				
//				KeyValue keyValue = new KeyValue(login.translateXProperty(), 350);
				//KeyFrame keyFrame = new KeyFrame(Duration.millis(800), keyValue);
				KeyFrame keyFrame = new KeyFrame(Duration.millis(800), // 애니메이션 진행 중 창이 remove되는 것을 막기 위해 이와 같이 처리한다
						new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// 애니메이션이 끝난 다음에 처리할 내용을 기술한다.
								root.getChildren().remove(login);
							}
						},
						keyValue, keyValue2, keyValue3);
				
				Timeline timeline = new Timeline();
				timeline.getKeyFrames().add(keyFrame);
				timeline.play();
				
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        });
    }
}
