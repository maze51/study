package basic.animation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RootController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'root.fxml'.";
        btnLogin.setOnAction(e->btnLoginClicked(e)); // 이렇게 별도 메서드를 만들어 처리할 수도 있다
    }
    
    // 메인 화면에서 Login버튼을 클릭했을 때
    public void btnLoginClicked(ActionEvent e) {
    	try {
			Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			// 루트 컨트롤러 구하기
			// 형식) 현재 화면에 있는 아무 컨트롤러객체.getScene().getRoot();
			StackPane root = (StackPane) btnLogin.getScene().getRoot();
			root.getChildren().add(login);
			
			/*
			// 이동 애니메이션
			login.setTranslateX(350); // X축으로 평행이동할 양 설정
									// (애니메이션을 시작할 위치로 설정한다)
			
			// 타겟속성과 종료값을 설정하는 객체 생성
			// 형식) new keyValue(변경될대상속성, 종료값);
			KeyValue keyValue = new KeyValue(login.translateXProperty(), 0);
			
			*/
			
			/*
			// Fade효과 (불투명도를 이용하고 값의 범위는 0.0(투명) ~ 1.0(불투명))
			login.setOpacity(0); // 시작 불투명도 설정
			
			KeyValue keyValue = new KeyValue(login.opacityProperty(), 1); // 최종 목적값
			*/
			
			/*
			// 회전 효과
			login.setRotate(720); // 회전 시작 각도
			KeyValue keyValue3 = new KeyValue(login.rotateProperty(), 0); // 최종 목적값
			*/
			
			// 확대, 축소 효과
			login.setScaleX(0); // 확대 축소 배율지정 (값이 2이면 두 배 크기라는 의미이다)
			login.setScaleY(0);
			
			KeyValue keyValue = new KeyValue(login.scaleXProperty(), 1);
			KeyValue keyValue2 = new KeyValue(login.scaleYProperty(), 1);
			
			
			// 애니메이션의 지속시간과 KeyValue를 설정하는 객체 생성 ==> KeyFrame
			// 형식1) new KeyFrame(지속시간, keyValue객체);
			// 형식2) new KeyFrame(지속시간, 애니메이션 종료 후 처리할 이벤트, keyValue객체);
			//KeyFrame keyframe = new KeyFrame(Duration.millis(900), keyValue);
			KeyFrame keyframe = new KeyFrame(Duration.millis(900), keyValue, keyValue2); // 여러 keyValue를 넣으면 여러 효과를 동시에 줄 수 있다
			
			
			// KeyFrame 에 설정된 내용대로 애니메이션을 진행시키는 객체
			Timeline timeline = new Timeline();
			
			// Timeline에 KeyFrame 추가
			timeline.getKeyFrames().add(keyframe);
			
			// 애니메이션 실행
			timeline.play();
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
    }
}
