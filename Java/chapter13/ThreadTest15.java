package basic;
//간단한 경우에는 static변수를 만들어 공유해서 쓸 수 있지만
// 복잡해질 경우에는 다른 방법도 알고 있어야 한다
// 객체를 하나만 만들어 놓고, 각각의 쓰레드에 참조값으로 저장할 변수를 만들고, 객체를 각 쓰레드에 연결한다

/*
	원주율을 계산만 하는 쓰레드와
	계산된 원주율을 출력만 하는 쓰레드가 있다.
	
	이 두 쓰레드를 이용하여 원주율을 계산한 후 출력하는 프로그램을 작성해 보자.
	
	원주율을 저장하는 객체가 필요하다.
	이 객체를 두 쓰레드가 공통으로 사용해서 처리한다.
	
 */

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareData sd = new ShareData(); // 공통으로 사용할 객체 생성
		
		// 쓰레드 객체들을 생성할 때 공통으로 사용할 객체의 참조값(인스턴스)을 넣어준다.
		CalcPIThread cp = new CalcPIThread(sd);
		PrintPIThread pp = new PrintPIThread(sd);
		
		cp.start();
		pp.start();
	}

}

// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread {
	private ShareData sd; // 공통으로 사용할 객체가 저장될 변수 선언
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		/*
			원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ...) * 4
			분모			1 - 3 + 5 - 7 + 9 - 11 ...
			2로 나눈 몫		0 - 1 + 2 - 3 + 4 - 5
		 */
		double sum = 0.0;
		for (int i = 1; i <= 2_000_000_000; i+=2) {
			if((i/2)%2 == 0){ // 짝수번째일 때
				sum += (1.0/i);
			} else {
				sum -= (1.0/i);
			}
		}
		
		sd.result = sum * 4;
		sd.isOk = true;
	}
}

// 계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread {
	private ShareData sd;
	
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(!sd.isOk){
			continue;
		}
		
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}
}


// 원주율을 관리하는 클래스(공유될 클래스)
class ShareData {
	public double result; // 계산된 원주율이 저장될 변수
	// volatile ==> 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다.
	//				즉, 캐시를 사용하지 않고 메모리에 있는 변수를 직접 사용한다.
	public volatile boolean isOk = false;  // 계산이 완료되었는지를 나타내는 변수
}