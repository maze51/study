package y_exam;

public class Exam_02 {
	public static void main(String[] args) {
		
		/*
		[2-1]
					1byte	2byte	4byte	8byte
		논리형		boolean
		문자형				char
		정수형		byte	short	int		long
		실수형						float	double
		
		[2-2]
		
		주민등록번호: 13자리 정수 => int의 최대값을 넘어가므로 long을 사용		
		long regNo = 1111111111111L;
		
		[2-3] 다음의 문장에서 리터럴, 변수, 상수, 키워드를 적으시오.
		int i = 100;
		long l =100L;
		final float PI = 3.14f;
		
		리터럴: 100, 100L, 3.14f
		변수: i, l
		키워드: int, long, final, float
		상수: PI
		
		[2-4]
		b. Byte
		기본형이 아닌 이유는? int, double, boolean과 달리 Byte는 대문자를 사용하여 byte와 같지 않다. 더불어 java의 예약어 목록에 포함되어 있지 않다. 	
		
		
		[2-5] 다음 문장들의 출력결과를 적으세요. 
		 오류가 있는 문장의 경우, 괄호 안에 ‘오류’라고 적으시오.
			System.out.println(“1” + “2”) → (String + String => 12(문자열) )
			System.out.println(true + “”) → (boolean + String => Sting + String => "true"+"" => (String)true)
			System.out.println(‘A' + 'B') → (char + char => 각 문자에 해당하는 유니코드 값끼리 덧셈 연산. 각각이 2byte이므로 4byte int로 자동 형변환 => 65 + 66 => (int)131)
			System.out.println('1' + 2) → (char + int => int + int => '1'의 유니코드 값 + 2 => 49 + 2 => (int)51)
			System.out.println('1' + '2') → (char + char => 각 문자에 해당하는 유니코드 값끼리 덧셈 연산. 각각이 2byte이므로 4byte int로 자동 형변환 => 49 + 50 => (int)99)
			System.out.println('J' + “ava”) → (char + String => String + String => J + ava => (String)Java)
			System.out.println(true + null) → (null은 클래스 상단에서 변수로 선언되지 않았고, 더블쿼테이션을 사용해 문자열로 지정되지도 않은 문자 => 오류)
			
		[2-6]	
			키워드가 아닌 것: True(대문자), NULL(대문자), Class(대문자), System(대문자)
			키워드: if
			
		[2-7]
			변수의 이름으로 사용할 수 있는 것
				$ystem, If, 자바, $MAX_NUM
			사용할 수 없는 것
			 	channel#5, hello@com(사용할 수 없는 특수문자), 7eleven(숫자가 맨 앞), new(예약어) 
			
		[2-8]
			참조형 변수는 4바이트
			같은 크기의 기본형은 int, float
			
		[2-9]
			형변환을 생략할 수 있는 것
			d. float f = (float)l;	float은 long보다 저장할 수 있는 값의 범위가 커 자동 형변환 가능 
			e. i = (int)ch;			int는 char보다 저장할 수 있는 값의 범위가 커 자동 형변환 가능
			
			형변환을 생략할 수 없는 것
			a. b = (byte)i;			i는 byte보다 큰 int값. byte값의 범위에 포함되는 값이라도 자동 형변환 불가.
			b. ch = (char)b;		byte는 char와 저장할 수 있는 값의 범위가 달라 자동 형변환 불가.
			c. short s = (short)ch;	char는 short와  저장할 수 있는 값의 범위가 달라 자동 형변환 불가.
		
		[2-10]
			char타입의 변수에 저장될 수 있는 정수 값의 범위
			char타입은 2byte=16bit이고 음수값이 없어 16개 저장소 모두에 값을 저장할 수 있다.
			따라서 범위는 0부터 2의 16승-1 (-1을 하는 이유는 0이 포함되기 때문)
			
		[2-11]
			변수를 잘못 초기화 한 것
			a. byte b = 256;		256은 byte가 저장할 수 있는 -2의 7승에서 2의 7승-1을 벗어남
			b. char c = '';			char에는 단 하나의 문자만 저장할 수 있고, 아무 문자도 없는 것은 문자 하나가 아님
			c. char answer = 'no';	char에는 단 하나의 문자만 저장할 수 있다.
			d. float f = 3.14;		뒤에 L이 포함되지 않은 실수는 double타입. float타입에 그대로 초기화할 수 없다.
			
			변수를 잘 초기화 한 것
			e. double d = 1.4e3f;	double은 float보다 큰 범위를 갖고 있어 초기화 가능하다
			
		[2-13]
			타입과 기본값이 잘못 연결된 것
			c. float - 0.0		맞는 기본값은 0.0f
			e. long - 0			맞는 기본값은 0L
			f. String - ""		맞는 기본값은 null
			
			타입과 기본값이 잘 연결된 것
			a. boolean - false
			b. char - '\u0000'
			d. int - 0
		 */
		
	}

}
