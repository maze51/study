package basic;

// 데몬 쓰레드 연습 ==> 3초에 한 번씩 저장하는 프로그램

public class ThreadTest09 {

	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// 데몬 쓰레드로 설정 ==> start()메서드 호출 전에 설정한다.
		autoSave.setDaemon(true);
		// 위 부분이 없으면(데몬 쓰레드로 설정하지 않으면) 자동 저장 쓰레드는 메인이 끝나도 계속 돌아간다
		autoSave.start();
		
		try {
			for(int i=1;i<=20;i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
		}
		System.out.println("메인 쓰레드 종료.");
	}

}

// 3초에 한 번씩 자동 저장하는 쓰레드
class AutoSaveThread extends Thread{
	public void save(){
		System.out.println("작업 내용을 저장합니다."); // 저장은 메시지 출력으로 대체
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			save();
		}
	}
}