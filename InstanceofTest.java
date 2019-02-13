package f_OOP2;

public class InstanceofTest {
	public static void main(String[] args) {
		//업캐스팅됐을 때 내가 사용하는 것이 뭘 쫒아가는지 알기 위한 부분
		Car2 c = new FireCar2();
		System.out.println(c.a); // 참조변수의 타입 Car2의 영향을 받아 10
		System.out.println(c.b); // 참조변수의 타입 Car2의 영향을 받아 20
		c.method(); // 따로 자기꺼 쓴다고 메서드를 오버라이드했으니 자식것이 호출됨
		
		if(c instanceof FireCar2){
			System.out.println("c는 FireCar2의 인스턴스이다");
			FireCar2 fc2 = (FireCar2)c; // true니까 이게 가능하다. 단 이때는 형변환이 필요 down-casting
		}
		
		if(c instanceof Car2){
			System.out.println("c는 Car2의 인스턴스이다");
			Car2 fc2 = c; // up-casting
		}
		
		if(c instanceof Object){
			System.out.println("c는 Object의 인스턴스이다");
			Object fc2 = c;
		}
		
		Car2 c2 = new Car2();
		if(c2 instanceof FireCar2){ // false
			System.out.println("c2는 FireCar2의 인스턴스이다");
		}
		if(c2 instanceof Car2){ // true
			System.out.println("c2는 Car2의 인스턴스이다");
			Car2 ccc = c2;
		}
		if(c2 instanceof Object){ // true
			System.out.println("c2는 Object의 인스턴스이다");
			Object ccc = c2; // up-casting
		}
	}
}


class Car2{
	static int a=10;
	int b=20;
	
	void method(){
		System.out.println("Car2의 메서드");
	}
}

class FireCar2 extends Car2{
	static int c=30;
	int b = 40;
	
	@Override
	void method(){
		System.out.println("FireCar2의 메서드");
	}
}