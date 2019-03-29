package basic;

public class LambdaTest02 {

	public static void main(String[] args) {
		// 람다식을 사용하지 않은 경우
		/*
		LambdaTestInterface1 t1 = new LambdaTestInterface1() {
			
			@Override
			public void test() {
				System.out.println("안녕하세요");
				
			}
		};
		*/
		
		LambdaTestInterface1 t1 = () -> {
			System.out.println("안녕하세요 람다식입니다.");
		};
		t1.test();
		
		// 처리할 실행문이 1개일 경우 중괄호를 생략할 수 있다.
		LambdaTestInterface1 t2 = () -> System.out.println("반가워요");
		
		t2.test();
		
		System.out.println("------------------------------------");
		
		LambdaTestInterface2 t3 = 
				(int a) -> {
					int result = a + 30;
					System.out.println("t3 result = " + result);
				};
		t3.test(8);		
		
		// 매개변수의 '자료형이름' 생략
		LambdaTestInterface2 t4 = 
				(z) -> {
					int result = z * 30;
					System.out.println("t4 result = " + result);
				};
		
		t4.test(12);
		
		// 매개변수가 1개일 경우 '괄호' 생략
		LambdaTestInterface2 t5 = 
				g -> {
					int result = g - 30;
					System.out.println("t5 result = " + result);
				};
		
		t5.test(100);
		
		System.out.println("------------------------------------");
		
		LambdaTestInterface3 t6 =
				(int x, int y) -> {
					int r = x + y;
					return r;
				};
				
		int k = t6.test(11, 32);
		System.out.println("t6 k = " + k);
		
		// 매개변수의 자료형이름 생략
		LambdaTestInterface3 t7 =
				(x, y) -> {
					return x - y;
				};
				
		k = t7.test(56, 22);
		System.out.println("t7 k = " + k);
		
		// 중괄호도 없이 식만 있다면 return이 생략된 것이라 생각하면 ok
		LambdaTestInterface3 t8 =
				(x, y) -> x * y;
				
		k = t8.test(7, 3);
		System.out.println("t8 k = " + k);
				
	}

}
