package enumTest;

// 열거형(enum) ==> 서로 관련있는 상수들의 집합(클래스처럼 보이게 하는 상수)

public class EnumTest {
	public static final int YES = 1;
	public static final int NO = 0;
	
	public static final int ON = 1;
	public static final int OFF = 0;
	
	// City 열거형 객체 선언
	public enum City {서울, 부산, 대구, 광주, 대전} // 끝에 ;없음. 상수 이름이 문자 같더라도 ""붙이지 않는다. enum은 class, City는 클래스명(일종의 데이터 타입)에 해당
//	public enum City2 {서울, 부산, 대구, 광주, 대전}
	// 분리된 열거형 객체는 서로 관련없음(보이는 값이 같다 해도)
	
	public static void main(String[] args) {
//		if(EnumTest.YES == EnumTest.ON){
//			System.out.println("참");
//		}
//		if(City.서울 == City2.서울){
//			System.out.println("참");
//		}
		
		/*
		valueOf("열거형상수명"); ==> 지정된 열거형에서 '열거형상수명'과 일치하는 열거형 상수를 반환한다.
		name(); ==> 열거형 상수명을 문자열로 반환한다.
		ordinal(); ==> 열거형 상수가 정의된 순서값을 반환한다(0부터 시작).
		
		 */
		
		// City enum에서 '대구'를 가져온다.
		City city1 = City.valueOf("대구");
		System.out.println("name : " + city1.name());
		System.out.println("ordinal : " + city1.ordinal());
		System.out.println();
		
		// City enum에서 '서울'을 가져온다.
		City city2 = City.서울; // 위 valueOf와 같은 명령
		System.out.println("name : " + city2.name());
		System.out.println("ordinal : " + city2.ordinal());
		
		// enum의 비교는 '=='연산자를 이용해 할 수 있다. (equals를 쓰지 않고도)
		
		if(city1==City.대구){ // 여기서는 City.서울 식으로 쓸 수 있다
			System.out.println("같음");
		} else {
			System.out.println("다름");
		}
		
		// switch문에서 사용할 수 있다.
		switch(city1){
		case 서울 : // switch문 case에 쓸 때만 상수 이름을 직접 쓸 수 있다. City.서울 식으로 쓰면 오히려 오류 발생
			System.out.println("city1은 서울입니다");
			break;
		case 부산 :
			System.out.println("city1은 부산입니다");
			break;
		case 대구 :
			System.out.println("city1은 대구입니다");
			break;
		case 광주 :
			System.out.println("city1은 광주입니다");
			break;
		case 대전 :
			System.out.println("city1은 대전입니다");
			break;
		}
		
		System.out.println();
		for(City ci : City.values()){
			System.out.println(ci.name() + " ==> " + ci.ordinal());
		}
	}

}
