package basic;
/*
우선순위를 따로 정하지 않으면 부모 쓰레드(메인 쓰레드 등)의 우선순위값(5)을 상속받는다

값을 비교할 때 그 차이가 크든 작든 관계없다. 누구의 우선순위가 높으냐 낮으냐만 따진다

프로그래머는 우선순위 값을 정해줄 뿐. 쓰레드 스케줄러가 모든 것을 관리한다

우선순위는 작동 전에 바꿀 수 있다(작동 중에는 X)

하지만 현재 예제에서는 우선순위를 설정한다 해도 큰 영향이 없다. 멀티코어 CPU의 발달로.

 */
public class ThreadTest08 {

	public static void main(String[] args) {
		// 쓰레드의 우선순위 연습
		Thread th1 = new ThreadTest1();
		Thread th2 = new ThreadTest2();
		
		System.out.println("th1의 현재 우선순위 : " + th1.getPriority());
		System.out.println("th2의 현재 우선순위 : " + th2.getPriority());
		
		// 우선순위 설정은 start()메서드 호출 전에 해야 한다.
		th1.setPriority(6);
		th2.setPriority(8);
		
		System.out.println("th1의 변경된 우선순위 : " + th1.getPriority());
		System.out.println("th2의 변경된 우선순위 : " + th2.getPriority());
		
		th1.start();
		th2.start();
	}

}

// 대문자를 출력하는 쓰레드
class ThreadTest1 extends Thread{
	@Override
	public void run() {
		for(char ch='A' ; ch <= 'Z' ; ch++){
			System.out.print(ch);

			for(long i=1L; i<=1_000_000_000L; i++){} // 시간 때우기
		}
	}
}

// 소문자를 출력하는 쓰레드
class ThreadTest2 extends Thread{
	@Override
	public void run() {
		for(char ch='a' ; ch <= 'z' ; ch++){
			System.out.print(ch);

			for(long i=1L; i<=1_000_000_000L; i++);
		}
	}
}