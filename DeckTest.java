package f_OOP2;

import java.util.Arrays;

public class DeckTest { // extends Object가 숨어 있음
	public static void main(String[] args) {
		Card c = new Card(1,1);
		System.out.println(c); //SPADE,A가 출력					c.toSting()이 생략된 것.
		Card c1 = new Card(2,5);
		System.out.println(c1); // CLOVER,5가 출력
		
		Deck z = new Deck();
		System.out.println(Arrays.toString(z.c));
		System.out.println(z.pick(51));
		System.out.println(z.pick());
		z.shuffle();
		System.out.println(Arrays.toString(z.c));
		z.shuffle(2);
		System.out.println(Arrays.toString(z.c));
	}
}


class Card{
	static final int KIND_MAX = 4; // 카드 무늬수
	static final int NUM_MAX = 13; // 무늬별 카드수
	
	static final int SPADE = 1; // 반드시 동일해야 하는 것들을 상수로
	static final int CLOVER = 2;
	static final int DIAMOND = 3;
	static final int HEART = 4;	
	
	int kind;
	int number;
	
	Card(int kind, int number){
		this.kind = kind;
		this.number = number;
	}

	@Override
	public String toString() {	// 대상의 주소를 반환해야 할 때, 리턴해줄 값을 바꿔줄 수 있다. 주소가 아니라 값을 찍고 싶을 때 이렇게 하면 된다.
		
		String kind = ""; // 위 kind값에 따라 값이 바뀌어야 한다.
		String number= ""; // 1이면  A, 10이면 J, 
		
		switch(this.kind){
		case 1:
			kind = "SPADE";
			break;
		case 2:
			kind = "CLOVER";
			break;
		case 3:
			kind = "DIAMOND";
			break;
		default:
			kind = "HEART";
			break;
		}
		
		switch(this.number){
		case 1:
			number = "A";
			break;
		case 11:
			number = "J";
			break;
		case 12:
			number = "Q";
			break;
		case 13:
			number = "K";
			break;
		default:
			number = ""+this.number; // number +=this.
			break;
		}
		
		return kind+","+number;
	}
}

class Deck{ // 카드 52장을 묶어 하나의 덱 만들기
	//1. 카드 수량을 저장할 수 있는 변수 CARD_NUM을 선언 및 초기화해주세요
	//	 단, 초기화시 Card클래스의 상수를 이용해주세요
	final int CARD_NUM = Card.KIND_MAX * Card.NUM_MAX;
	
	//2. 카드 52장을 저장할 수 있는 변수 c를 선언 및 생성해주세요
	//	 단, c의 크기는 1에서 만든 변수를 이용해주세요
	Card[] c = new Card[CARD_NUM];
	
	//3. 기본 생성자를 만들어주세요
	//	 생성자 내부에서 수행될 문장은 변수 c의 각 방을 1,1 ~ 1,13 ~ 4,13까지로 초기화해주세요		triangle참조
	Deck(){
		int k=0;
		for(int i=0;i<Card.KIND_MAX;i++){
			for(int j=0;j<Card.NUM_MAX;j++){
				c[k] = new Card(i+1,j+1);
				k++;
			}
		}
	}
	
	//4. c에서 카드 한 장을 뽑을 수 있는 메서드를 만들어주세요 pick
	//	 단, 인자값으로 받은 방의 카드를 반환해야 한다. (내가 5라고 하면 다섯번째 방의 카드를 반환)
	Card pick(int num){
		return c[num];
	}
	
	//5. c에서 랜덤한 방의 카드 한 장을 뽑을 수 있는 메서드를 만들어주세요 pick
	//	 단, 4번에서 만든 pick메서드를 '활용'해주세요 (번호를 주지 않고 랜덤한 방 번호의 카드를 반환)
	Card pick(){
		int r = (int)(Math.random()*c.length);
		return this.pick(r);
	}
	
	//6. 카드를 섞어주세요 shuffle
	//	 연습문제 형태로 섞어주세요(0번방과 랜덤방, 1번방과 랜덤방,... 51번방과 랜덤방)
	void shuffle(){
		for(int i=0;i<c.length;i++){
			int j = (int)(Math.random()*c.length);
			Card tmp;
			
			tmp = c[j];
			c[j] = c[i];
			c[i] = tmp;
		}
	}
	
	//7. 카드를 섞어주세요 shuffle
	//	 두 랜덤방을 뽑아 서로 바꾸는 방식으로
	//	 인자값으로 받은 숫자만큼 반복해주세요
	void shuffle(int y){
		for(int i=0;i<y;i++){
			int j = (int)(Math.random()*c.length);
			int k = (int)(Math.random()*c.length);
			Card tm;
			
			tm = c[j];
			c[j] = c[k];
			c[k] = tm;
		}
	}
	
	// 다 끝나면 main에 호출해 보고 JVM그려오기
}














