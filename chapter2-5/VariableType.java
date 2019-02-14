package a_variable;

public class VariableType {
	public static void main(String[] args) {
		
		/*
		 변수의 타입
		 
		 1. 기본형 타입(primitive type): 값을 저장(숫자, 실수, 한 문자). 단 8개
		  - boolean, char, byte, short, int, long, float, double
		 2. 참조형 타입(reference type): (주소에 가면 여러 변수를 또 가지고 있을 수 있음). 숫자가 무한대
		  - 주소를 저장하는 데 사용된다.
		  - 4byte의 저장공간을 가진다.
		 */
		
//		정수 50을 저장할 수 있는 변수를 선언 및 초기화해주세요
		int a; // 변수의 선언		int는 변수타입, a는 변수명
		a = 50; // 변수의 초기화
		int b = 50; // 변수의 선언 및 초기화
		
		/*
		 1. 종류
		  - 논리형 : true, false 중 하나의 값을 가진다. ex) boolean
		  - 문자형 : 단 하나의 문자를 저장한다. ex) char 
		  - 정수형 : 정수만 저장 가능하다. ex) byte, short, int, long
		  - 실수형 : 실수를 저장 가능하다. ex) float, double
		 
		 2. 기본형 변수의 크기(1byte = 8bit)
		  - 1byte : boolean, byte  
		  - 2byte : char, short 
		  - 4byte : float, int
		  - 8byte : double, long
		 
		 
		 */
		boolean power = true;
		
		char lang = 'A'; // 싱글쿼테이션(싱글쿼트). "" 이건 더블쿼테이션.
		
		/*
		 3. 논리형 - boolean(기본값 false)
		  - boolean형 변수에는 true와 false중 하나를 저장할 수 있다.
		  - boolean형 변수는 대답(yes, no), 스위치(on/off)등 논리구조에 주로 사용된다.
		  - 데이터를 다루는 최소단위가 1byte임으로 1byte의 크기를 가진다.
		 */
		
		// 변수명 answer, 값은 true 변수를 선언 및 초기화
		boolean answer = true;
		
		/*
		 4. 문자형 - char
		  - java는 Unicode문자 체계를 이용하기 때문에 2byte의 크기를 가진다.
		  - 'A' 
		 */
		
		char c = 'A';		// 문자 형태.
		char d = '\u0041';	// 유니코드 형태. 유니코드 문자 체계는 기본적으로 16진수
		char e = 65;		// 10진수(정수) 형태.
		
		System.out.println(d);
		
		/*
		 5. 정수형 - byte, short, int, long
		  - 기본 자료형은 int이다.
		  - 변수에 저장하려는 정수값의 범위에 따라 4개의 정수형 중 하나를 선택하면 된다.
		  - byte, short의 경우 크기가 작아서 범위를 넘어서는 경우가 많다.
		      그래서 대표형인 int를 많이 사용한다. + 자바는 기본적으로 4바이트 연산을 수행한다.
		 */
		// 정수값 50을 저장할 수 있는 변수를 선언 및 초기화
		byte by = 50;
		// 정수값 30_000을 저장할 수 있는 변수를 선언 및 초기화
		short sh = 30000;
		// 정수값 1000000을 저장할 수 있는 변수를 선언 및 초기화
		int in = 1000000;
		// 정수값 5000000000을 저장할 수 있는 변수를 선언 및 초기화
		long lo = 5000000000L; // long타입이라고 알려줘야 에러나지 않음
		
		byte qq = 126;
		System.out.println(qq); // 126
		qq++; // qq의 값을 1 증가시켜라
		System.out.println(qq); // 127
		qq++;
		System.out.println(qq); // overflow 발생. 변수가 저장할 범위를 넘었을 때 최소값부터 다시 표현하는 현상.
		
		/*
		 6. 실수형 - float, double
		  - 실수를 저장하는 데 사용된다. 
		  - float : 1+8+23			참고만 할 것(실수형이 long보다 훨씬 크다는 것만 알기)
		    double : 1+11+52
		  - 실수형 중 사용할 자료형을 선택할 때는 값의 범위만이 아닌
		      정밀도도 중요한 요소가 된다.
		      사용 빈도는 float이 더 높다
		 */
		// 3.14159254862 를 float타입의 변수에 저장하세요.
		float pi = 3.14159254862f;
		// 3.14159254862 를 double타입의 변수에 저장하세요.
		double pii = 3.14159254862;
		
		System.out.println(pi); // 소수점 아래 7자리까지 나옴
		System.out.println(pii);
		
		System.out.println(0.1 == 0.1f); // 왼쪽은 double값(실제로는 0.1 근사값d)
		System.out.println(0.5 == 0.5f); // 참고) 이클립스에서 같으면 노란줄이 뜨고, 아니면 안 뜬다
		// double타입은 2의 n승으로 표현한다. 때문에 2의 n승으로 표현 가능한 값일 경우 float, double값이 일치할 수 있다.
		
		/*
		 7. String : 문자열		참조형이지만 기본형처럼 사용할 수 있다. java에서 허용했기 때문.
		  - 기본값 : null			참조형의 모든 기본값은 null
		  - \를 제외한 모든 문자가 들어갈 수 있음. \뒤의 일부 문자(t,n 등)는 특수문자로 인식한다.
		  - 참조형은 기본형과 어떤 연산도 불가능하지만, String은 기본형과 합칠 수 있다(특이하게도)
		 	 
		 */
		String nnnn = null; // name변수가 들어갈 주소가 없음
		String name = ""; // 주소가 있는데 찾아가니 아무것도 없는 것
		
		String a3 = 7+"5"; // int 7이 String 5로 형변환되어 문자열 75가 출력됨
		System.out.println(a3);
		String a4 = " "+7; // 공백7
		System.out.println(a4);
		String a5 = 7 + 7 + "6";
		System.out.println(a5);  // int + int + String = 14 + "6" = 문자열 146
		String a6 = 7 + "6" + 7;
		System.out.println(a6); // int + String + int = 문자열76+7 = 문자열 767
		String s7 = "hi"+true;
		System.out.println(s7); // String + boolean = 문자열 hitrue
		
		/*
		 기본형의 형변환
		 
		  byte ---> short ---> int ---> long ---> float ---> double
		                       ^ 
		            char ------|
		 
		 이 때 4바이트보다 작은 변수는 4바이트로 자동 변환되어 계산됨.
		 따라서 byte + byte = int, short + char = int
		 */
		int i3 = 67;
		
		byte b3 = (byte)i3; // cast연산자. cast는 다른 형태로 바꾼다는 뜻. [바꾸고 싶은 타입]을 적는다.
		
		short s3 = (short)i3;
		
		long l3 = i3; // 문제가 없기 때문에 (long)을 생략할 수 있다.
		
		// 45.67과 23.1432의 합에서 정수부분만 저장해 주세요
		
		float f1 = 45.67f;
		float f2 = 23.1432f;
		int oo = (int)(f1 + f2);
		
		//1. 'F' 를 변수에 저장 aa1
		char aa1 = 'F';
		//2. 3.141592를 변수에 저장 aa2
		float aa2 = 3.141592f;
		//3. aa1과 aa2를 더했을 때 결과를 문자로 만들어 주세요
		char aa3 = (char)(aa1 + aa2);
		System.out.println(aa3);
	}

}
