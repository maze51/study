package ch6ex;

class MyMath2 {
	long a; // 매개변수 없는 메서드 수행 시 값을 저장할 인스턴스변수 a, b를 선언
	long b;
	
	long add(){  // 메서드명 add, 매개변수 없음, 반환타입 long {a+b를 return}하는 인스턴스메서드 선언 및 구현
		return a + b;
	}
	long subtract(){ // 메서드명 subtract, 매개변수 없음, 반환타입 long {a-b를 return}하는 인스턴스메서드 선언 및 구현
		return a - b;
	}
	long multiply(){ // 메서드명 multiply, 매개변수 없음, 반환타입 long {a*b를 return}하는 인스턴스메서드 선언 및 구현
		return a * b;
	}
	double divide(){ // 메서드명 divide, 매개변수 없음, 반환타입 double {a/b를 return}하는 인스턴스메서드 선언 및 구현
		return a / b;
	}
	
	static long add(long a, long b) { // 메서드명 add, 매개변수 long a, long b, 반환타입 long {a+b를 return}하는 클래스메서드 선언 및 구현
		return a + b;
	}
	static long subtract(long a, long b) { // 메서드명 subtract, 매개변수 long a, long b, 반환타입 long {a-b를 return}하는 클래스메서드 선언 및 구현
		return a - b;
	}
	static long multiply(long a, long b) { // 메서드명 multiply, 매개변수 long a, long b, 반환타입 long {a*b를 return}하는 클래스메서드 선언 및 구현
		return a * b;
	}
	static double divide(double a, double b) { // 메서드명 divide, 매개변수 double a, double b, 반환타입 double {a/b를 return}하는 클래스메서드 선언 및 구현
		return a / b;
	}
}

public class MyMathTest2 {
	public static void main(String[] args) {
		System.out.println(MyMath2.add(200L, 100L)); // 200L, 100L을 인자로 클래스메서드 add를 호출하고 그 결과값을 출력. 300L이 출력됨
		System.out.println(MyMath2.subtract(200L, 100L)); // 200L, 100L을 인자로 클래스메서드 subtract를 호출하고 그 결과값을 출력. 100L이 출력됨
		System.out.println(MyMath2.multiply(200L, 100L)); // 200L, 100L을 인자로 클래스메서드 multiply를 호출하고 그 결과값을 출력. 20000L이 출력됨
		System.out.println(MyMath2.divide(200.0, 100.0)); // 200.0, 100.0을 인자로 클래스메서드 divide를 호출하고 그 결과값을 출력. 2.0이 출력됨
		
		MyMath2 mm = new MyMath2(); // MyMath2 클래스의 인스턴스를 참조하기 위한 참조변수 mm선언. new연산자로 MyMath2클래스의 기본 생성자를 로드해 클래스의 인스턴스를 생성하고, 인스턴스의 주소를 참조변수에 저장
		mm.a = 200L; // 참조변수 mm을 통해 인스턴스변수 a값을 200L로 변경한다
		mm.b = 100L; // 참조변수 mm을 통해 인스턴스변수 b값을 100L로 변경한다
		
		System.out.println(mm.add()); // 참조변수 mm을 통해 인스턴스메서드 add를 호출하고 그 결과값을 출력. 300L이 출력됨
		System.out.println(mm.subtract()); // 참조변수 mm을 통해 인스턴스메서드 subtract를 호출하고 그 결과값을 출력. 100L이 출력됨
		System.out.println(mm.multiply()); // 참조변수 mm을 통해 인스턴스메서드 multiply를 호출하고 그 결과값을 출력. 20000L이 출력됨
		System.out.println(mm.divide()); // 참조변수 mm을 통해 인스턴스메서드 divide를 호출하고 그 결과값을 출력. 2.0이 출력됨
	}
}
