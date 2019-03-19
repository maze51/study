package basic;

import java.util.Arrays;

public class ThreadTest14answer {
	public static int currentRank = 0; // 경기가 끝난 말의 등수를 결정하는 변수
	
	public static void main(String[] args) {
		Horse1[] horses = new Horse1[]{
				new Horse1("01번말"),
				new Horse1("02번말"),
				new Horse1("03번말"),
				new Horse1("04번말"),
				new Horse1("05번말"),
				new Horse1("06번말"),
				new Horse1("07번말"),
				new Horse1("08번말"),
				new Horse1("09번말"),
				new Horse1("10번말")
		};

		// 경기 상황을 출력하는 쓰레드 객체 생성
		PlayState ps = new PlayState(horses);

		for (Horse1 h : horses) {
			h.start();
		}
		ps.start();

		for (Horse1 h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}

		try {
			ps.join();
		} catch (InterruptedException e) {
		}
		System.out.println();
		System.out.println("경기 끝");
		System.out.println();

		// 배열의 요소값들을 정렬한다.
		Arrays.sort(horses);

		// 경기 결과 출력
		for (int i = 0; i < horses.length; i++) {
			System.out.println(horses[i]);
		}
	}
}

// Horse클래스 작성
class Horse1 extends Thread implements Comparable<Horse1>{
	private String horseName; 	// 말이름
	private int rank; 			// 등수
	private int location; 		// 현재 위치

	// 생성자
	public Horse1(String horseName) {
		this.horseName = horseName;
	}

	//getter
	public String getHorseName() {
		return horseName;
	}

	public int getLocation() {
		return location;
	}

	public int getRank(){
		return rank;
	}


	@Override
	public int compareTo(Horse1 h) {
		return Integer.compare(rank, h.rank);
	}

	@Override
	public void run() {
		for (int i = 0; i <= 50; i++) {
			location = i;
			try {
				Thread.sleep((int)(Math.random() * 400));
			} catch (InterruptedException e) {
			}
		}

		// 경기가 끝난 후 등수를 결정해서 rank변수에 저장한다.
		ThreadTest14answer.currentRank++;
		rank = ThreadTest14answer.currentRank;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은 " + rank + "등 입니다.";
	}
}

// 경기 상황을 출력하는 쓰레드
class PlayState extends Thread{
	Horse1[] horses; // 경기 중인 말들이 저장된 배열을 저장할 변수

	// 생성자
	public PlayState(Horse1[] horses) {
		super();
		this.horses = horses;
	}

	@Override
	public void run() {
		while(true){
			// 빈줄을 출력하는 반복문
			for (int i = 1; i <=30; i++) {
				System.out.println();
			}

			// 각 말들의 현재 위치를 나타내는 반복문
			for (int i = 0; i < horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				for (int j = 0; j <= 50; j++) {
					if(j == horses[i].getLocation()){
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				if(horses[i].getRank()>0){
					System.out.print("  " + horses[i].getRank()); // 들어온 순서대로 그림 옆에 등수를 출력하는 부분
				}
				System.out.println(); // 줄바꿈
			}
			if(ThreadTest14answer.currentRank==horses.length){ // 경기가 끝나는 시점 검사
				break;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}
