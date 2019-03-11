package basic;

import java.util.Vector;

public class VectorTest {
	
	// Collection에 저장할 수 있는 데이터는 (원래는)객체형 데이터 뿐이다.
	/*
	배열의 가장 큰 단점: 크기를 한 번 정하면 변경할 수 없다.
	(특징: 같은 타입만 저장할 수 있다. 하지만 Object형으로 만들면 다 넣을 순 있다)
	
	컬렉션들: 크기 변경이 자유롭고, 많은 양의 데이터를 저장할 수 있는 객체
	이 차이점 때문에 배열보다 컬렉션(중 리스트)을 많이 사용하게 된다.
	 */
	
	public static void main(String[] args) {
		//Vector객체 사용하기
		Vector v1 = new Vector(); // 생성시 제네릭 타입을 사용하지 않아 Object타입으로 저장된다.
		
		System.out.println("크기 : " + v1.size());
		
		//Vector는 add()메서드를 이용하여 데이터를 추가한다
		//데이터가 추가되었는지 확실하게 확인하고자 할 때는 add의 boolean형 반환값을 활용한다. 보통은 굳이 필요 없다
		
		v1.add("aaa");
		v1.add(new Integer(111)); // 원래 기본형 데이터를 추가하는 방식 (wrapper 클래스 형으로 바꿔 저장해야 한다)
		v1.add(123); // Integer형으로 auto-boxing(boxing-변환 작업을 자동으로 해 주는 것)
		v1.add('a');
		v1.add(true);
		v1.add(3.14);
		
		System.out.println("크기 : " + v1.size());
		System.out.println("v1 => " + v1);
		
		// addElement()를 이용하여 추가할 수 있다(예전 버전과 호환을 위해 존재함).
		v1.addElement("ccc");
		
		System.out.println("v1 => " + v1);
		
		// add(index, 데이터); ==> 벡터의 index번째에 '데이터'를 끼워 넣는다.
		//					  ==> index는 0번부터 시작한다.
		v1.add(1, "kkk");
		System.out.println("v1 => " + v1);
		
		// set(index, 데이터); ==> 벡터의 index번째의 값을 주어진 '데이터'로 덮어쓴다.
		//					  ==> 반환값 : 원래의 데이터
		
		String a = (String) v1.set(0, "zzz");
		System.out.println("원래의 데이터 : " + a);
		System.out.println("v1 => " + v1);
		
		// remove(index); ==> 벡터의 index번째 데이터를 삭제한다.
		//				  ==> 자료가 삭제되면 index번째 다음의 데이터들이 앞으로 자동으로 당겨 채워진다.
		//				  ==> 반환값 : 삭제된 데이터
		
		// remove(삭제할데이터);	==> 벡터에서 '삭제할데이터'를 찾아 삭제한다.
		//						==> 삭제할 데이터가 여러개면 앞에서부터 삭제된다.
		//		 ==> '삭제할데이터'가 '정수형'이거나 'char형'일 경우 객체로 변환해서 삭제해야 한다.
		
		v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		
		String b = (String)v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println("삭제된 자료 : " + b);
		
		v1.add(123);
		System.out.println("삭제 전 v1 => " + v1);
		
		v1.remove(true);
		System.out.println("삭제 후 v1 => " + v1);
		
		//v1.remove(123); X 삭제할 데이터가 정수형
		v1.remove(new Integer(123)); // O
		System.out.println("삭제 후 v1 => " + v1);
		
		//v1.remove('a'); X char는 실제로 들어갈 때 97로 들어간다.
		v1.remove(new Character('a')); // O
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(3.14); // 실수형은 문제 없이 삭제된다
		System.out.println("삭제 후 v1 => " + v1);
		
		// get(index); ==> 벡터의 index번째 자료를 반환한다.
		int data = (int)v1.get(0);
		System.out.println("0번째 자료 : " + data);
		
//		int data2 = (int) v1.get(1); // 문법상으로는 Object형을 int형으로 형변환함에 아무 문제가 없다
//		System.out.println(data2); // 하지만 실행시 ClassCastException오류 발생. 원 데이터가 String형이었기 때문.
		
		//--------------------------------------------------------------------------------------------------------------
		/*
		제네릭 타입(generic type)
			==> 객체를 선언할 때 < >괄호 안에 그 Collection이 사용할 데이터의 타입을 지정해 주는 것을 말한다.
				이런식으로 선언하게 되면 지정한 데이터 타입 이외의 다른 데이터를 저장할 수 없다.
				제네릭 타입으로 선언하면 데이터를 꺼내올 때 별도의 형변환이 필요 없다.
				데이터의 일관성을 유지하기 위해 사용한다.
				 
				단, 제네릭으로 지정할 수 있는 데이터 타입은 '클래스형'이어야 한다.
				(그래서 int는 Integer, boolean은 Boolean, char는 Character등으로 대체해서 사용해야 한다)
		 */
		
		Vector<String> v2 = new Vector<String>(); // String만 저장할 수 있는 벡터
		Vector<Integer> v3 = new Vector<Integer>(); // 클래스형을 사용해야 정상적으로 작동한다. int형만 저장할 수 있는 벡터
		
		v2.add("가가가가");
//		v2.add("123"); // 오류 : 다른 종류의 데이터를 추가할 수 없다.
		String temp2 = v2.get(0); // 형변환이 필요없다.
		
		System.out.println("temp2 => " + temp2);
		//--------------------------------------------------------------------------------------------------------------
		
//		Vector<Vector> vv = new Vector<Vector>; // 필요하다면 2차원 배열같은 식으로 활용가능
//		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>(); // 3차원 배열같은
		
		v2.clear(); // 벡터의 모든 데이터를 삭제한다.
		System.out.println("v2의 크기 : " + v2.size());
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v2 => " + v2);
		
		// removeAll(Collection객체); // clear와는 다르다. 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다.
		v2.removeAll(v4); // 찾아서 삭제한다
		System.out.println("v2 => " + v2);
		System.out.println("--------------------------------------------------------------");
		
		v2.clear();
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		// 벡터의 데이터를 순서대로 모두 하나씩 가져와서 사용하고 싶으면 반복문을 사용하면 된다. (보통은 for문)
		for (int i = 0; i < v2.size(); i++) {
			System.out.println(i + "번째 자료 : " + v2.get(i));
		}
		System.out.println("--------------------------------------------------------------");
		// 향상된 for문
		for(String s : v2){ // v2의 첫번째 값을 s에 저장하고 실행할 문장을 실행한다. 이후 반복하는 식
			System.out.println(s);
		}
		// collection, 배열 등에서 사용가능. 단 index값이 없고, 대상 안의 데이터만 꺼내서 활용할 수 있다
		
		for(Object obj : v1){
			// 제네릭 타입을 적지 않았으므로 Object형으로 처리한다. 단 단순 출력이 아니라 활용하고자 한다면 데이터가 정수형인지 문자형인지 판단하는 if문을 반복문 안에 추가해 줄 필요가 있다.
			System.out.println(obj);
		}
	}
}
