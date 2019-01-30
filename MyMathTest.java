package e_OOP;

public class MyMathTest {
	public static void main(String[] args) {
		
		MyMath.add1(); // 클래스변수라 인스턴스화가 필요없다
		
		int result = MyMath.add2();		// 반환값이 있으므로 이렇게 할 수 있다
		System.out.println(result);
		
		int result2 = MyMath.add3(50000); // 원하는 걸 줘야 걔한테 갈 수 있다
		System.out.println(result2);
		
		MyMath a = new MyMath();		// 인스턴스 메서드를 호출하기 위해 먼저 인스턴스화가 필요하다
		// 메서드 호출 시 주는 값 a를 인자값이라고 한다
		int result3 = a.add4(300, 500);
		System.out.println(result3);
		
		long result4 = a.multiply(3000, 3000, 3000);
		System.out.println(result4);
		
		float result5 = a.distance(1, 1, 2, 2);
		System.out.println(result5);
	}
}



class MyMath{
	
	static int one = 100;
	static int two = 200;
	
	// 매개변수 없고 반환값도 없다.
	// 클래스변수 one과 two의 값의 합계를 출력
	static void add1(){		//이렇게 클래스변수만 쓰는 녀석은 클래스 메서드로 만들 생각을 해야 한다
		int result = one + two;
		System.out.println(result);
	}
	
	// 매개변수 없고 
	// 클래스변수 one과 two의 값의 합계를 반환하는 메서드 add2
	static int add2(){
		int result = one + two;
		return result;
	}
	
	// 매개변수가 int형 하나
	// one과 two와 매개변수값의 합계를 반환하는 메서드 add3
	static int add3(int a){
		int result = one + two + a;
		return result;
	}
	
	// 매개변수는 int형 두개
	// 매개변수 두개의 합을 반환하는 인스턴스 메서드 add4
		int add4(int a, int b){
			int result = a + b;
			return result;
		}
		
	// 매개변수는 int형 세개
	// 매개변수들의 곱을 반환하는 인스턴스 메서드 multiply
	// 단, overflow를 고려하여 만들어주세요
		
		long multiply(int a, int b, int c){
			long result = (long)a * b * c;
			return result;
		}
		
	// 매개변수는 int형 네개 (x1,y1   x2,y2)
	// 두 점 사이의 거리를 소숫점 셋째자리에서 반올림하여 두번째 자리까지 반환하는 메서드를 만들어주세요
	// 단. 루트는 Math.sqrt()를 활용
	
	float distance(int x1, int y1, int x2, int y2){
		double result = Math.sqrt((x2 - x1)*(x2 - x1)+(y2 - y1)*(y2 - y1));
		float result2 = (int)(result*100+0.5)/100f;
		return result2;
	}
}
//		F2 눌렀을 때 뜨는 것 '반환받는 값 클래스명.메서드명(넣어야하는 값)'
//		결과값을 받아오면? 메서드는 값이 된다(MyMath.add2()는 곧 300)
//		매개변수는 지역변수의 일종. 메서드가 끝나면 사라진다.