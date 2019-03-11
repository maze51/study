package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이몽룡");
		
		System.out.println("정렬 전 :" + list);
		
		// 정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		// 이 메서드의 기본적인 정렬방식은 '오름차순' 방식이다.
		Collections.sort(list);
		System.out.println("정렬 후 :" + list);
		
		Collections.shuffle(list); // 자료 섞기
		System.out.println("자료 섞기 후 :" + list);
		
//		Desc dd = new Desc();
//		Collections.sort(list, dd);
		Collections.sort(list, new Desc()); // 위 두 줄을 한줄로. 정렬 기준 객체를 지정하여 정렬하기.
		System.out.println("정렬 후 :" + list);
		
		//(여기서 정렬은 String에 구현되어 있던 Comparable이 작동한 것)
		
	}

}


// 정렬 방식을 사용자가 임의로 정하고 싶으면Comparator인터페이스를 구현하는 클래스를 작성하고 이 클래스의 인스턴스를
// sort()메서드의 매개값으로 넣어주면 된다.

class Desc implements Comparator<String> {
	// Comparator인터페이스에는 compare()메서드가 선언되어 있어서 이것을 재정의해야 한다.
	
	// compare() 메서드를 구현하는 방법
	// 오름차순일 경우 => 앞의 갚이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 한다.
	
	// 이 메서드가 양수를 반환하면 앞, 뒤 값의 순서가 바뀐다.
	
	// String객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는데
	// 이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
	// (Wrapper클래스와 Date객체, File객체에도 구현되어 있다) 특히 날짜
	
	@Override
	public int compare(String o1, String o2) {
//		if(o1.compareTo(o2)>0){
//			return -1; // 양수로 하면 오름차순이 되어버린다
//		} else if(o1.compareTo(o2)<0) {
//			return 1;
//		} else {
//			return 0;
//		}
//		return o1.compareTo(o2); // 이러면 그저 오름차순
		return o1.compareTo(o2) * -1; // 이러면 부호가 바뀌어 내림차순. 위 if문을 한 줄로 줄일 수 있다.
		//여긴 단순히 비교하고 결과값을 주는 부분. sort에서 꺼내서 반환값을 봐 가면서 바꾼다.
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}