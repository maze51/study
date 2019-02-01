package e_OOP;

public class CarTest {
	public static void main(String[] args) {
		Car c = new Car();		// 보이지 않지만 저장(컴파일) 순간 생성자가 생성됨. 안에 생성자가 하나도 없을 때. 하나라도 있으면 자동 생성자는 생기지 않는다.
		// 여기 입장에서는 white로 초기화해서 온 것 처럼 보임. 생성자는 인스턴스변수를 초기화하려는 목적으로도 활용됨
		
		Car c1 = new Car("white", "auto", 2);		// 오버로딩 활용. 변수를 일일이 넣을 필요 없이 한방에 끝낼 수 있다. + 실행할 문장이 줄어 속도가 빨라진다.
		System.out.println(c1.color);
		Car c2 = new Car("red", "auto");
		
//		for(int i=0;i<20;i++){					// 필요할 때 생성자를 불러서 값만 바꿔준다
//			Car e = new Car("white","auto",2);
//		}
		
	}
}

class Car{
	String color;									// 여기에 바로 black을 넣으면 안 되는 이유? 값을 바꿔줄 때 일일이 넣는 작업이 다시 필요해짐
	String gearType;								// 얘네들은 인스턴스 내에 쌓인다
	int door;
	
	Car(){ // 기본생성자
		color = "white";
		gearType = "stick";
		door = 4;
	}
	
	Car(String color, String gearType, int door){ // 매개변수가 있는 생성자
		this.color = color;								// 단지 지역변수 color에 넣어줬을 뿐 -> this(이 인스턴스에)를 붙여주면 인스턴스변수 color를 가리키게 한다
		this.gearType = gearType;						// 콜스택에는 (String color, String gearType, int door), 
		this.door = door;
	}
	
	
	Car(String color, String gearType){ // 옵션 2개만 달고싶다. 도어는 빼고
		this();	//Car()호출한 것과 동일. Car()로 먼저 가서 white stick 4를 들고 여기로 온다. 여기서 괄호안에 매개변수 str str int 넣어주면 두번째 생성자도 호출가능.	
		// 생성자는 인스턴스화할 때만 호출할 수 있다. 하지만 이걸 쓰면 생성자 내에서 다른 생성자를 호출할 수 있다.
		// 생성자는 필요할 때 쓸 수 없다. 그래서 생성자를 두 번 거쳐올 때 this()를 사용한다. 생성자는 메서드와 함께 로드됨.
		this.color = color;
		this.gearType = gearType; // 문짝은 4개 유지하면서, 색상과 기어는 red auto로 수정할 수 있다
	}
}