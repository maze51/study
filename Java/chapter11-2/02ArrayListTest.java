package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		//ArrayList는 기본적인 사용법이 Vector와 같다.
		ArrayList list1 = new ArrayList();
		
		// add()를 이용하여 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('K');
		list1.add(false);
		list1.add(12.34);
		
		System.out.println("list1의 크기 : " + list1.size());
		System.out.println("list1 => " + list1);
		
		// get()메서드를 이용하여 데이터를 꺼내온다.
		System.out.println("1번째 자료 : " + list1.get(1));
		
		// 데이터 끼워넣기도 똑같다.
		list1.add(0,"zzz");
		System.out.println("list1 => " + list1);
		
		// 데이터 변경하기
		String temp = (String) list1.set(0, "yyy");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		// 삭제도 같다
		list1.remove(0);
		System.out.println("list1 => " + list1);
		
		list1.remove("bbb");
		System.out.println("list1 => " + list1);
		System.out.println("-----------------------------------------------");
		
		// 제네릭을 지정하여 선언할 수 있다.
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
		}
		
		for (String a : list2) {
			System.out.println(a);
		}
		System.out.println("-----------------------------------------------");
		
		// contains(비교데이터); => 리스트에 '비교데이터'가 있으면 true, 없으면 false
		
		System.out.println(list2.contains("DDDD")); // 있으면 true
		System.out.println(list2.contains("ZZZZ")); // 없으면 false
		
		// indexOf(비교데이터); => 리스트 안에 '비교데이터'가 있으면 '비교데이터'가 있는 index값을 반환하고, 없으면 -1을 반환한다.
		System.out.println("DDDD의 index값 : " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ의 index값 : " + list2.indexOf("ZZZZ"));
		System.out.println("-----------------------------------------------");
		
		// subList(시작위치, 종료위치); => 리스트의 데이터 중에서 '시작위치'부터 '종료위치'이전까지의(종료위치 미포함) 데이터를 추출하여 List로 반환한다.
		
		List<String> tempList = list2.subList(1, 4);
		System.out.println("tempList => " + tempList);
		
		// toArray(); => 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		//				 기본적으로 Object형 배열로 변환한다.
		
		// toArray(new 제네릭타입[0]) ==> 제네릭타입의 배열로 반환한다.
		
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		System.out.println(Arrays.toString(strArr));
		
		System.out.println("-----------------------------------------------");
		
		String[] strArr2 = list2.toArray(new String[0]);
		for(String a : strArr2){
			System.out.println(a);
		}
		
		
		
		
		
	}
}
