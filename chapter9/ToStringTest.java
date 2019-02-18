package i_javaLang;

import java.io.Serializable;

public class ToStringTest {
	public static void main(String[] args) {
		//3. Card의 객체를 만들어주세요
		// 단 매개변수가 2개인 생성자를 이용하여 DIAMOND,3으로 초기화해 주세요
		
		Card c = new Card("DIAMOND",3);
		System.out.println(c);
	}
}

class Card implements Serializable{
	String kind;
	int number;
	
	//1. 매개변수가 2개인 생성자를 만들어주세요
	Card(String kind, int number) {
		this.kind = kind;
		this.number = number;
	}
	//2. 기본생성자를 만들어주세요
	// 단 매개변수가 2개인 생성자를 이용하여 SPADE, 1로 초기화해주세요
	
	Card(){
		this("SPADE",1);
	}

	@Override
	public String toString() {
		return "Card [kind=" + kind + ", number=" + number + "]";
	}
}