package e_OOP;

public class TvTest {
	public static void main(String[] args) {
		System.out.println(Tv.color);		// System 클래스의 println메서드를 호출한 것. 하지만 설명에는 일단 제외
		//클래스친구들은 클래스명. 으로 소환가능. 이클립스 창 세모 모양에서 뚫린건 변수, 채워진 건 메서드, S는 static이 붙었다는 뜻.
		Tv.color = "black";					//Tv(클래스명).color(클래스변수명)으로 호출. 값을 변경
		System.out.println(Tv.color);
		Tv.changeColor();					//Tv클래스에 있으니 클래스명을 넣고 메서드명 입력
		System.out.println(Tv.color);
		
		
		//인스턴스친구들은 인스턴스화 해야 소환가능
		Tv t = new Tv();					//인스턴스화. 주소가 생기고 그걸 t에 넣어준 것.		t는 메인 메서드의 지역변수. 참조형. 주소를 저장.
		t.channel = 20;						//인스턴스변수는 이처럼 참조변수(t)를 통해 t의 주소로 접근
		t.channelUp();						
		System.out.println(t.channel);
		System.out.println(t.power);		//boolean타입의 기본형 false로 저장됨
		t.power();
		System.out.println(t.power);		//power메서드를 한 번 호출하면 반대로 바꿔줌
		
		
	}

}
//이클립스에서 메서드 따라갈 때는 클릭하고 f3
//이클립스에서 블럭잡고 Alt+상하키: 블럭잡은 것들 상하이동
//클래스가 같은 자바 파일에 있지만, 둘은 분리된 것. 다른 .class파일로 저장된다.
//main 메서드가 필수는 아니다. 실행시킬 때는 필수.
//값이 들어가기 전에는 유형별 기본값이 존재.

class Tv{
	
	// 멤버변수		전역변수는 클래스 선언과 초기화를 분리할 수 없다
	static String color; // 클래스변수		어느 메서드에서나 사용할 수 있다
	boolean power; // 인스턴스변수(static 없으면)
	int channel;
	
	static void changeColor(){ // 클래스메서드, 색상을 변경
		color = "yellow";
	}
	
	void power(){ // 전원
		power=!power; // 꺼져있으면 켜지게, 켜져있으면 꺼지게
	}
	
	void channelUp(){ // 이처럼 메서드로 계속 조작
		channel++;
	}
	
}





