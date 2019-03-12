package basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		//멀티 쓰레드 프로그램
		
		// Thread를 사용하는 방법
		
		// 방법1 ==> Thread클래스를 상속받기
		//			Thread클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 호출한다.
		
		MyThread1 th1 = new MyThread1();
		th1.start(); // 쓰레드 환경을 만들고, 해당 쓰레드의 run()메서드를 호출해 준다.
		
		// 방법2 ==> Runnable인터페이스를 구현하기
		//			Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		//			그리고, 이 때 생성된 Thread의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();
		
		// 방법3 ==> 익명구현체(익명클래스)를 이용하기 (방법2의 응용)
		//	    ==> Runnable인터페이스를 구현한 익명구현체를 Thread인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < 201; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		th3.start();
		
		System.out.println("main메서드(main쓰레드) 작업 끝");
	}

}

// Thread클래스를 상속한 class
class MyThread1 extends Thread{
	private int a;
	public void test(){
		
	} // 이렇게 다른 변수나 메서드를 만들어 쓸 수도 있다. run()이 하나 더 있을 뿐.
	@Override
	public void run() {
		//이 run()메서드 영역에 쓰레드에서 처리할 내용을 기술한다.
		for (int i = 1; i < 201; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간) 메서드는 주어진 "시간"동안 작업을 잠시 멈춘다.
				// "시간"은 밀리세컨드(1/1000초) 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// Runnable인터페이스를 구현한 class
class MyThread2 implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i < 201; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/*
sleep(long millis)호출시 반드시 try-catch예외처리가 필요하다. sleep()으로 일시정지된 쓰레드는 시간이 다 되거나 interrupt()가 호출되어야(InterruptedException발생) 실행대기 상태가 되기 때문.
sleep 쓸 일이 많을 경우, 메서드를 만들어 사용하면 좋다
 

 Thread 스케줄러: 작동시간을 알아서 관리해 준다
 모든 프로그램에는 main쓰레드가 하나씩 존재한다. 따라서 이 프로그램에서 돌아가는 쓰레드는 총 4개.

 run메서드가 새로운 콜스택에 호출될 수 있도록 작업해주는 메서드가 start
 run을 그냥 호출하면? main위 start자리에 run이 그냥 실행되어버린다. 멀티가 아닌 싱글 쓰레드 프로그램처럼.
 run은 이를테면 해당 클래스의 메인 메서드

 다중 상속은 불가능. 여러 인터페이스의 구현은 가능
 쓰레드도 써야 하고, 다른 클래스도 상속받아 써야 할 때 2번 방법을 사용하면 된다. 아니면 1번.
*/