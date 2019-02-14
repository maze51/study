package f_OOP2;

public class InnerEx01 {
	
	class InstanceInner { // 인스턴스클래스
		int iv = 100;
//		static int cv = 200; // 클래스변수 선언 불가
		static final int CON = 100; // 상수는 사용 가능
	}
	
	static class StaticInner{ // 스태틱클래스
		static int cv = 10; // 클래스변수 선언 가능
		int iv = 400; // iv도 사용 가능. 인스턴스화가 필요할 수도, 아닐수도 있으므로 사용 가능
	}
	
	void method(){
		class LocalInner{ // 지역클래스
			int iv = 40; // iv사용가능
//			static int cv = 50; // cv사용불가. 먼저 로드불가. 객체를 만들어야 가능
			//상수는 어디든 가능
		}
	}
	
	
	
	
	
	
	
	
	
}
