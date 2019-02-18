package i_javaLang;

public class StringTest01 {
	public static void main(String[] args) {
		
		String str1 = "abc"; // 같은 주소를 갖는다
		String str2 = "abc";
		
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2)); // 실제로는 값을 비교한 것
		
		String str3 = new String("abc"); // 리터럴이 아니다. 값이 로드되어야만(인스턴스화 되어야만) 리터럴로 인지한다.
		String str4 = new String("abc"); // 이건 heap메모리에 먼저 저장시키지 않는다.
		
		System.out.println(str3 == str4);
		System.out.println(str3.equals(str4)); // String 클래스는 equals메서드를 오버라이딩해서 값을 비교할 수 있도록 만들어졌다.
		
		System.out.println(str1.equals(str4)); // 주소는 다르지만 안의 값이 같다. 그래서 true
	}
}
