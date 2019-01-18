package y_exam;

public class Exam_03 {
	public static void main(String[] args) {

		/*
		 [3-1] 다음 연산의 결과를 적으시오.
		 class Exercise3_1 {
			public static void main(String[] args) {
				int x = 2;
				int y = 5;
				char c = 'A'; // 'A'의 문자코드는 65
				
				System.out.println(1 + x << 33);
				=> 3 << 33 => 3 * 2^33 = 3 * 2^1 쉬프트연산자로 int값을 32번 밀면 원 위치로 돌아옴 = 6
				
				System.out.println(y >= 5 || x < 0 && x > 2);
				=> y=5이므로 y>=5는 true || x=2이므로 x < 0 && x > 2는 false 
				=> or결합 연산자는 어느 한 쪽만 true면 true => true
				
				System.out.println(y += 10 - x++); => y += (10 - 2) 2인 이유? x뒤에 ++라 계산식에서는 아직 증가하지 않음
				y += 8 => y = y + 8 => 결과는 13 											이 시점에서 x=3, y=13
				
				System.out.println(x+=2);
				=> x += 2는 x = x + 2 = 5		x값은 위 식에서 1증가되었기 때문에 2가 아닌 3			x=5
				
				System.out.println( !('A' <= c && c <='Z') );
				=> c를 식에 대입하면 'A' <= 'A' && 'A' <='Z' => 'A' <= 'A' 와 'A' <='Z' 모두 true
				=> 전체 식에 논리부정 연산자 !가 사용되었으므로 결과는 false
				
				System.out.println('C'-c);
				=> 영문 대문자는 유니코드 상 순서대로 번호가 부여됨. 유니코드 상 C는 A보다 2 큰 번호.
				결과는 2
				
				System.out.println('5'-'0');
				=> 숫자는 유니코드 상 순서대로 번호가 부여됨. 5는 0보다 5 큰 번호.
				결과는 5
				
				System.out.println(c+1);
				=> c + 1 은 char + int => int + int => A의 문자코드 65 + 1
				결과는 66
				
				System.out.println(++c);
				=> 증감연산자는 타입을 자동 변환하지 않음. 따라서 char c는 증감연산 후에도 char타입을 유지.
				++가 변수명 앞에 붙었으므로 c값이 먼저 증가되고 증가된 값이 출력됨.
				문자코드 65에서 1증가된 66에 해당하는 문자는 B
				결과는 B																	이 시점에서 c='B'
				
				System.out.println(c++);
				=> 위 식과 달리 ++가 변수명 뒤에 붙었으므로 c값을 출력하고 값이 1 증가됨.
				단 위 식에서 c값이 1 증가되었으므로 'A'가 아닌 'B'가 출력됨.
				결과는 B																	이 시점에서 c='C'
				
				System.out.println(c);
				=> 위 식에서 c값이 다시 1 증가되었으므로 'B'가 아닌 'C'가 출력됨.
				결과는 C
		 		
		
				[3-2]
				사과가 120개라면 바구니 12개가 필요. 121개는 13개, 129개도 13개. 결론적으로 1의 자리에서 올림하면 해결.
				사과 갯수를 10으로 나누고 float으로 형변환. 그 값에 0.9를 더하고 int로 재변환
				int numOfApples = 123; 
				int sizeOfBucket = 10; 
				int numOfBucket = (int)(numOfApples/10f+0.9);
				System.out.println("필요한 바구니의 수 :"+numOfBucket);
				
				 
				 [3-3]
				 양수일 때 '양수'를 출력
				 num > 0 ? "'양수'"
				 음수일 때 '음수'를 출력 을 추가
				 num > 0 ? "양수" : num < 0 ? "음수" : 0
				 
				 int num = 10;
				 System.out.println(num > 0 ? "양수" : num < 0 ? "음수" : 0);
				 
				 [3-4]
				 십, 일의 자리를 소숫점 아래로 쫒아내고, 다시 곱해 불러오기
				 num2/100f => 4.56
				 (int)(num2/100f) => 4
				 (int)(num2/100f)*100 => 400
				 
				 int num2 = 456;
				 System.out.println((int)(num2/100f)*100);
				 
				 [3-5]
				 일의 자리를 소숫점 아래로 보내고 다시 곱하고 거기에 ++1
				 num3/10f => 33.3
				 (int)(num3/10f) => 33
				 (int)(num3/10f)*10 => 330
				 
				 
				 int num3 = 333;
				 System.out.println((int)(num3/10f)*10+1);		/ 증감연산자 사용 가능한가? 생각좀
				 
				 [3-6]
				 결과값은 num4%10과 더하면 항상 10이 나오게 됨
				 왜? 크면서도 가장 가까운 10의 배수이므로
				 그래서  10-num4%10이 정답
				 
				 int num4 = 100;
				 System.out.println(10-num4%10);
				
				 [3-7]
				 int fahrenheit = 100;
				 float celcius =      ;
				 System.out.println("Fahrenheit:"+fahrenheit);
				 System.out.println("Celcius:"+celcius);
				
				1. 결과값을 구하는 식: (5/9)*(fahrenheit-32)
				여기서 소수점 값을 올바르게 구하기 위해 ((float)5/9) 로 캐스팅
				((float)5/9)*(fahrenheit-32) = 37.77778
				
				2. 결과값을 소수점 셋째자리에서 반올림하기
				 값이 55.5546일 때
				 값*100 => 5555.46
				 +0.5 => 5555.96
				 (int)형변환 => 5555
				 /100f => 55.55 
				 (int)(결과값*100+0.5)/100f
				 
				 float celcius = (int)(((float)5/9)*(fahrenheit-32)*100+0.5)/100f;
				 
				  [3-8]
				  */
				byte a = 10;
				byte b = 20;
				byte c = (byte)(a + b);
				
				char ch = 'A';
				ch = (char)(ch + 2);
				
				float f = 3f / 2;
				long l = 3000L * 3000 * 3000;
				
				float f2 = 0.5f;
				double d = 0.5;
				
				boolean result = d==f2;
				
				System.out.println("c="+c);
				System.out.println("ch="+ch);
				System.out.println("f="+f);
				System.out.println("l="+l);
				System.out.println("result="+result);
				
				 /*
				   문제점1: 4바이트보다 작은 변수형끼리의 계산은 int로 자동 형변환되어 계산
				  byte + byte => int + int = int
				  int를 byte에 그대로 저장할 수 없어 오류 발생
				  byte c에 결과값을 저장하고자 한다면 (byte)로 캐스팅
				  byte c = (byte)(a + b);	출력값은 c=30
				  
				   문제점2: 정수형의 기본 자료형은 int
				  char + int => int + int = int
				  int를 char에 그대로 저장할 수 없어 오류 발생
				  char ch에 결과값을 저장하고자 한다면 (char)로 캐스팅
				  ch = (char)(ch + 2);		출력값은 ch=C
				  
				   문제점3: 정수형의 기본 자료형은 int
				  int / int = int
				  float은 int보다 큰 변수이므로 저장은 가능하지만,
				   계산 결과값이 int로 나온 시점에서 소수점 이하가 사라짐.
				  3f / 2 로 수정하면 float / int => float / float으로 자동 형변환되어
				   출력값은 1.5
				  
				   문제점4: 정수형의 기본 자료형은 int
				  3000 * 3000 * 3000 = 270억
				  int가 저장할 수 있는 값의 범위를 넘어 오버플로우 발생
				  3000L * 3000 * 3000으로 수정하면
				  long * int * int => 모두 long으로 형변환. 결과값도 long
				   출력값은 27000000000
				   
				   문제점5: double타입은 2의 n승으로 표현한다. 때문에 2의 n승으로 표현 가능하지 않은 값일 경우 0.1f != 0.1이 된다
				  true값을 얻고자 한다면 변수 f2와 d를 2의 n승으로 표현 가능한 값으로 수정한다. 
				  float f2 = 0.5f;
				  double d = 0.5;
				   출력값은 true
				  
				   
				   [3-9]
				  영문 대문자+소문자+숫자일 때 true
				  영문 대문자 조건식 =>	'A' <= ch1 && ch1 <= 'Z'
				  영문 소문자 조건식 =>	'a' <= ch1 && ch1 <='z'
				  숫자 조건식 =>			'0' <= ch1 && ch1 <='9'
				  
				  char ch1 = 'z';
				  boolean b2 = 'A' <= ch1 && ch1 <= 'Z' || 'a' <= ch1 && ch1 <='z' || '0' <= ch1 && ch1 <='9';
				  
				   [3-10]
				   대문자->소문자	ch가 대문자일 때만 소문자로	코드는 32차이
				  char lowerCase = ( 1 ) ? ( 2 ) : ch;
				  1번식: ('A' <= ch7 && ch7 <= 'Z')
				  2번식: (char)(ch + 32)
				  
				  char ch7 = 'Q';
				  char lowerCase = ('A' <= ch7 && ch7 <= 'Z') ? (char)(ch7 + 32) : ch7;
				  System.out.println("ch:"+ch7);
				  System.out.println("ch to lowerCase:"+lowerCase);
				  */
				
				
		
		


		
	}

}
