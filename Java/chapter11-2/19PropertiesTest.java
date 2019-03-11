package basic;

import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) {
	/*
		Properties는 Map에 비해 축소된 기능의 객체라고 할 수 있다.
		Map은 모든 형태의 데이터를 key와 value의 값으로 사용할 수 있지만
		Properties는 key와 value값으로 String만 사용할 수 있다.
		
		Map은 put()메서드, get()메서드를 이용하여 데이터를 입출력하지만
		Properties는 setProperty(), getProperty()를 통해 입출력한다.
	 */
		Properties prop = new Properties(); // String만 사용 가능하니 제네릭 타입이 필요 없다.
		
		// 데이터 저장
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");
		
		// 값 읽기
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("name => " + name);
		System.out.println("tel => " + tel);
		System.out.println("addr => " + addr);
	}
}
