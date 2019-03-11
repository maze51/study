package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
	정렬과 관련된 Interface는 Comparable과 Comparator 이렇게 두 가지가 있다
	
	- 보통 객체(VO객체) 자체에 정렬 기능을 넣기 위해서는 Comparable을 구현하고,
	   앞의 예제처럼 정렬 기준을 별도로 구현하고 싶을 때에는 Comparator를 구현하여 사용하면 된다.
	   
	- Comparable에서는 compareTo()메서드를 구현해야 하고,
	  Comparator에서는 compare()메서드를 구현해야 한다.
 */

// 예) 회원번호, 이름, 전화번호를 멤버로 갖는 Member클래스를 구성하는 데
//	    회원의 이름을 기준으로 오름차순이 될 수 있는 class를 만드시오.
class Member implements Comparable<Member>{
	
	private int num;
	private String name; /*앞에 있는 데이터*/
	private String tel;
	
	public Member(int num, String name, String tel) { // 자동 생성자. Generate Constructor using fields
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	// 이름을 기준으로 오름차순 정렬이 되도록 설정한다.
	@Override
	public int compareTo(Member mem/*뒤에 있는 데이터*/) {
		return this.name.compareTo(mem.getName()); // 오름차순이니까 반환값을 그대로 쓰면 된다
		
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
}

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-0000-0000"));
		memList.add(new Member(5, "변학도", "010-1111-1111"));
		memList.add(new Member(10, "성춘향", "010-2222-2222"));
		memList.add(new Member(3, "이순신", "010-7141-3333"));
		memList.add(new Member(6, "강감찬", "010-6216-1188"));
		memList.add(new Member(2, "일지매", "010-0084-3452"));
		
		System.out.println("정렬전");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------");
		
		Collections.sort(memList); // Comparable을 구현하지 않고 정렬시키면 여기서 오류 발생. String과 달리 구현되어 있던 Comparable이 없기 때문
		System.out.println("정렬후");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------");
		
		// 번호의 내림차순 정렬
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("번호의 내림차순 정렬후");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------");
		
	}
}

// 정렬 기준을 외부에 따로 선언하기 위해서는 Comparator를 구현한다.
// Member의 num값의 내림차순으로 정렬하는 기준 class를 작성한다.
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		/*
		if(mem1.getNum() < mem2.getNum()){
			return 1;
		} else if(mem1.getNum() > mem2.getNum()){
			return -1;
		} else {
			return 0;
		}
		*/
		
		// Wrapper클래스를 이용하는 방법 1
//		return Integer.compare(mem1.getNum(), mem2.getNum()); // 오름차순
//		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1; // 내림차순
		
		// Wrapper클래스를 이용하는 방법 2
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
		//compareTo는 iv, compare는 cv
		
	}
}



