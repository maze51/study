/*
package g_Exception;

public class ExceptionEx02 {
	public static void main(String[] args) {
		
		Exception e = new Exception("ㅇㅇㅇ");
		
		try {
			throw e;
		} catch (Exception e1) {
			e1.printStackTrace(); // Exception 내용을 그대로 출력해주는 역할
			System.out.println(e1.getMessage()); // 안의 이유만 가져오는 역할
		}
		
		System.out.println("아니야");
		
		throw new RuntimeException(); // try-catch를 강제하진 않지만, 에러났으므로 뒷문장 실행 x
		System.out.println();
		
		// 컴파일러가 예외처리를 강제하지 않는 경우
		// 1. RuntimeException클래스들
		// 2. Error와 그 자손들
		// 이런 것들을 'unchecked 예외'라고 한다. 나머지는 checked 예외
	}
}
*/