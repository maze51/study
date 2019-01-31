package e_OOP;

public class MemberCall {
	int iv = 10;		// 인스턴스변수
	static int cv = 20;
	
	int iv2 = cv;		// 
	
//	static int cv2 = cv; // 같은 클래스변수끼리는 사용가능     iv는 불가능
	
	static void staticMethod(){
		System.out.println(cv); // 같이 로드되었으므로 cv사용 가능. iv는 불가능
	}
	
	void instanceMethod(){
		System.out.println(cv);
		System.out.println(iv);
	}
	
	static void staticMethod2(){ // 이것만 사용가능
		cv = 200;
		staticMethod();
		staticMethod2();
	}
	
	void instanceMethod2(){		// 모든 변수와 메서드 사용가능. iv도 마찬가지.
		iv = 100;
		cv = 200;
		iv2 = 500;
		staticMethod();
		instanceMethod();
		staticMethod2();
		instanceMethod2();
	}
}
