package basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		/*
			Map => key값과 value값을 한 쌍으로 관리하는 객체
				=> key값은 중복을 허용하지 않고, 순서가 없다. (Set의 특징을 갖고 있다)
				=> value값은 중복을 허용한다.
		 */
		HashMap<String, String> map = new HashMap<String, String>();
		
		// put(key값, value값) => 자료 추가
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map => " + map);
		
		// 자료 수정 => 데이터를 저장할 때 key값이 같으면 나중에 저장한 값이 저장된다
		map.put("addr", "서울");
		System.out.println("map => " + map);
		
		// 자료 삭제 => remove(key값);
		//				반환값 : 삭제 성공시 해당 key의 value값, 삭제 실패시 null
//		String temp = map.remove("tel");
//		System.out.println("temp => " + temp);
//		System.out.println("map => " + map);
		
		// 자료 읽기 => get(key값);
		System.out.println("name : " + map.get("name"));
		System.out.println();
		
		// key값을 읽어와 자료를 처리하는 방법
		
		// 방법1 : keySet()메서드 이용하기
		//		: 맵의 key값만 읽어와 Set형으로 반환하는 메서드
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String key = it.next(); // key값 구하기
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("--------------------------------------------");
		
		// 방법2 : keySet()의 값들을 향상된 for문으로 처리하기
		for(String key : map.keySet()){
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("--------------------------------------------");
		
		// 방법3 : 맵의 value값만 읽어와 처리하기
		//		: values()메서드 이용하기
		for(String value : map.values()){
			System.out.println(value);
		}
		System.out.println("--------------------------------------------");
		
		/*
		방법 4 : Map에는 Entry라는 내부class가 만들어져 있다.
				이 Entry클래스는 key와 value라는 멤버변수로 구성되어 있다.
				Map에서는 이 Entry클래스를 Set형식으로 저장하여 관리한다.
			
			(참고) 많이 사용하진 않는다. 1, 2번 방법을 많이 사용한다
		 */
		
		// Entry객체 전체를 가져와서 Set형식으로 저장할 수 있다.
		//		=> entrySet()메서드를 이용한다.
		
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		
		// 가져온 Entry객체를 순서대로 처리하기(Iterator나 향상된 for문을 이용한다)
		Iterator<Map.Entry<String, String>> entryIt = entrySet.iterator();
		
		while(entryIt.hasNext()){
			Map.Entry<String, String> entry = entryIt.next(); // Entry객체 가져오기
			
			System.out.println("key값 : " + entry.getKey()); // Entry객체의 key값 가져오기
			System.out.println("value값 : " + entry.getValue()); // value값 가져오기
			System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
