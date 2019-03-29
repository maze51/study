package singleton;

public class SingletonTest {

	public static void main(String[] args) {
		// MySingle test1 = new MySingle(); // new 명령은 사용 불가
		
		MySingle test2 = MySingle.getInstance(); // getInstance()메서드를 이용해서 객체 생성
		MySingle test3 = MySingle.getInstance();
		
		System.out.println("test2 = " + test2);
		System.out.println("test3 = " + test3); // 같은 주소를 가리킨다
		
		System.out.println(test2 == test3);
		
		test2.displayTest();
	}

}
