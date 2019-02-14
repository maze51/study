package f_OOP2;

public class InterfaceTest02 {
	public static void main(String[] args) {
		Fight f = new Fight();
		
		if(f instanceof Fight){
			System.out.println("f는 Fight의 인스턴스이다");
			Fight f2 = f; // 가능
		}
		
		if(f instanceof Unit2){
			System.out.println("f는 Unit2의 인스턴스이다");
			Unit2 u = f; // 업캐스팅
		}
		
		if(f instanceof Object){
			System.out.println("f는 Object의 인스턴스이다");
			Object u = f;
		}
		
		if(f instanceof Fightable){
			System.out.println("f는 Fightable인터페이스를 구현한 구현체이다");
			Fightable f2 = f; // 인터페이스의 다형성
		}
		
		if(f instanceof Movable){
			System.out.println("f는 Movable인터페이스를 구현한 구현체이다");
			Movable f2 = f;
			//이 때 f2는 fight의 im인 move만 호출할 수 있다
		}
		
		
		
	}
}

class Unit2{
	int x;
	int y;
	int currentHp;
}

interface Movable {
	void move(int x, int y);
}

interface Attackable {
	void attack(Unit2 u);
}

interface Fightable extends Movable, Attackable{ // 인터페이스는 인터페이스만 상속가능. 다중상속 가능
	
}

class Fight extends Unit2 implements Fightable{ // abstract가 붙었다면 인스턴스화 불가

	@Override
	public void move(int x, int y) { // 조상 클래스의 메서드를 자손클래스에서 오버라이딩할 때 접근제어자를 조상 클래스의 메서드보다 좁은 범위로 설정할 수 없다
		
	}

	@Override
	public void attack(Unit2 u) {
		
	}
	
	
}