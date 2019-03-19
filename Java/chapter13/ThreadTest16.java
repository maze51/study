package basic;

// 데이터의 신뢰성이 떨어지는 데이터가 된다.

public class ThreadTest16 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject(); // 공통으로 사용할 객체 생성
		
		WorkerThread th1 = new WorkerThread("쓰레드1", sObj);
		WorkerThread th2 = new WorkerThread("쓰레드2", sObj);
		
		th1.start();
		th2.start();
	}

}

// 작업을 진행하는 쓰레드
class WorkerThread extends Thread {
	private ShareObject sObj;

	public WorkerThread(String name, ShareObject sObj) {
		super(name); // 쓰레드 이름 설정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sObj.add();
		}
	}
}

// 공통으로 사용할 클래스
class ShareObject{
	private int sum = 0;
	
	// 동기화 하기
	public void add(){
	//public synchronized void add(){ // 방법 1 ==> 메서드에 동기화 설정
	
		synchronized (this) { // 방법 2 ==> 동기화 블럭으로 동기화 설정. this자리에는 객체의 참조변수를 넣는다. 하지만 거의 대부분 this를 쓴다
		
		int n = sum;
		
		n += 10; // 10 증가
		
		sum = n;
		
		System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}
//어느 시점에서든지 다른 쓰레드 제어로 넘어갈 수 있다(변경 전 값을 가지고). 때문에 결과값이 제대로 나오지 않음.
// 해결하기 위해 다른 쓰레드의 접근을 막을 필요가 있다 -> 동기화 처리