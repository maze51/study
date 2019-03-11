package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class SetTest2 {

	public static void main(String[] args) {
		// 보통 Set은 순서가 없는데, 이 TreeSet은 데이터를 자동으로 정렬하는 기능이 있다. 그래서 순서가 있는 것 처럼 보일 수도 있다.
		TreeSet<String> ts = new TreeSet<String>();
		List<String> list = new ArrayList<String>();

		for(char ch='Z' ; ch>='A'; ch-- ){
//			String tmp = String.valueOf(ch);
			String tmp = ch + "";
			ts.add(tmp);
			list.add(tmp);
		}
		
		System.out.println("TreeSet 자료 : " + ts);
		System.out.println("List    자료 : " + list);
		System.out.println();
		
		// TreeSet에 저장된 자료 중 특정한 자료보다 적은 자료를 찾아서
		// SortedSet으로 변환하는 메서드가 있다. => headSet(기준값);
		// 기본적으로 '기준값'은 포함되지 않는다.
		// 만약 '기준값'을 포함시키고 싶으면 '기준값'다음에
		// true(포함), false(미포함)를 지정하여 결정할 수 있다.
		
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K이전 자료 : " + ss1);
		System.out.println("K이전 자료 : " + ts.headSet("K", true));
		System.out.println();
		// tailSet(기준값); => '기준값'보다 큰 자료를 찾아서 SortedSet으로 반환한다.
		//					  기본적으로 '기준값'이 포함된다.
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K이후 자료 : " + ss2);
		System.out.println("K이후 자료 : " + ts.tailSet("K", false));
		System.out.println();
		
		// subSet은 ~부터 ~까지의 자료를 가져온다.
		// 기본적으로 '앞'의 값은 포함되고 '뒤'의 값은 포함되지 않는다.
		System.out.println("subSet : " + ts.subSet("K", "N"));
		
		// subSet도 각 값 뒤에 논리값을 붙여서 포함 여부를 정할 수 있다.
		System.out.println("subSet : " + ts.subSet("K", true, "N", true));
		System.out.println("subSet : " + ts.subSet("K", false, "N", false)); // 둘 다 포함되지 않는다
		System.out.println("subSet : " + ts.subSet("K", false, "N", true));
		
		
		
	}

}
