package basic;

/*
	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다.
	이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 
	나중에 실행되는 프로그램에 영향을 줄 수 있다.
	그래서 이 stop()메서드의 사용은 추천하지 않는다.
	
	stop()은 락을 걸고 풀지 않은 상태에서 끝내버림. -> 교착상태
	파일을 프로그램 A에서 열었을 때 프로그램 B에서는 '이미 사용중입니다'라고 뜨는 것처럼
	suspend() resume() stop() 모두 되도록 쓰지 말 것
 */

public class ThreadTest12 {

	public static void main(String[] args) {
		/*
		ThreadStopEx01 th = new ThreadStopEx01();
		th.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		th.setStop(true); // 쓰레드를 종료 시키는 여러 방법 중 하나
		//th.stop(); // 자원 정리, 실행 종료를 찍을 시간 없이 바로 끝난다
		 
		 */
		
		// interrupt() 메서드를 이용한 쓰레드 멈추기
		ThreadStopEx02 th2 = new ThreadStopEx02();
		th2.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		th2.interrupt(); // interrupt() 메서드를 호출해서 쓰레드를 멈추게 한다.
		
	}

}

class ThreadStopEx01 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop){
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop){
			System.out.println("쓰레드 실행 중...");
		}
		
		System.out.println("자원 정리하기....");
		System.out.println("실행 종료!");
	}
}

// interrupt() 메서드를 이용하여 쓰레드를 멈추게 하는 방법
class ThreadStopEx02 extends Thread{
	@Override
	public void run() {
		/*
		// 방법1
		try {
			while(true){
				System.out.println("실행 중...");
				Thread.sleep(1); // interrupt()메서드가 호출되면 InterruptedException이 발생한다.
			}
		} catch (InterruptedException e) {
		}
		*/
		
		while(true){
			System.out.println("쓰레드 실행 중...");
			/*
			// interrupt() 메서드가 호출되었는지 검사하는 방법1
			// isInterrupted() 메서드 ==> 인스턴스 객체용 메서드로
			// interrupt() 메서드가 호출되면 true를 반환하고, 그렇지 않으면 false를 반환한다.
			if(this.isInterrupted()){
				break;
			}
			*/
			
			// interrupt() 메서드가 호출되었는지 검사하는 방법2
			// interrupted() 메서드는 Thread의 정적메서드이고
			// interrupted() 메서드가 호출되면 true, 그렇지 않으면 false가 반환된다.
			if(Thread.interrupted()){ // 위와 거의 같은 기능. 위는 인스턴스 메서드, 여기는 클래스메서드
				break;
			}
		}
		
		System.out.println("자원 정리...");
		System.out.println("실행 종료...");
	}
}