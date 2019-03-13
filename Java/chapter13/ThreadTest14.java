package basic;

import java.util.ArrayList;
import java.util.Collections;

/*

	10마리의 말들이 경주하는 경마 프로그램 작성하기

	말은 Horse라는 이름의 클래스로 구성한다.
	이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
	그리고 이 클래스는 등수를 오름차순으로 처리할 수 있는 기능이 있다(Comparable 인터페이스 구현).

	경기 구간은 1 ~ 50구간으로 되어 있다.

	일정한 시간마다 말들의 위치를 아래와 같이 출력한다.
	출력예)
		1번말 ----->---------------------------------------- (점은 50개. 현재 위치에 따라 >표시된다)
		2번말 -------->-------------------------------------
		3번말 -->-------------------------------------------
		...
		10번말----->----------------------------------------

	경기가 끝나면 등수 순으로 출력한다.
 */

public class ThreadTest14 {
	public static ArrayList<Horse> hList = new ArrayList<Horse>();
	public static ArrayList<Horse> result = new ArrayList<Horse>();

	public static void main(String[] args) {	/////////////////// main
		CPosition a = new CPosition();
		a.setDaemon(true);

		for (int i = 1; i < 11; i++) {
			hList.add(new Horse(i+"번말", 0, 0));
		}
		a.start();
		for (int i = 0; i < hList.size(); i++) {
			hList.get(i).start();
		}

		for (int i = 0; i < hList.size(); i++) {
			try {
				hList.get(i).join();
			} catch (InterruptedException e) {
			}
		}

		System.out.println();
		System.out.println("경기 결과");

		Collections.sort(result);

		for (int i = 0; i < ThreadTest14.result.size(); i++) {
			System.out.println(ThreadTest14.result.get(i).getRank() + "위 " + 
					ThreadTest14.result.get(i).getHorseName());

		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int rank;
	private int cposition;

	public Horse(String horseName, int rank, int cposition) {
		super();
		this.horseName = horseName;
		this.rank = rank;
		this.cposition = cposition;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getCposition() {
		return cposition;
	}

	public void setCposition(int cposition) {
		this.cposition = cposition;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			try {
				Thread.sleep((int)(Math.random()*301 + 100));
			} catch (InterruptedException e) {
			}
			cposition++;
		}
		//System.out.println(horseName + "도착");
		

		for (int i = 0; i < ThreadTest14.hList.size(); i++) {
			if(ThreadTest14.hList.get(i).getHorseName().equals(horseName)){
				ThreadTest14.result.add(ThreadTest14.hList.get(i));
			}
		}
		int rank = 1;
		for (int i = 0; i < ThreadTest14.result.size(); i++) {
			ThreadTest14.result.get(i).setRank(rank);
			rank++;
		}
	} // end of run

	@Override
	public int compareTo(Horse o) {
		return Integer.compare(this.rank, o.rank);
	}
}

class CPosition extends Thread{ // 현재 위치를 출력할 데몬 쓰레드
	public void showPosition(){
		System.out.println("현재 위치");
		for (int i = 0; i < ThreadTest14.hList.size(); i++) {
			System.out.print(ThreadTest14.hList.get(i).getHorseName()+"\t");
			for (int j = 0; j < ThreadTest14.hList.get(i).getCposition()-1; j++) {
				System.out.print("-");
			}
			System.out.print(">");
			for (int j = 0; j < 50-ThreadTest14.hList.get(i).getCposition(); j++) {
				System.out.print("-");

			}
			System.out.println();
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000); // 2초마다 현재 위치 출력
			} catch (InterruptedException e) {
			}
			showPosition();
		}
	}
}