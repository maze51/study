package e_OOP;

public class ProductTest {
	public static void main(String[] args) {
		System.out.println(Init.action); // 변수 로드 => 초기화블럭 20 => 100
		
		Init it = new Init(70); // 변수 로드 => 초기화블럭 => 생성자 10 => 200 => 70
		System.out.println(it.action2);
	}
}



class Init{
	
	static int action = 20;
	int action2 = 10;
	//new와 생성자 사이에 초기화블럭이 들어간다!
	
	int a = 5; //명시적 초기화
	
	Init(int action2){ //생성자를 이용한 초기화
		this.action2 = action2;			// 지역변수와 전역변수 구분!
	}
	
	static {	// 클래스초기화 블럭 - 클래스친구들이 먼저 올라가고 그 다음이 초기화 블럭				클래스변수의 변경은 이것 말고 방법이 (거의?) 없다.
		action = 100;
	}
	
	{			// 인스턴스초기화 블럭			 인스턴스초기화 블럭을 사용하는 이유? 생성자가 공식 등으로 대단히 복잡할 경우, 생성자는 옵션만 조정하고 공통적으로 사용하는 부분이 있을 경우
		action2 = 200;
	}
	
} // 초기화블럭은 일단 슥 보고 넘기기