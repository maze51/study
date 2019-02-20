package j_collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorTest {
	public static void main(String[] args) {
		//Iterator 단방향 이동만 가능
		//Enumeration은 컬렉션이 나오기 전에 사용되던 Iterator의 구버전이라고 생각하면 된다
		
		List<String> list = new ArrayList<String>();
		//1. list에 "123" 추가
		list.add("123");
		//2. list에 "456" 추가
		list.add("456");
		//3. list에 "789" 추가
		list.add("789");
		
		Iterator<String> it = list.iterator(); // list를 iterator타입으로 바꿔준다. set으로도 가능
		// 컬렉션의 요소를 하나씩 확인하는 용도.
		
		while(it.hasNext()){ // 다음 녀석이 있나?
			System.out.println(it.next());
//			it.remove(); // 삭제
		}

		//ListIterator
		// Iterator의 단방향을 보완하기 위해서 나왔다.
		// List에서만 사용할 수 있다.
		ListIterator<String> li = list.listIterator(); // ListIterator로 변환해줌
		li.hasNext();
		System.out.println(li.next()); // 다음것으로 넘어감
		li.hasPrevious();
		System.out.println(li.previous()); // 다시 앞으로
		
		
		
		
		
	}
}
