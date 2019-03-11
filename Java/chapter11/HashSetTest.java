package j_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HashSetTest {
	public static void main(String[] args) {
		//1. Set객체를 만들어주세요 set
		Set<Integer> set = new HashSet<Integer>();
		
		//2. set변수에 0~10사이의 랜덤한 정수값을 추가해주세요
		// 반복횟수는 20회
		for (int i = 0; i < 20; i++) {
			boolean result = set.add((int)(Math.random()*45));
			System.out.println(result);
		}
		
		System.out.println(set); // set이라서 실패할 일이 있다.
		
		ArrayList<Integer> arrList = new ArrayList<Integer>(set); // ArrayList생성자에 set을 보내면 List로 바꿔준다
		Collections.sort(arrList);
		
		System.out.println(arrList); 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
