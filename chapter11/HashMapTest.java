package j_collection;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
	public static void main(String[] args) {
		//1. map객체를 만들어주세요
		Map<String, Integer> map = new HashMap<String, Integer>(); //앞은 인터페이스 타입. 인터페이스의 다형성을 이용해서 Map타입을 쓸 수 있다. 뒤는 구현체인 Hashmap<>
		//2. 아래와 같이 값을 추가해주세요
		//	박서경	80
		//	정요한	95
		//	박영춘	79
		//	유다연	10
		//	유민하	200
		map.put("박서경", 80); // 원래는 new Interger(80)이라고 써야 하지만 오토박싱이 이루어졌다
		map.put("정요한", 95);
		map.put("박영춘", 79);
		map.put("유다연", 10);
		map.put("유민하", 200);
		
		System.out.println(map); // toString()이 오버라이드되어 있다!
		
		System.out.println(map.get("유다연")); // 키에 해당하는 value값을 반환한다
		
		System.out.println(map.keySet()); // key를 몰아서 set에다 저장한다. 역시 순서는 멋대로다
//		Set<String> keySet = map.keySet();
		
		map.remove("유민하"); //지우고 value값을 반환한다	delete
		
		System.out.println(map);
		
		//똑같은 key값을 줘도 들어간다. 나중에 넣은 value값으로 치환이 이루어진다.	update
		map.put("유다연", 100);
		System.out.println(map);
		
		//JDK1.8 replace
		//키만 동일한 경우
		//map.replace(key,newValue); // put과 비슷하지만 메서드명만 보고 바꾸는 부분이라고 인지할 수 있다
		//map.replace("유다연",100);
		//키와 값이 모두 동일한 경우
		//map.replace(key,oldValue,newValue); // 기존 값까지 같아야만 새 값으로 바꿔준다
		//map.replace("유다연",10,100);
		
		
		
	}
}
