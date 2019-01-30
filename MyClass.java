package e_OOP;

public class MyClass {
	public static void main(String[] args) {
		
		Com c = new Com();
		
		c.cpu = "Core i7";
		c.clock = 3600;
		System.out.println(c.cpu);
		
		c.power();
		System.out.println(c.power);

		c.browser = "구글 크롬";
		System.out.println(c.browser);
		c.changeBrowser();
		System.out.println(c.browser);

		System.out.println(c.clock);
		c.overClock();
		System.out.println(c.clock);

		c.power();


	}
}

class Com {
	String cpu;
	int clock;
	boolean power;
	String browser;

	void overClock(){
		clock = 4000;
	}

	void power(){
		power = !power;
	}

	void changeBrowser(){
		browser = "인터넷 익스플로러";
	}


}

//이 때 여러 PC의 CPU와 클럭이 모두 같은가? 모두 같은 걸 쓰는가?
//그렇지 않다면 cv, cm이 아닌 iv, im으로 지정해야 한다.
//변수 및 메서드 에는 의미가 있어야

/*
package e_OOP;

public class MyClass {
	public static void main(String[] args) {
		Com.cpu = "Core i7";
		Com.clock = 3600;
		System.out.println(Com.cpu);
		Com c = new Com();
		c.power();
		System.out.println(c.power);

		c.browser = "구글 크롬";
		System.out.println(c.browser);
		c.changeBrowser();
		System.out.println(c.browser);

		System.out.println(Com.clock);
		Com.overClock();
		System.out.println(Com.clock);

		c.power();


	}
}

class Com {
	static String cpu;
	static int clock;
	boolean power;
	String browser;

	static void overClock(){
		clock = 4000;
	}

	void power(){
		power = !power;
	}

	void changeBrowser(){
		browser = "인터넷 익스플로러";
	}


}
*/



/*
	변수로 뽑을 것
	boolean power
	String cpu
	String browser
	int clock			3600 4000

	메서드로 뽑을 것

	static void changeClock(){
		clock = 4000;
	}

	void power(){
		power = !power;
	}

	void changeBrowser(){
		browser = "Internet Explorer";
	}

	주제: PC 사용

	명사적 의미를 담고 있는 것
	PC본체, 모니터, 키보드, 마우스, 유무선공유기, 웹 브라우저, 운영체제

	1. 역할에 따라 분리
	켜고 끈다
	입력한다
	실행한다

	2. 타입 정하기
	boolean[] onOff = new boolean[]{
	String[] input = new String[]{
	String[] run = new String[]{

	동사적 의미
	전원을 켠다, 전원을 끈다, 키보드를 친다, 마우스를 클릭한다, 운영체제를 설치한다, 브라우저를 설치한다, WIFI에 접속한다, 브라우저를 켠다
	void connInternet(boolean conn){
		System.out.println(
	}
 */



