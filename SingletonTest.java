package f_OOP2;

public class SingletonTest {
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		System.out.println(s1.a);
		Singleton s2 = Singleton.getInstance();
		System.out.println(s2.a);
		
		s1.a = 20;
		System.out.println(s1.a);
		System.out.println(s2.a); // 주소를 공유하게 만든 것
		
		
	}
}


class Singleton{
	int a;
	private static Singleton s; // Singleton의 객체 생성용. static메서드에서 사용할 것이므로 static을 붙여준다
	
	private Singleton() {
		
	}
	
	static Singleton getInstance(){ // Singleton타입을 반환
		if(s == null){
			s = new Singleton();
		}
		return s; // 두번째는 null이 아니므로 s의 주소를 그대로 가져간다. 결과적으로 s의 주소가 s1과 s2, 클래스변수 s 세 군데를 가리키게 된다.
	} // 인스턴스를 하나만 만들어서 공유하는 방식. 
	
	
}