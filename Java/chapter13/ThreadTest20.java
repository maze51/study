package basic;

public class ThreadTest20 {

	public static void main(String[] args) {
		DataBox db = new DataBox();
		
		ProducerThread th1 = new ProducerThread(db);
		ConsumerThread th2 = new ConsumerThread(db);
		
		th1.start();
		th2.start();
	}

}

// 데이터를 넣어주는 쓰레드
class ProducerThread extends Thread{
	private DataBox db;

	public ProducerThread(DataBox db) {
		this.db = db;
	}
	
	@Override
	public void run() {
		for (int i = 0; i <= 5; i++) {
			String data = "공급data-" + i;
			db.setData(data); 
		}
	}
}

// 데이터를 사용하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox db;

	public ConsumerThread(DataBox db) {
		this.db = db;
	}
	
	@Override
	public void run() {
		for (int i = 0; i <= 5; i++) {
			String data = db.getData();
		}
	}
}

// 데이터를 공통으로 사용하는 클래스
class DataBox{
	private String data;
	
	// data변수의 값이 비어 있으면 값이 채워질 때 까지 기다렸다가 값이 채워지면 그 때 값을 반환한다.
	public synchronized String getData(){
		if(data==null){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		String temp = data;
		System.out.println("소비자 쓰레드가 읽은 데이터 : " + temp);
		data = null;
		notify();
		return temp;
		
	}
	
	// 데이터가 비워지지 않았을 때는 데이터가 비워질 때 까지 기다리고
	// 데이터가 비워지면 데이터를 세팅하는 메서드
	public synchronized void setData(String data){
		if(this.data!=null){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.data = data;
		System.out.println("공급자 쓰레드가 저장한 데이터 : " + data);
		notify();
	}
}
