package basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		System.out.println("입력한 값 : " + str);
		
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
				
	} // 싱글 쓰레드라 10초간 입력을 기다렸다 종료시킬 수 없다.

}
