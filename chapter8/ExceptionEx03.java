package g_Exception;

public class ExceptionEx03 {
	public static void main(String[] args){
		
		method1();
		
	}
	
	static void method1(){ // 대개 (Controller-Service-DAO 중)Service영역쯤에서 처리한다
		
		try {
			method2(); // 위 fix법이 던지는 것, 아래 fix법이 try-catch로 바꾸는 것
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void method2() throws Exception{ // 예외 던지기. 여러 예외가 발생할 수 있다면 ,찍고 더 적어주면 된다
//		Exception e = new Exception();
//		throw e;
		throw new Exception("메서드 2번에서 발생");
	}
	// 예외는 콜스택 갯수만큼 쌓인다. 지금은 3개
	// '맨위'부터 찾다가 내가 만든/아는 파일명을 찾으면 거기가 실제 위치. 누르면 거기로 이동하니 거기부터 그 전단계 전단계로 찍고 디버그 돌리기.
	
	
}
