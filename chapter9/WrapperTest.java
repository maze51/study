package i_javaLang;

public class WrapperTest {
	public static void main(String[] args) {
		
		Integer i1 = new Integer(100); // int의 wrapper 클래스
		Integer i2 = new Integer(100);
		
		System.out.println(i1 == i2);
		System.out.println(i1.equals(i2)); // wrapper클래스에 equals가 override되어있다
		
		System.out.println(i1); // wrapper클래스에 toString이 override되어있다
		
		// wrapper클래스의 특징
		// 오토박싱(자동으로 박스를 만들어 준다)
		Integer i4 = 100;
		int i3 = i1;
		System.out.println(100 == i1);
		
		
		int i5 = new Integer(200); // 언박싱(박스를 자동으로 풀어준다). 다 치우고 값만 꺼내서 기본형에 넣어준 것.
		
		
		Object[] a = new Object[3];
		a[0] = 20; // 오토박싱(Java에서 자동으로 이뤄진다)
		
		int i6 = (int)a[0]; // 다운캐스팅. 언박싱
		
		
		
		
		//1. 최대, 최소값
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		System.out.println(Integer.SIZE); // bit수
		
		//BYTES => byte수	(JDK 1.8이후)
		// Integer.BYTES => 4
		
		
		
		
	}
}
