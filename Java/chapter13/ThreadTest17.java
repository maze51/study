package basic;
/*
	은행에서 입출금을 쓰레드로 처리하는 예제
 */


public class ThreadTest17 {
	private int balance;	// 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금 처리를 하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// 출금 처리를 하는 메서드 (반환값: 출금이 성공하면 true, 실패하면 false)
	public boolean withdraw(int money){
		//	public synchronized boolean withdraw(int money){
		synchronized (this) {

			if(money<=balance){
				for (int i = 0; i < 10_000_000; i++) {} // 시간때우기용
				balance -= money;
				System.out.println("메서드 안의 balance = " + balance);
				return true;
			} else {
				return false;
			}
		}
	}

	public static void main(String[] args) {
		final ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000); // 잔액을 10000원으로 세팅

		// 익명 구현체로 쓰레드 구현
		Runnable syncTest = new Runnable() {
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);
				System.out.println("쓰레드에서 result = " + result + ", balance = " + acount.getBalance());
			}
		};

		Thread th1 = new Thread(syncTest);
		Thread th2 = new Thread(syncTest);

		th1.start();
		th2.start();
	}

}
