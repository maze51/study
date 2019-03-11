package i_javaLang;

import java.util.Arrays;

public class GetClassTest {
	public static void main(String[] args) throws ClassNotFoundException {
		Card c1 = new Card();
		Class cc = c1.getClass();
		
		System.out.println(Arrays.toString(cc.getInterfaces()));
		
		Class cc2 = Card.class; // Card 리터럴에서 읽어오기
		//객체는 생성되지 않지만 객체의 내용은 싹 얻어온다
		System.out.println(Arrays.toString(cc2.getInterfaces()));
		
		Class cc3 = Class.forName("i_javaLang.Card"); // getName처럼 패키지명+클래스명을 함께 가져와야 한다.
		System.out.println(Arrays.toString(cc3.getInterfaces()));
	}
}
