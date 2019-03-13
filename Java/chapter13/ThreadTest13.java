package basic;
/*
	3개(명)의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
	출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성해 보자.
 */


public class ThreadTest13 {
	public static String strRank = "";
	
	public static void main(String[] args) {
		DisplayCharacter[] dcArray = new DisplayCharacter[]{
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		
		for (int i = 0; i < dcArray.length; i++) {
			dcArray[i].start();
		}
		
		for (int i = 0; i < dcArray.length; i++) {
			try {
				dcArray[i].join(); // 다 끝날 때 까지 기다린다
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + strRank);
	}

}

// 알파벳을 출력하는 쓰레드
class DisplayCharacter extends Thread{
	private String name;
	
	// 생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (char c='A' ; c<='Z' ; c++) {
			System.out.println(name + "의 출력 문자 : " + c);
			try { // 실행을 똑같이 한다면 순서의 의미가 없다. sleep의 값을 똑같이 해도 의미가 없다. 그래서 랜덤값을 사용함.
				Thread.sleep((int)(Math.random()*301 + 200)); // 200~500사이의 난수를 sleep의 값으로 설정한다.
			} catch (InterruptedException e) {
			}
		}
		System.out.println(name + "출력 끝...");
		ThreadTest13.strRank += name + " ";
	}
}