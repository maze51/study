package basic;

/*
쓰레드가 실행대기열(RUNNABLE)에 왔다고 바로 실행되는 것이 아님.
대기하고 있는 다른 쓰레드가 많다면 대기해야할 수 있음
 */

// 쓰레드의 상태를 출력하는 예제.

public class ThreadTest10 {

	public static void main(String[] args) {
		// 타겟 쓰레드를 먼저 만든다
		TargetThread target = new TargetThread();
		StatePrintThread spt = new StatePrintThread(target);
		spt.start();
	}
}

// TargetThread의 상태를 출력하는 쓰레드
class StatePrintThread extends Thread{
	private Thread targetThread; // TargetThread가 저장될 변수
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true){
			// 쓰레드의 현재 상태값 구하기
			Thread.State state = targetThread.getState();
			System.out.println("타겟 쓰레드의 상태값 : " + state);
			
			if(state == Thread.State.NEW){ // 쓰레드가 NEW상태인지 검사
				targetThread.start();
			}
			
			if(state == Thread.State.TERMINATED){ // 쓰레드가 종료상태인지 검사
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}
}


// 처리 대상이 되는 쓰레드 (target쓰레드)
class TargetThread extends Thread{
	@Override
	public void run() {
		for (long i = 1L; i < 20_000_000_000L; i++) {} // 시간 지연용			
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		for (long i = 1L; i < 20_000_000_000L; i++) {} // 시간 지연용		
	}
}
