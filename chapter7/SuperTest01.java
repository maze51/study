package f_OOP2;

public class SuperTest01 {
	public static void main(String[] args) {
		Child2 c2 = new Child2();
	}
}

class Parent2{
	
	int x = 10;
	int y = 20;
	
	int getAdd(){
		return x + y;
	}
	
}

class Child2 extends Parent2 {
	int x = 30;
	
	int getAdd(){
		int x = 40;
		System.out.println(x); // 40
		System.out.println(this.x); // 30
		System.out.println(super.x); // 10 부모쪽을 바로 지정할 순 없음. super는 자식의 인스턴스에서만 사용가능
		
		int result = getAdd(); // 30
		return 50;
	}
}