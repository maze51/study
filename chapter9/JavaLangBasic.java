package i_javaLang;

public class JavaLangBasic {
	/*
	1. java.lang패키지
	  - 자바 프로그래밍의 기본이 되는 클래스들을 포함하고 있다.
	  	ex) String, System......
	  	
	2. Object클래스
	  - Object클래스는 멤버변수 없이 11개의 메서드로 이루어져 있다.
	  
	  - boolean equals(Object obj)		EqualsTest01, 02 참조
	  	: 매개변수로 객체의 참조변수(인자값으로 주소가 들어온다)를 받아서 비교하고 그 결과를 boolean값으로 알려준다(주소가 같은지 판단한다).
	  	: 참조변수에 저장된 주소값이 같은지를 비교할 수 밖에 없다.
	  	: 주소값이 아닌 멤버변수의 값을 비교하기 위해서는 equals메서드를 오버라이드해야 한다.
	  		ex) String, Date, File, wrapper클래스(기본형 8가지를 객체화해서 쓸 수있게 한 것들) (equals메서드가 이미 오버라이드된 클래스들)
	  		
	  - hashCode() - 10진수로 이루어져 있다.
	  	: 객체의 주소값을 이용해서 해시코드를 만들어 반환한다.
	  	: String클래스는 문자열의 내용이 같으면 동일한 해시코드를 반환한다.
	  	
	  - toString()
	  	: 해당 인스턴스의 정보를 문자열로 제공할 목적으로 작성
	  	: 기본 toString()
	  		return getClass().getName()+ "@" +Integer.toHexString(hascCode()); // 이름 가져오고 골뱅이 붙고 16진수 주소가 따라온다
	  		
	  - clone()		CloneTest01, 02, 03 참조
	  	: 얕은복사
	  	: 자신을 복제하여 "새로운 인스턴스"를 생성한다. (알아서 new해서 만들고 복사한다)
	  	   단순히 인스턴스변수의 "값만"을 복사해준다. (참조형은 주소를 복사해준다)
	  	   객체가 참조하고 있는 객체까지 복제하지 않는다.
	
	  - getClass()	GetClassTest 참조
	    : 자신이 속한 클래스의 Class객체를 반환하는 메서드(인스턴스에 대한 모든 정보를 가져올 수 있다. 반환타입이 Class) - 인스턴스를 얻어오는 방식이 총 3가지(new말고 두가지는??)
	    : 클래스로부터 객체를 얻는 방식(GetClassTest참조)
	    	1) 생성된 객체로부터 얻어오는 방식
	    	Class obj = new Card().getClass(); // 객체를 만들고 객체를 통해 얻어오는 방식
	    	
	    	2) 클래스 리터럴로부터 얻는 방법(클래스를 통채로 값으로 인식하는 방법) - 아주 드물게 쓰인다
	    	Class obj = Card.class;
	    	
	    	3) 클래스의 이름으로부터 얻는 방법 - 아주 많이 쓰인다
	    	Class obj = Class.forName("Card"); // classnotfound 익셉션이 많이 뜬다
	
	  - 나머지 메서드 6개는 쓰레드 기반
	
	3. String클래스
	  - 다른 언어에서는 문자열을 char[]로 다루었으나, 자바에서는 문자열을 위한 클래스를 제공한다.
	  	: 문자열을 합치거나 할 때 새로운 문자열을 가진 String인스턴스가 생성된다. (StringTest01참조)
	  	// ma에 클래스가 로드될 때, 클래스 내 문자열 "리터럴"들을 뽑아서 우선 heap메모리 별도 공간에 주소로 넣어둔다(아직 로드되지 않았음에도). 
	  	// 이 때 같은 문자열은 같은 주소로 넣는다(같은 문자열을 다른 주소에 넣지 않는다). 그리고 이걸 나중에 갖다 쓴다. -> 결합이 비효율적. 그래서 문자열 합할 때 StringBuffer를 쓴다
	  	: 문자열의 결합이 많이 필요한 경우에는 StringBuffer를 이용한다.
	  	
	  - String클래스의 생성자와 메서드	StringTest02참조
	  
	  - 문자 인코딩 변환
	  	: 한글 윈도우의 경우 문자 인코딩으로 CP949를 이용한다(이클립스 기본은 MS949).
	  	   하지만 우리는 UTF-8을 사용한다.
	  	   
	  - String.format
	  	: 기본형 값을 String으로 변환
	  	  1) 빈문자열을 더하는 방식
	  	  	int a = 10;
	  	  	String s = a +"";
	  	  	안좋은 방식. 메모리 낭비가 심하고 속도가 늦다.
	  	  2) valueOf()를 이용하는 방식
	  	  	String s1 = String.valueOf(a);
	  	  	
	  	: String을 기본형 값으로 변환
	  	  1) wrapper클래스를 이용
	  	  	String str = "100";
	  	  	int a = Integer.parseInt(str);	// int라는 기본형을 객체 형태로 만든 것
	  	  	// 잘못된 값이 들어오면 예외가 발생하므로 예외처리가 필요. 숫자가 맞는가?
	  	  
	  	  	int c = Integer.valueOf(str); // 내가 원하는 방식을 앞에 쓰면 알아서 바꿔준다
	  	  	
	  	  2) String을 기본형으로 변환시 10진수이다.
	  	  	String s = "200";
	  	  	int a = Integer.parseInt(s,4); // 200(4) => 32(10진수로 바꾸면)
	  	  	// 숫자가 해당 진수로 가능한 값인가? 예외처리 필요
	  	  
	4. StringBuffer클래스와 StringBuilder클래스	<문자열 합치기는 반드시 StringBuffer사용!>
	  - String클래스는 인스턴스를 생성할 때 지정된 문장을 변경할 수 없다.
	  - 문자열의 변경을 위해서는 StringBuffer클래스를 이용한다.
	  

	5. Math클래스		MyMathTest참조
	
	6. wrapper클래스	WrapperTest참조
	  - 객체지향의 개념은 모든 것을 객체로 다뤄야 한다.
	  - 8가지 기본형은 객체로 다루지 않는다.
	  - 때로는 기본형 변수도 어쩔 수 없이 객체로 다뤄야 하는 경우가 있다.
	  	: 매개변수로 객체를 요구할 때
	  	: 기본형이 아닌 객체로 저장되어야 할 때
	  	
	  	boolean |  Boolean
	  	char    |  Character -**
	  	byte    |  Byte
	  	short   |  Short
	  	int     |  Integer -**
	  	long    |  Long
	  	float   |  Float
	  	double	|  Double
	  	  	 
	  - 오토박싱 : 기본형 값을 wrapper클래스의 객체로 변환해 주는 기능
	  - 언박싱   : wrapper클래스의 객체를 기본형 값으로 변환해 주는 기능
	  
	7. StringTokenizer클래스(split과 많이 비슷함)(StringTokenizerTest 참조)
	  - 긴 문자열을 지정된 구분자를 기준으로 토큰(token)이라는 여러개의 문자열로 잘라내는데 사용된다.
	  - 단 한 문자의 구분자만 사용할 수 있다.
	
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
