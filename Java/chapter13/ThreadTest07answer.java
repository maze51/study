package basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.

	컴퓨터의 가위바위보는 난수를 이용하여 구하고,
	사용자는 showInputDialog() 메서드를 이용하여 입력받는다.

	입력 시간은 5초로 제한하고 카운트다운을 진행한다.
	5초안에 입력이 없으면 게임을 진 것으로 처리하고 프로그램을 종료한다.

	5초안에 입력이 완료되면 승패를 구해서 그 결과를 출력한다.

	결과예시)
			=== 결 과 ===
			컴퓨터 : 바위
			당  신 : 보
			결  과 : 당신이 이겼습니다.
			(컴퓨터가 이겼습니다 / 비겼습니다)

 */
public class ThreadTest07answer {
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		// 난수를 이용하여 컴퓨터의 가위바위보를 정한다.
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random()*3); // 0 ~ 2 사이의 난수 만들기
		String com = data[index];
		
		// 사용자로부터 가위바위보 입력받기.
		String man = null;
		gt.start(); // 카운트다운 시작
		
		do{
		man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		}while(man==null || !man.equals("가위") && !man.equals("바위") && !man.equals("보"));
		
		inputCheck = true; // 입력이 완료되었다는 표시를 한다.
		
		// 결과 판정
		String result = "";
		if(com.equals(man)){
			result = "비겼습니다.";
		} else if(com.equals("가위") && man.equals("보") ||
				  com.equals("바위") && man.equals("가위") ||
				  com.equals("보") && man.equals("바위")){
			result = "컴퓨터가 이겼습니다.";
		} else {
			result = "당신이 이겼습니다.";
		}
		
		// 결과 출력
		System.out.println("=== 결 과 ===");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당  신 : " + man);
		System.out.println("결  과 : " + result);
		
	}
}

// 카운트 다운을 처리하는 쓰레드

class GameTimer extends Thread {
	@Override
	public void run() {
		for(int i=5 ; i>=1 ; i--){
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if(ThreadTest07answer.inputCheck==true){
				return;
			}
		}
		
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
	}
}























