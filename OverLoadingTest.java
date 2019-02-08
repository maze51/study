package ch6ex;

public class OverLoadingTest {
	public static void main(String[] args) {
		MyMath3 mm = new MyMath3(); // MyMath3 클래스의 인스턴스를 참조하기 위한 참조변수 mm선언. new연산자로 MyMath3클래스의 기본 생성자를 로드해 클래스의 인스턴스를 생성하고, 인스턴스의 주소를 참조변수에 저장
		System.out.println(mm.add(3, 3)); // 참조변수 mm을 통해 인스턴스메서드 add(int a, int b) 호출. 인자로 3, 3을 지정. 메서드 반환값을 출력.
		System.out.println(mm.add(3L, 3)); // 참조변수 mm을 통해 인스턴스메서드 add(long a, int b) 호출. 인자로 3L, 3을 지정. 메서드 반환값을 출력.
		System.out.println(mm.add(3, 3L)); // 참조변수 mm을 통해 인스턴스메서드 add(int a, long b) 호출. 인자로 3, 3L을 지정. 메서드 반환값을 출력.
		System.out.println(mm.add(3L, 3L)); // 참조변수 mm을 통해 인스턴스메서드 add(long a, long b) 호출. 인자로 3L, 3L을 지정. 메서드 반환값을 출력.
		
		int[] a = {100, 200, 300}; // int타입 변수를 갖는 배열 a 선언. 선언과 함께 100, 200, 300의 값을 갖는 길이 3의 배열을 생성
		System.out.println(mm.add(a)); // 참조변수 mm을 통해 인스턴스메서드 add(int[] a) 호출. 인자로 a를 지정. 메서드에서 반환된 값을 출력.
	}
}

class MyMath3 {
	int add(int a, int b) { // 메서드명 add, 인자가 대입될 매개변수 int a, int b, 반환타입 int, { "add(int a, int b) "를 출력하고 a+b를 return }하는 인스턴스메서드 선언 및 구현
		System.out.print("add(int a, int b) ");
		return a + b;
	}
	
	long add(int a, long b) { // 메서드명 add, 인자가 대입될 매개변수 int a, long b, 반환타입 long, { "add(int a, long b) "를 출력하고 a+b를 return }하는 인스턴스메서드 선언 및 구현
		System.out.print("add(int a, long b) ");
		return a + b;
	}
	
	long add(long a, int b) { // 메서드명 add, 인자가 대입될 매개변수 long a, int b, 반환타입 long, { "add(long a, int b) "를 출력하고 a+b를 return }하는 인스턴스메서드 선언 및 구현
		System.out.print("add(long a, int b) ");
		return a + b;
	}
	
	long add(long a, long b) { // 메서드명 add, 인자가 대입될 매개변수 long a, long b, 반환타입 long, { "add(long a, long b) "를 출력하고 a+b를 return }하는 인스턴스메서드 선언 및 구현
		System.out.print("add(long a, long b) ");
		return a + b;
	}
	
	int add(int[] a) { // 메서드명 add, 매개변수 int[] a, 반환타입 int, {"add(int[] a) " 출력 후 지역변수 int result를 선언 및 0으로 초기화,
					   // 배열 int[] a의 모든 값을 더해 result에 저장하는 반복문 설정 후 result를 return} 하는 인스턴스메서드 선언 및 구현
		System.out.print("add(int[] a) ");
		int result = 0;
		for(int i=0;i<a.length;i++) {
			result += a[i];
		}
		return result;
	}
}