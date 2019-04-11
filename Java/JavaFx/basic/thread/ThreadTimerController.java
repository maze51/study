package basic.thread;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadTimerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;
    
    private boolean stop;

    @FXML
    void initialize() {
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
        assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
        // 이벤트 설정 추가 - 시작 버튼 클릭 이벤트 처리
        btnStart.setOnAction(e->{
        	stop = false;
        	
        	Thread th = new Thread(
        		new Runnable() {
					@Override
					public void run() {
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						while(!stop) {
							String strTime = sdf.format(new Date());
							// javaFX에서 만든 일반 쓰레드에서 javaFX의 컨트롤값을 변경시키면 오류가 발생한다.
							// 이때는 Platform.runLater()메서드를 이용하여 처리해야 한다.
							Platform.runLater(()->{
								// 이 영역에서 javaFX컨트롤의 데이터를 변경시키도록 한다.
								lblTime.setText(strTime);
							});
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e2) { }
						}
					}
				}	
			);
        	th.setDaemon(true);
        	th.start();
        });
        
        // 멈춤 버튼 클릭 이벤트 처리
        btnStop.setOnAction(e->{
        	stop = true;
        });
        
    }
}
