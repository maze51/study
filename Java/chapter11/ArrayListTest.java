package j_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListTest {
	public static void main(String[] args) {
		
		List<Integer> list1 = new ArrayList<Integer>(); // implements하는 것이 상당히 많다. 앞은 인터페이스타입 뒤는 그를 구현한 구현체의 인스턴스타입으로 만들어야 한다.
		list1.add(new Integer(5));
		list1.add(new Integer(2));
		list1.add(3);	// 오토박싱이 자동으로 이루어진다
		list1.add(1);
		list1.add(4);
		
		System.out.println(list1); // toString()메서드가 오버라이딩되어 있다.
		
		Collections.sort(list1); // list형만 받는 자동정렬 메서드
		System.out.println(list1);
		
		ArrayList<Integer> list2 = new ArrayList<Integer>(list1); // list1에 있는 걸 다 복사해서 넣어준다
		ArrayList<Integer> list3 = new ArrayList<Integer>(list1.subList(1, 4)); // 방번호로 잘라준다
		System.out.println(list3);
		
		
		System.out.println(list3.remove(1));
		// delete. 자동으로 오토박싱을 해줄 수 없는 상태. 이러면 1번 자리의 값을 삭제한다
		System.out.println(list3.remove(new Integer(1)));
		// ArrayList에서 1값을 찾아 삭제
		
		System.out.println(list3.get(1)); // select
		
		list3.add(1, 15);
		// insert 해당 번째에 넣어주고 해당 위치 뒤에 있던 기존 값은 뒤로 민다. 아직 값이 채워지지 않은 맨 뒷칸은 지정해서 채워넣을 수 없다.
		System.out.println();
		
		list3.set(2, 22); // update
		System.out.println(list3);
		
		System.out.println(list1.contains(6)); // 포함하고 있나 체크
		
		System.out.println(list1.containsAll(list3)); // 전부 포함하고 있는가 체크
	}
}
