package i_javaLang;

public class EqualsTest01 {
	public static void main(String[] args) {
		
		//2. Value클래스의 객체를 만들어주세요 v1, 인자값 10
		Value v1 = new Value(10);
		//3. Value클래스의 객체를 만들어주세요 v2, 인자값 10
		Value v2 = new Value(10);
		
		System.out.println(v2);
		System.out.println(v1==v2); // false
		
		System.out.println(v1.equals(v2)); // false
		
		v2 = v1;
		
		System.out.println(v1.equals(v2)); // true
		
		
	}
}

class Value {
	int value;
	//1. 매개변수가 하나인 생성자
	Value(int value){
		this.value = value;
	}
}