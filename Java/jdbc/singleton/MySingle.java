package singleton;
/*
	Singleton 패턴 ==> 객체(인스턴스)를 하나만 만들어지게 하는 방법
			==> 외부에서 new를 사용하지 않고 동일한 객체(인스턴스)를 반환하는 클래스
			
	- Singleton Class를 구성하는 방법
	1. 생성자를 private으로 한다.
		(외부에서 생성자에 접근하지 못하게 하기 위해서)
	2. 객체의 내부에서 해당 객체의 인스턴스를 생성해서 생성된 인스턴스의 참조값을 반환하는 메서드를 만든다.
		(이 메서드의 이름은 보통 getInstance()이고 static으로 만든다)
	
 */

public class MySingle {
	// 1. 자기 class의 참조값을 저장할 변수 선언 (반드시 static으로 만든다)
	private static MySingle single;
	
	// 2. 생성자는 private으로 구성한다.
	private MySingle(){
		System.out.println("생성자입니다."); // 딱히 할 일이 없으면 기본 생성자처럼 비운다
	}
	
	// 3. 자기 class의 객체를 생성해서 반환하는 메서드 (반드시 static으로 만든다)
	public static MySingle getInstance(){
		if(single==null) single = new MySingle(); // null이라는 것은 한번도 생성된 적이 없다는 뜻
		
		return single;
	}
	
	// 기타 이 클래스에서 처리할 메서드나 멤버변수들을 사용하는 내용을 기술한다.
	public void displayTest(){
		System.out.println("싱글톤 객체의 메서드 입니다.");
	}
}
