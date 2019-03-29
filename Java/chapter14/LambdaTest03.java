package basic;

public class LambdaTest03 {
	
	// 메서드의 매개변수는 그 메서드 내에서의 '지역변수'이다.
	public void myMethod(final int temp) {
		final int localVar = 40;
		
		//temp = 200;
		//localVar = 1;
		
		// 람다식
		LambdaTestInterface1 lt = () -> {
			// 람다식 안에서 지역변수 사용하기
			// 람다식(익명구현체) 내에서 사용되는 지역변수는 final이어야 한다.
			// (1.8이후로는 코딩하지 않아도 알아서 붙여준다. 하지만 값을 변경하고자 한다면 오류 발생)
			// 오류 방지를 위해 메서드 안에서 final을 명시해 주는 것이 좋다
			System.out.println("temp => " + temp);
			System.out.println("localVar => " + localVar);
		};
		
		lt.test();
	}
	
	public static void main(String[] args) {
		new LambdaTest03().myMethod(100);
	}

}
