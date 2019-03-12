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
public class ThreadTest07 {

	public static boolean inputCheck = false;
	public static String userInput = "";
	public static int comInput = 0;

	public static void main(String[] args) {
		
		Rps th1 = new Rps();
		CountDowna th2 = new CountDowna();
		
		th1.start();
		th2.start();
	}
}

class Rps extends Thread{

	@Override
	public void run() {
		ThreadTest07.userInput = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하세요\n5초 안에 입력하지 않으면 패배합니다.");
		ThreadTest07.inputCheck = true;
		
		comGbb();
		
		if(ThreadTest07.userInput==null){ // 취소버튼을 눌렀을 경우
			System.out.println("프로그램을 종료합니다");
			System.exit(0);
		}
		System.out.println("당  신 : " + ThreadTest07.userInput);
		
		game();
	}
	
	// 컴퓨터의 가위바위보 값을 구하는 메서드
	public void comGbb(){
		int com = (int)(Math.random()*3+1);
		ThreadTest07.comInput = com;
		System.out.println("=== 결 과 ===");
		if(com==1){
			System.out.println("컴퓨터 : 가위");
		} else if(com==2){
			System.out.println("컴퓨터 : 바위");
		} else {
			System.out.println("컴퓨터 : 보");
		}
	}
	
	// 양 쪽의 값을 비교해 결과값을 구하는 메서드
	public void game(){
		int user = 0;
		int result = 0;
		
		if(ThreadTest07.userInput.equals("가위")){
			user = 1;
		} else if(ThreadTest07.userInput.equals("바위")){
			user = 2;
		} else if(ThreadTest07.userInput.equals("보")){
			user = 3;
		} else {
			System.out.println("가위바위보에 맞는 값을 입력해 주세요");
			return;
		}
		
		result = user - ThreadTest07.comInput;
		switch(result){
		case 1: case -2:
			System.out.println("결  과 : 당신이 이겼습니다.");
			break;
		case -1: case 2:
			System.out.println("결  과 : 컴퓨터가 이겼습니다.");
			break;
		case 0:
			System.out.println("결  과 : 비겼습니다");
			break;
		default:
			System.out.println("정상적인 값이 입력되지 않았습니다");
		}
	}
}

class CountDowna extends Thread{
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if(ThreadTest07.inputCheck==true){
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}

		System.out.println("컴퓨터가 이겼습니다.");
		System.out.println("게임을 종료합니다.");
		System.exit(0);
	}
}
