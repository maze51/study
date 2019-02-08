package ch6ex;

class Data {
	int x; // 결과값을 저장할 int 타입의 인스턴스변수 x 선언 
}

public class ReferenceReturnEx {
	public static void main(String[] args) {
		Data d = new Data(); // Data클래스의 객체를 참조하기 위한 참조변수 d 선언. new연산자로 Data클래스의 기본 생성자를 로드해 클래스의 인스턴스를 생성하고, 인스턴스의 주소를 참조변수에 저장
		d.x = 10; // 참조변수 d를 통해 인스턴스변수 x의 값을 10으로 변경
		
		Data d2 = copy(d); // 클래스메서드 copy를 호출. 같은 클래스 안에서 클래스메서드 호출이라 클래스명 생략 가능.
						   // 인자값으로 참조변수 d의 주소 지정. 반환된 결과값을 Data 타입의 참조변수 d2에 저장
		
		System.out.println(d.x); // 참조변수 d의 x값을 출력. 10이 출력됨
		System.out.println(d2.x); // 참조변수 d2의 x값을 출력. 10이 출력됨
	}
	
	static Data copy(Data d) { // 메서드명 copy, 인자가 대입될 매개변수 Data d, 반환타입 Data인 클래스메서드 선언
		Data tmp = new Data(); // Data클래스의 객체를 참조하기 위한 참조변수 tmp 선언. new연산자로 Data클래스의 기본 생성자를 로드해 클래스의 인스턴스를 생성하고, 인스턴스의 주소를 참조변수에 저장
		tmp.x = d.x; // 참조변수 d를 통해 얻은 x값을 참조변수 tmp를 통해 얻은 x값에 대입한다
		return tmp; // tmp값을 반환한다
	}
}
