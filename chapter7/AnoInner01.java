package f_OOP2;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnoInner01 {
	public static void main(String[] args) {
		
		Button b = new Button();
		ActionListener ac = new EventHandler(); // 인터페이스의 다형성을 이용해서 만든 것
		b.addActionListener(ac);
	}
}

class EventHandler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("버튼눌림");
	}
	
}