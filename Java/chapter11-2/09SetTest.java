package basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/*
	- List와 Set의 차이점
	
	1. List
		- 데이터의 순서가 있다(index가 있다)
		- 중복되는 데이터를 저장할 수 있다.
		
	2. Set
		- 데이터의 순서가 없다(index가 없다)
		- 중복되는 데이터를 저장할 수 없다.


 */



public class SetTest {

	public static void main(String[] args) {
		HashSet set = new HashSet();
		
		// Set에 데이터를 추가할 때도 add()메서드를 사용한다.
		// 반환값 : 데이터 추가에 성공하면 true, 실패하면 false
		set.add("DD");
		set.add("AA");
		set.add(2);
		set.add("CC");
		set.add(1);
		set.add(3);
		
		System.out.println("set : " + set);
		System.out.println("set의 개수 : " + set.size());
		
		// Set은 중복되는 데이터를 허용하지 않는다
		// 그래서 이미 있는 데이터를 add하면 false를 반환하고 데이터는 추가되지 않는다.
		
		boolean isAdd = set.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		
		isAdd = set.add("CC");
		System.out.println("중복될 때 : " + isAdd);
		
		System.out.println("set : " + set);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 해당 자료를 삭제한 후 추가하는 방법을 사용한다.
		// 예) FF를 EE로 수정하기
		
		// 데이터 삭제하기 : remove(삭제할데이터)
		// clear() => 전체 자료 삭제
		set.remove("FF");
		System.out.println("삭제 후 set : " + set);
		System.out.println();
		
		set.add("EE");
		System.out.println("set : " + set);
		
		//set.clear();
		//System.out.println("clear 후 set : " + set);
		
		// Set은 데이터의 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다.
		// 그래서 데이터를 하나씩 얻기 위해서는 Iterator로 변환해서 사용해야 한다.
		// Iterator로 변환하는 방법 => iterator()메서드를 호출하면 된다.
		
		// Set데이터를 Iterator로 변환하기
		Iterator it = set.iterator();
		System.out.println("Iterator로 자료 출력하기");
		
		// 데이터 개수만큼 반복해서 데이터를 하나씩 꺼내오기
		while(it.hasNext()){ // 다음 위치에 자료가 있으면 true
		// 이 while문은 데이터 개수만큼 자동으로 반복하는 반복문이 된다
		// next() => 포인터를 다음 자료 위치로 이동하고 이동한 위치의 자료를 반환한다.
			System.out.println(it.next());
		}
		System.out.println();
		
		System.out.println("향상된 for문을 이용하여 출력하기");
		// 향상된 for문 사용(Iterator를 쓰지 않고)
		for(Object data : set){ // 내부에서 Iterator를 자동으로 만들어서 출력하는 식
			System.out.println(data);
		}
		
		// 로또번호 만들기(Set이용 - 자동으로 중복값 불가)
		HashSet<Integer> lotto = new HashSet<Integer>();
		
		while(lotto.size() < 6){
			// 1 ~ 45 사이의 난수 만들기
			// (int)(Math.random()* (최대값-최소값+1) + 최소값;
			int num = (int)(Math.random()*(45-1+1)+1);
			lotto.add(num);
		}
		System.out.println("lotto 번호 : " + lotto);
		
		// 컬렉션 유형의 객체들은 서로 다른 자료 구조로 쉽게 변경해서 사용할 수 있다.
		ArrayList<Integer> lottoList = new ArrayList<Integer>(lotto); // set에 있는 데이터를 바로 list로 옮길 수 있다
		System.out.println("lottoList(로또번호) " + lottoList);
		
		for (int i = 0; i < lottoList.size(); i++) {
			System.out.println(lottoList.get(i) + " ");
		}
		System.out.println();
		
		
		
	}

}


