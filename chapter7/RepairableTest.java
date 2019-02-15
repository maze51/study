package f_OOP2;

public class RepairableTest {
	public static void main(String[] args) {
		// 참조변수 tank로 Tank클래스의 인스턴스 생성
		Tank tank = new Tank();
		// 참조변수 dropship으로 Dropship클래스의 인스턴스 생성
		Dropship dropship = new Dropship();
		// 참조변수 marine으로 Marine클래스의 인스턴스 생성
		Marine marine = new Marine();
		// 참조변수 scv로 SCV클래스의 인스턴스 생성
		SCV scv = new SCV();
		// 참조변수 scv를 통해 repair메서드 호출. 인자값은 tank
		scv.repair(tank);
		// 참조변수 scv를 통해 repair메서드 호출. 인자값은 dropship
		scv.repair(dropship);
		// 참조변수 scv를 통해 repair메서드 호출. 인자값은 marine
		//		scv.repair(marine);
	}


}

// Repairable 인터페이스 만들기. 구현부 내용은 없음
interface Repairable {}

// Unit 클래스 생성. int형 지역변수 hitpoint, int형 상수 MAX_HP를 선언한다.
class Unit {
	int hitpoint;
	final int MAX_HP;
	// int hp를 인자값으로 하는 Unit 클래스의 생성자 만들기. 구현부에서 입력받은 hp값을 이용해 MAX_HP를 초기화한다.
	Unit(int hp){
		MAX_HP = hp;
	}
}

// Unit 클래스의 자손 클래스인 GroundUnit 클래스 생성
class GroundUnit extends Unit {
	// int hp를 인자값으로 하는 GroundUnit 클래스의 생성자 만들기. 
	// 조상클래스 Unit의 생성자를 호출해 구현부에서 입력받은 hp값을 이용, MAX_HP를 초기화한다.
	GroundUnit(int hp){
		super(hp);
	}
}


// Unit 클래스의 자손 클래스인 AirUnit 클래스 생성
class AirUnit extends Unit{
	// int hp를 인자값으로 하는 AirUnit 클래스의 생성자 만들기.
	// 조상클래스 Unit의 생성자를 호출해 구현부에서 입력받은 hp값을 이용, MAX_HP를 초기화한다.
	AirUnit(int hp){
		super(hp);
	}
}

// GroundUnit 클래스의 자손 클래스이자 Repairable 인터페이스를 구현한 Tank 클래스 생성
class Tank extends GroundUnit implements Repairable {
	// 매개변수 없는 Tank클래스의 생성자 만들기. 인자값 150으로 GroundUnit 클래스 생성자를 호출하고
	// GroundUnit 클래스 생성자가 다시 Unit 클래스의 생성자를 호출해 MAX_HP를 150으로 초기화한다
	// MAX_HP값으로 hitpoint를 초기화한다
	Tank(){
		super(150);
		hitpoint = MAX_HP;
	}


	// Object클래스로부터 toString()메서드를 오버라이딩해 문자열 "Tank"를 반환하도록 한다
	public String toString(){
		return "Tank";
	}
}
// AirUnit 클래스의 자손 클래스이자 Repairable 인터페이스를 구현한 Dropship 클래스 생성
class Dropship extends AirUnit implements Repairable{
	// 매개변수 없는 Dropship클래스의 생성자 만들기. 인자값 125로 AirUnit 클래스 생성자를 호출하고
	// AirUnit 클래스 생성자가 다시 Unit 클래스의 생성자를 호출해 MAX_HP를 125로 초기화한다
	// MAX_HP값으로 hitpoint를 초기화한다
	Dropship(){
		super(125);
		hitpoint = MAX_HP;
	}


	// Object클래스로부터 toString()메서드를 오버라이딩해 문자열 "Dropship"을 반환하도록 한다
	public String toString(){
		return "Dropship";
	}
}
// GroundUnit 클래스의 자손 클래스인 Marine 클래스 생성
class Marine extends GroundUnit {
	// 매개변수 없는 Marine클래스의 생성자 만들기. 인자값 60으로 GroundUnit 클래스 생성자를 호출하고
	// GroundUnit 클래스 생성자가 다시 Unit 클래스의 생성자를 호출해 MAX_HP를 60으로 초기화한다
	// MAX_HP값으로 hitpoint를 초기화한다
	Marine(){
		super(60);
		hitpoint = MAX_HP;
	}
}

// GroundUnit 클래스의 자손 클래스이자 Repairable 인터페이스를 구현한 SCV 클래스 생성
class SCV extends GroundUnit implements Repairable {
	// 매개변수 없는 SCV클래스의 생성자 만들기. 인자값 60으로 GroundUnit 클래스 생성자를 호출하고
	// GroundUnit 클래스 생성자가 다시 Unit 클래스의 생성자를 호출해 MAX_HP를 60으로 초기화한다
	// MAX_HP값으로 hitpoint를 초기화한다
	SCV(){
		super(60);
		hitpoint = MAX_HP;

	}
	// Repairable형 참조변수 r을 인자값으로 하는 메서드 repair생성. 반환타입은 void
	void repair(Repairable r){
		// 참조변수 r이 참조하고 있는 인스턴스의 타입이 Unit으로 형변환 가능하다면 true를 반환하는 if문 만들기
		if(r instanceof Unit){  // true가 나오는 이유?
			// 조건식이 true일 때 참조변수 r을 Unit형으로 형변환해 Unit형 참조변수 u에 저장한다.
			Unit u = (Unit)r;
			// 그리고 참조변수 u로 접근한 주소의 hitPoint값이 MAX_HP값과 같지 않다면
			// hitPoint를 1씩 증가시키고 u.hitPoint=u.MAX_HP일 때 식에서 빠져나가는 반복문을 만든다.
			while(u.hitpoint != MAX_HP){
				u.hitpoint++;
			}
			// 반복문이 종료되면 출력문을 만든다.
			// 출력문: 참조변수 u의 toString()메서드를 호출하고 +"의 수리가 끝났습니다."
			System.out.println(u.toString() + "의 수리가 끝났습니다.");
		}
	}
}
