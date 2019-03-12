package basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {
	// 입력 여부를 확인하기 위한 변수 선언
	// 모든 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		DataInput th1 = new DataInput();
		CountDown th2 = new CountDown();
		
		th1.start();
		th2.start();
	}

}

// 데이터를 입력하는 쓰레드
class DataInput extends Thread{
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		ThreadTest06.inputCheck = true; // 입력이 완료되었다는 처리를 한다.
		System.out.println("입력값 : " + str);
	}
}

// 카운트다운을 처리하는 쓰레드
class CountDown extends Thread{
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			// 입력이 완료되면 쓰레드를 종료시킨다.
			if(ThreadTest06.inputCheck==true){
				return; // run()메서드가 끝나면 쓰레드가 끝나는 것과 같다.
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		
		// 10초 동안 입력이 없으면 프로그램을 종료시킨다.
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램 종료
	}
}