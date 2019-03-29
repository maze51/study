package basic;

import javafx.application.Application;
import javafx.stage.Stage;

/*
	JavaFx 프로그램의 실행 순서
	
	main()메서드 ==> launch()메서드 ==> 객체의 생성자 메서드 ==>
	init()메서드 ==> start()메서드 ==> 창이 만들어진다. ==> 사용자가 사용 후 종료
	==> stop()메서드

 */
public class JavaFxLifeCycle extends Application {
	
	// 생성자 메서드
	public JavaFxLifeCycle() {
		System.out.println(Thread.currentThread().getName() + " : 생성자 메서드 실행");
	}
	
	@Override
	public void init() throws Exception {
		// 초기화 작업을 주로 기술한다(보통 생략하고 start를 쓴다)
		System.out.println(Thread.currentThread().getName() + " : init()메서드 실행");
	}

	@Override
	public void start(Stage primaryStage) {
		// JavaFx의 화면에 배치될 컨트롤들을 구성한다
		// 가장 중추적인 내용이 들어가는 메서드
		System.out.println(Thread.currentThread().getName() + " : start()메서드 실행");
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		// 프로그램이 종료될 때 처리해야 할 자원 반납 같은 작업을 진행한다
		
		/*
			JavaFx프로그램이 종료되는 경우
			
			1. 마지막 windows(Stage)가 닫힐 때
			2. 마지막 windows(Stage)의 close()메서드가 호출될 때
			3. 자바 코드의 Platform.exit()메서드가 호출될 때
			
			4. 자바 코드의 System.exit(0)메서드가 호출될 때
				--> 이 때만 stop()메서드를 거치지 않고 종료된다
		 */
		
		System.out.println(Thread.currentThread().getName() + " : stop()메서드 실행");
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " : main()메서드 실행");
		launch(args); // JavaFx 환경을 구성하고 init()메서드를 호출한다.
						// (해당 객체 생성 및 Stage객체를 생성한다)
	}
}
