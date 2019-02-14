package f_OOP2;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnoInner02 {
	public static void main(String[] args) {
		
		Button b = new Button();
		
		b.addActionListener(new ActionListener() { // 아직 객체가 완성되지 않은 것
			//중괄호 열어서 바로 구현해버린 것
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("버튼눌림");
			}
		} // 익명클래스. 클래스를 만듬과 동시에 객체생성까지 한 것. 안에는 미구현된 부분을 구현. 
		);
		
		
		
		
	}
}
