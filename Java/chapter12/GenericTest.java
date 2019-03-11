package genericTest;

// 제네릭을 사용하지 않는 클래스
class NonGenericClass{
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

/*
	제네릭 클래스 만드는 방법
class 클래스명<제네릭타입글자>{
	제네릭타입글자 변수명;	// 변수 선언에 사용할 경우
	...
	
	제네릭타입글자 메서드명(){ // 메서드의 반환값으로 사용할 경우
		...
		return 값;
	}
	
	반환값타입 메서드명(제네릭타입글자 변수명){ // 메서드의 매개변수에 사용할 경우
		...
	}
}
=== 제네릭타입 글자 ===
T ==> Type
K ==> Key
V ==> Value
E ==> Element
 */

// 제네릭을 사용한 클래스
class MyGenericClass<T>{ // 아래에서 String으로 만들면 T자리에 모두 String이 들어간다고 간주하면 편하다. Integer면 모두 Integer
	private T value;
	
	public void setValue(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
}

public class GenericTest {

	public static void main(String[] args) {
		// 제네릭 도입 전에는 주로 Object형을 활용해 객체를 만들었다. 저장은 문제가 없었으나 뽑아 쓰려면 형변환을 해야 하는데, 
		// 잘못 형변환을 해도 코딩 당시에는 에러 표시가 뜨지 않는다. 실행시 에러가 발생하고, 문제가 생긴다(찾기 어려움, 프로젝트 진행이 더뎌짐).
		// -> 내가 원하는 형태의 데이터를 명확하게 저장하고 뽑아쓸 수 있도록 제네릭 타입을 도입함.
		
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setValue("가나다라마바사"); // setValue를 쓸 때는 형변환 없이 잘 들어간다
		
		String rtn = (String) ng1.getValue(); // 저장한 데이터에 맞게 형변환을 잘 해주면 별 문제가 없다.
											 
		System.out.println("문자열 반환값 : " + rtn);
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setValue(100);
//		int irtn = (int) ng2.getValue();
//		System.out.println("정수 반환값 : " + irtn);
//		String rtn2 = (String) ng2.getValue(); // 하지만 형변환이 잘못됐을 경우 오류가 발생한다
//		System.out.println("반환값 : " + rtn2);
		
		//==============================================================
		MyGenericClass<String> mg1 = new MyGenericClass<String>();
		mg1.setValue("우리나라");
		String strRtn = mg1.getValue(); // 형변환이 필요 없다
		System.out.println("strRtn = " + strRtn);
		
		MyGenericClass<Integer> mg2 = new MyGenericClass<Integer>();
		mg2.setValue(200);
		int intRtn = mg2.getValue();
		System.out.println("intRtn = " + intRtn);
		
		// 제네릭에 설정한 데이터와 다른 종류의 데이터를 세팅할 때 "컴파일 단계에서"오류 발생
//		mg1.setValue(100);
		
	}

}
