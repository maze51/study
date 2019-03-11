package i_javaLang;

import java.util.Date;

public class EqualsTest02 {
	public static void main(String[] args) {
		Person p1 = new Person(8603301233456L);
//		Person p2 = new Person(8603301233456L);
		Person p2 = null;
		
		
		System.out.println(p1 == p2); // 주소를 비교하기 때문에 false
		
		
		if(p1.equals(p2)){
			System.out.println("같은 사람");
		} else {
			System.out.println("다른 사람");
		}
	}
}


class Person {
	private long regNo;
	
	public Person(long regNo) {
		this.regNo = regNo;
	}

	@Override
	public boolean equals(Object obj) { // p2의 주소를 받는다. 업캐스팅됐으므로 호출불가.
		boolean result = false;


		if(obj == null && obj instanceof Person && regNo == ((Person)obj).regNo){	// 첫번째 regNo는 p1의 메서드를 호출한 것. p2를 받아서 p2안의 person의 regNo와 비교한 것.
			// Person의 인스턴스이면서 regNo가 같은 것을 찾는 조건식
			// 객체를 가지고 다닐때는 그것이 null인지 체크하는 절차가 필요하다
			result = true;
		}

		return result;
	}
	
	
}