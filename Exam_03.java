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
				=> 산술연산자는 쉬프트연산자보다 우선순위가 높다 => 1+2=3 
				=> 3 << 33 => 3 * 2^33 = 3 * 2^1 쉬프트연산자로 int값을 32번 밀면 원 위치로 돌아옴 = 6
				
				System.out.println(y >= 5 || x < 0 && x > 2);
				=> y=5이므로 y>=5는 true || x=2이므로 x < 0 && x > 2는 false 
				=> or결합 연산자는 어느 한 쪽만 true면 true => true
				
				System.out.println(y += 10 - x++);
				=> 산술연산자는 대입연산자보다 우선순위가 높다 => y += (10 - 2++)
				=> y += (10 - 2) 2인 이유? x뒤에 ++라 계산식에서는 아직 증가하지 않음
				y += 8 => y = y + 8 => 결과는 13 											이 시점에서 x=3, y=13
				
				System.out.println(x+=2);
				=> x += 2는 x = x + 2 = 5		x값은 위 식에서 1증가되었기 때문에 2가 아닌 3			x=5
				
				System.out.println( !('A' <= c && c <='Z') );
				=> c를 식에 대입하면 'A' <= 'A' && 'A' <='Z' => 'A' <= 'A' 와 'A' <='Z' 모두 true
				=> 전체 식에 논리부정 연산자 !가 사용되었으므로 결과는 false
				
				System.out.println('C'-c);
				=> 이항 연산자에서 int보다 작은 값끼리 계산할 경우 int로 자동 변환.
				=> 영문 대문자는 유니코드 상 순서대로 번호가 부여됨. 유니코드 상 C는 A보다 2 큰 번호.
				결과는 2
				
				System.out.println('5'-'0');
				=> 이항 연산자에서 int보다 작은 값끼리 계산할 경우 int로 자동 변환.
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
				numOfApples를 sizeOfBucket으로 나누면서 double로 형변환. 그 값에 0.9를 더하고 int로 재변환.
				float을 쓰지 않는 이유: 321이상의 1의 자리가 1인 수에서 오류.
				float타입의 정밀도는 7자리. a*10^n의 형태로 표현된 7자리의 10진수(1.234567*10^n)를 오차 없이 저장할 수 있다.
				(int)891을 10.0으로 나누면 89.10000000000, 10.0f로 나누면 89.0999984...
				
				int numOfApples = 123; 
				int sizeOfBucket = 10; 
				int numOfBucket = (int)(numOfApples/(double)sizeOfBucket+0.9);
				System.out.println("필요한 바구니의 수 :"+numOfBucket);
				
				@@ 바구니가 담을 수 있는 숫자가 바뀌어도 적용할 수 있는 식인가? 위 식은 10개일 때만 가능하므로
				공용으로 사용할 수 없다. 올림 수치가 계속 바뀌게 됨.
				
				다른 풀이
				사과 개수가 10의 배수라면 사과/바구니=필요한 수
				하지만 10의 배수가 아니라면 하나 더 많아야 함
				int numOfBucket = numOfApples%10 == 0 ? numOfApples/sizeOfBucket : numOfApples/sizeOfBucket + 1;
				
				@@ 여기서 numOfApples%10 보다는 numOfApples%sizeOfBucket이 적합!
				
				[3-3]
				양수일 때 '양수'를 출력
				num > 0 ? "'양수'"
				음수일 때 '음수'를 출력 을 추가
				num > 0 ? "양수" : num < 0 ? "음수" : 0
				 
				int num = 10;
				System.out.println(num > 0 ? "양수" : num < 0 ? "음수" : 0);
				
				@@ 마지막 0일때 출력되는 0은 타입을 맞춰 "0"으로 하는 것이 올바르다
				
				[3-4]
				1. 100으로 나눠 십, 일의 자리를 없앤다. 여기서 소수점 아래로 들어가는 십, 일의 자리는
				활용하지 않고 버릴 값이므로 굳이 형변환 과정을 거치지 않아도 무방하다. 
				2. 100을 곱해 완료
				num2/100 => 4 (.56은 버림)
				num2/100*100 => 400
				
				int num2 = 456;
				System.out.println(num2/100*100);
				
				@@ 다른 풀이
				100의자리 아래 수를 모두 빼는 방법
				num2-num%100 = 400
				
				[3-5] 
				1. num3을 10으로 나눠 일의 자리를 버림 처리한다.
				num3/10 => 33(.3은 버림)
				2. 거기에 10을 곱하고 1을 더한다.
				num3/10*10+1
				3. num3이 음수일 때는? 같은 방법으로 일의 자리를 버리고 1을 뺀다.
				num/10*10-1
				4. 삼항 연산자를 사용하여 두 경우를 한 식에 처리한다.
				num3 >=0 ? num3/10*10+1 : num3/10*10-1
				 
				int num3 = 333;
				System.out.println(num3 >=0 ? num3/10*10+1 : num3/10*10-1);
				
				[3-6]
				결과값은 num4%10과 더하면 항상 10이 나오게 됨
				왜? 크면서도 가장 가까운 10의 배수-num4 값이므로
				그래서  결과값을 구하는 식은 10-num4%10
				
				int num4 = 24;
				System.out.println(10-num4%10);
				
				@@ 다른 방법 (num4/10+1)*10 - num4
				
				
				[3-7]
				int fahrenheit = 100;
				float celcius =      ;
				System.out.println("Fahrenheit:"+fahrenheit);
				System.out.println("Celcius:"+celcius);
				
				1. 결과값을 구하기
				1-1 결과값을 구하는 식: 5/9*(fahrenheit-32)
				1-2 소수점 값을 올바르게 구하기 위해 5f/9*(fahrenheit-32)로 표현
				
				2. 결과값을 소수점 셋째자리에서 반올림하기
				2-1 c*100						결과값 c가 이미 float이므로 float*int는 float*float으로 자동 형변환됨. 굳이 100f로 쓸 필요 없음.
				2-2 (c*100+0.5)
				2-3 (int)(c*100+0.5) 
				2-4 (int)(c*100+0.5)/100f
				
				 
				float celcius = (int)(5f/9*(fahrenheit-32)*100+0.5)/100f;
				
				
				
				
				  [3-8]
				  */
				byte a = 10;
				byte b = 20;
				byte c = (byte)(a + b);
				
				char ch = 'A';
				ch = (char)(ch + 2);
				
				float f = 3f / 2;
				long l = 3000L * 3000 * 3000;
				
				float f2 = 0.1f;
				double d = 0.1;
				
				boolean result = (float)d==f2;
				
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
				  float은 int보다 큰 변수이므로 저장 가능하지만,
				   계산 결과값이 int로 나온 시점에서 소수점 이하가 사라짐.
				  3f / 2 로 수정하면 float / int => float / float으로 자동 형변환되어
				   출력값은 1.5
				   
				  @@ 둘 다 변수라면 하나를 (float)으로 캐스팅하기. 변수는 f만 붙여서 해결할 수 없으므로.
				  
				   문제점4: 정수형의 기본 자료형은 int
				  3000 * 3000 * 3000 = 270억
				  int가 저장할 수 있는 값의 범위를 넘어 오버플로우 발생
				  3000L * 3000 * 3000으로 수정하면
				  long * int * int => 모두 long으로 형변환. 결과값도 long
				   출력값은 27000000000
				  
				   문제점5 
				  double타입은 2의 n승으로 표현한다. 때문에 2의 n승으로 표현 가능하지 않은 값일 경우 0.1f != 0.1이 된다
				  true값을 얻고자 한다면 변수 f2와 d를 2의 n승으로 표현 가능한 값으로 수정한다. 
				  float f2 = 0.5f;
				  double d = 0.5;
				   출력값은 true
				  
				  @@ 다른 방법: double타입 쪽에 (float)을 붙여도 true
				  boolean result = d!=f2; 로 바꿔준다
				  boolean result = !(d==f2); 로 바꿔준다
				  sysout "result="+!result 로 바꿔준다
				  
				  
				   변수값을 수정하지 않고 true값을 얻고자 한다면 boolean result에서 d를 float으로 형변환한다.
				   그러면 0.1f ==0.1f가 되어 true값이 나온다
				   단 f2에 (double)을 붙여 0.1d == 0.1d처럼 만들면 false가 된다.
				  0.5f를 double로 형변환해도 0.5d와 다른 값이기 때문이다.  
				  
				   
				[3-9]
				영문 대문자+소문자+숫자일 때 true
				영문 대문자 조건식 =>	'A' <= ch1 && ch1 <= 'Z'
				영문 소문자 조건식 =>	'a' <= ch1 && ch1 <='z'
				숫자 조건식 =>			'0' <= ch1 && ch1 <='9'
				  
				char ch1 = 'z';
				boolean b2 = 'A' <= ch1 && ch1 <= 'Z' || 'a' <= ch1 && ch1 <='z' || '0' <= ch1 && ch1 <='9';
				 
				@@ 삼항 연산자를 세 번 중첩해서 쓰는 방법도 있음
				
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
