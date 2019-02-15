package g_Exception;

public class ExceptionBasic {
	public static void main(String[] args) {
		/*
		1. 프로그램 오류
		  - 프로그램이 실행 중 어떤 원인에 의해서 오작동하거나 비정상적으로 종료되는 경우
		  - 컴파일 에러 : 컴파일중에 발생하는 에러
		  - 런타임 에러 : 실행중에 발생하는 에러
		  - 에러는 프로그램 코드를 수정해서 수습될 수 없는 심각한 오류
		   	예외는 프로그램 코드를 수정하여 수습할 수 있는 경미한 오류
		   	
		2. RuntimeException클래스와 그 외 클래스
		  - 모든 예외의 최고조상은 Exception이다
		  - RuntimeException클래스들은 주로 프로그래머의 실수에 의해 발생할 수 있는 예외
		  	ex) IndexOutOfBoundsException, NullPointException...
		  - (그 외)Exception클래스들은 주로 외부의 영향으로 발생할 수 있는 예외
		  	ex) FileNotFoundException, ClassNotFoundException...
		  	
		3. 예외처리(try-catch)
		  - 프로그램 실행 시 발생할 수 있는 예외의 발생에 대비한 코드를 작성하는 것
		  - 작성방법
		  	try{
		  		예외가 발생할 가능성이 있는 문장
		  	} catch(Exception e){ // catch할 Exception의 종류를 지정. 최상위 Exception만 적어도 무방
		  		예외가 발생하였을 때 처리할 문장
		  	} catch(Exception2 e){
		  		예외가 발생하였을 때 처리할 문장
		  	}
		  - 발생한 예외의 종류와 일치하는 단 한개의 catch블럭만 수행한다.
		  
		4. 예외 발생시키기
		  - 연산자 new를 이용해서 발생시키려는 예외 클래스의 객체를 만든다. (마치 Scanner처럼)
		  	Exception e = new Exception();
		  - 키워드 throw를 이용해서 발생시키면 된다.
		  	throw e;
		  	
		  	한줄로? throw new Exception();
		  
		5. 예외 던지기 (throws Exception)
		  - 메서드 예외 선언하기
		  	: 메서드의 선언부에 키워드 'throws'를 사용해서 메서드 내에서 발생할 수 있는 예외를 모두 적어주면 된다.
		  	: 처리는 해당 메서드를 호출한 쪽에서 해 줘야 한다.
		
		6. finally
		  - try-catch 블럭의 예외발생에 상관없이 실행되어야 할 코드를 포함한.
		  
		  try {
		  	예외 발생 가능성이 있는 문장			// 예외가 발생했다면 다음 문장은 수행하지 않는다
		  	int a;
		  } catch(Exception1 e) {
		  	예외 발생시 처리할 문장
		  } finally {
		  	예외 발생에 상관없이 실행될 코드
		  }
		
		- try -> catch -> finally 예외 발생시
		  try -> finally 예외 발생 x시
		 */
		
		
		
		
		
		
		
		
	}
}
