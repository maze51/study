import java.util.Arrays;


public class Exam_exercise {
	public static void main(String[] args) {
		int[] lotto = new int[6];
		for(int i=0;i<lotto.length;i++){
			lotto[i] = (int)(Math.random()*45)+1;
			
		}
		System.out.println(Arrays.toString(lotto));
		
		
//		int sum = 0;
//		for(int i=1;i<11;i++){
//			sum+=i*(11-i);
//		}
//		System.out.println(sum);
		
		
//		[4-12]
//		
//		for(int gob=1;gob<4;gob++) {
//			for(int dan=2;dan<5;dan++) {
//				System.out.print(dan + "*" + gob + "=" + dan*gob);
//				System.out.print("\t");
//				}
//			System.out.println();
//			}
//		System.out.println();
//		for(int gob=1;gob<4;gob++) {
//			for(int dan=5;dan<8;dan++) {
//				System.out.print(dan + "*" + gob + "=" + dan*gob);
//				System.out.print("\t");
//				}
//			System.out.println();
//			}
//		System.out.println();
//		for(int gob=1;gob<4;gob++) {
//			for(int dan=8;dan<10;dan++) {
//				System.out.print(dan + "*" + gob + "=" + dan*gob);
//				System.out.print("\t");
//			}
//			System.out.println();
//		}
	
	
	
		//[4-2] 1부터 20까지의 정수 중에서 2 또는 3의 배수가 아닌 수의 총합을 구하시오.

		//		1. 2 또는 3의 배수가 아닌 수를 구하기	i%2 != 0 && i%3 != 0)
		//		2. 이들의 총합 구하기 sum += i

//		int sum = 0;
//		
//		for(int i=1;i<=20;i++) {
//			if(i%2 != 0 && i%3 != 0) {
//				sum += i;
//			}
//		}
//		System.out.println(sum);

		//		[4-3]
		//		1. 1부터 10까지의 결과
		//		2. 그 결과를 구하기 위해 10번 중첩되는 동안, 매 과정마다 결과값을 더하기

//		int sum2 = 0;
//		int sum3 = 0;
//		for(int i=1;i<=10;i++){
//			sum2 += i;
//			sum3 += sum2;
//		}

//		System.out.println("sum2: "+sum2);
//		System.out.println("sum3: "+sum3);
		
		// [4-4] 1+(-2)+3+(-4)+... 과 같은 식으로 계속 더해나갔을 때, 몇까지 더해야 총합이 100이상이 되는지 구하시오.
		
//		int a = 0; // 시작값&증가시킬 값. 본 식에서는 1이지만 마지막에 1증가 현상을 방지하기 위해 0으로 설정한다
//		int b = 0; // 계속 더한 결과값
//		int c = -1; // 부호 바꾸기용
//		
//		while(b<100) {		// 합계값 b가 100이상이면 반복문을 중지한다
//			a++;			// 위로 보낸 이유? 마지막에 1증가 현상을 방지하기 위해
//			if(a%2==0) {
//				a *= c; 	// a가 2의 배수일 때 음수로 변환한다
//			}
//			b+=a;			// b에 a를 더해 준다 
//			
//			if(a < 0) {
//				a = -a;		// a의 절대값을 취한다. 그렇지 않으면 1 -2 3 -4와 같이 증가시킬 수 없다
//			}
//		}
//		System.out.println(a);
		
		//[4-4] 다른 방법(미완)
//		int num = 1;
//		int result = 0;
//		
//		for(;result<100;num++) {
//			if(num%2==0) {
//				result -= num;
//			} else {
//				result += num;
//			} // end of if
//		} // end of for
//		System.out.println("횟수: "+num);

////		[4-15]
//		int number = 22321;
//		int tmp = number;
//		int result =0; // 변수 number를 거꾸로 변환해서 담을 변수
//		while(tmp !=0) {
//			result=(result*10)+(tmp%10);				// tmp의 각 자리수를 뽑아 자리수*10과 더한다
//			tmp /= 10;
//		}
//		if(number == result)
//			System.out.println( number + "는 회문수 입니다.");
//		else
//			System.out.println( number + "는 회문수가 아닙니다.");
		
		
//		
		
		//		[4-14]
//		int answer = (int)(Math.random()*100)+1;
//		int input = 0; // 사용자입력을 저장할 공간
//		int count = 0; // 시도횟수를 세기위한 변수
//		// 화면으로 부터 사용자입력을 받기 위해서 Scanner클래스 사용
//		java.util.Scanner s = new java.util.Scanner(System.in);
//		do {
//			count++;
//			System.out.print("1과 100사이의 값을 입력하세요 :");
//			input = s.nextInt(); // 입력받은 값을 변수 input에 저장한다.
//			if(input>answer) {
//				System.out.println("더 작은 수를 입력하세요.");
//			} else if(input<answer) {
//				System.out.println("더 큰 수를 입력하세요.");
//			} else {
//				System.out.println("맞췄습니다.");
//				System.out.println("시도횟수는 "+count+"번입니다.");
//				break;
//			}
//		} while(true);


		
//		[4-11]
		// Fibonnaci 수열의 시작의 첫 두 숫자를 1, 1로 한다.
//				int num1 = 1;
//				int num2 = 1;
//				int num3 = 0; // 세번째 값
//				System.out.print(num1+","+num2);
//				for (int i = 0 ; i < 8 ; i++ ) {
//					num3 = num1 + num2;
//					System.out.print(","+num3);
//					num1=num2;
//					num2=num3;
//				}
//		
//		[4-13]
//		String value = "23o52";
//		char ch = ' ';
//		boolean isNumber = true;
//		
//		for(int i=0; i < value.length() ;i++) {
//			ch=value.charAt(i);
//			if(!('0'<=ch && ch<='9')) {
//				isNumber = false;
//				break;
//			}
//		}
//		if (isNumber) {
//			System.out.println(value+"는 숫자입니다.");
//			} else {
//			System.out.println(value+"는 숫자가 아닙니다.");
//			}
		
//		[4-12]
//		중첩 for문. 단은 2~9, 곱은 1~3
//		for(int i=2;i<=9;i++) {
//			for(int j=1;j<=3;j++) {
//				System.out.println(i+"*"+j+"="+i*j);
//			}
//		}
		
//		[4-10]
//		int num = 12345;
//		int sum = 0;
//		while(num>0) {
//			sum+=num%10;
//			num/=10;
//		}
//		System.out.println("sum="+sum);
		
		
//		[4-9] 		[실행결과] 15
//		String str = "12345";
//		int sum = 0;
//		
//		for(int i=0;i<str.length(); i++) {
//			sum+=str.charAt(i)-'0';
//		}
//		System.out.println("sum="+sum);
		

//		[4-8] 방정식 2x+4y=10의 모든 해를 구하시오. 단, x와 y는 정수이고 각각의 범위는 0<=x<=10, 0<=y<=10
//		1. 중첩 for문으로 각각의 x, y를 표현 2. if문으로 식에 맞는 변수를 출력
//		for(int i=0;i<=10;i++) {
//			for(int j=0;j<=10;j++) {
//				if(2*i+4*j==10) {
//					System.out.println("x="+i+", y="+j);
//				}
//			}
//		}

		
//		[4-6] 두 개의 주사위를 던졌을 때, 눈의 합이 6이 되는 모든 경우의 수를 출력하는 프
//		로그램을 작성하시오.
		
//		1. 두 개의 주사위를 던지기 - 중첩 for문	2. 눈의 합이 6이 되는 경우의 수를 출력 - if문
//		for(int i=1;i<=6;i++) {
//			for(int j=1;j<=6;j++) {
//				if(i+j==6) {
//					System.out.println("[" + i + ", " + j + "]");
//				}
//			}
//		}
		
//		[4-5]
		/*	1단계
		int i = 0;
		while(i<=10){
			(안쪽 for문)
			i++;
		}
		*/
		/* 2단계
		int i = 0;
		int j = 0;
		while(i<=10){
			while(j<=i){
				System.out.print("*");
				j++;
			}
			System.out.println();
			i++;
		}
		*/
//		int i = 0;
//		int j = 0;
//		while(i<=10){
//			while(j<=i){
//				System.out.print("*");
//				j++;
//			}
//			System.out.println();
//			i++;
//			j=0;		// i가 1증가해 안쪽 for문이 다시 반복될 때마다 j=0이 됨을 반영하기 위해
//		}

		

		//		int num4 = 2_111_222_222;
		//		System.out.println(10-num4%10);

		//		int num = 0;
		//		System.out.println(num > 0 ? "양수" : num < 0 ? "음수" : "0");


		//		int numOfApples = 2341; 
		//		int sizeOfBucket = 10; 
		//		int numOfBucket = numOfApples%10 == 0 ? numOfApples/sizeOfBucket : numOfApples/sizeOfBucket + 1;
		//		System.out.println("필요한 바구니의 수 :"+numOfBucket);


		//		[3-7]
		//		1. 결과값을 소수점 셋째자리에서 반올림해 둘째자리까지 표현하기
		//		1-1 c*100f	1-2 (c*100f+0.5)	1-3 (int)(c*100f+0.5) 1-4 (int)(c*100f+0.5)/100f
		//		2. 결과값 구하기
		//		2-1 c=5/9*(f-32)	2-2 소수점 아래 값을 나타내기 위해 5f/9*(f-32)

		//		[3-5]
		//		int num3 = -2_043_452_707;
		//		System.out.println(num3 >=0 ? num3/10*10+1 : num3/10*10-1);

		//		[3-4]
		//		100의 자리 이하를 버리고자 한다면 우선 100으로 나눠 버릴 부분을 소수점 아래로 보내고, 100을 곱해 완료
		//		int num = 66766;
		//		System.out.println(num/100*100);

		//		[3-2]
		//		int numOfApples = 322; 
		//		int sizeOfBucket = 10; 
		//		int numOfBucket = (int)(numOfApples/(float)sizeOfBucket+0.9);
		//		System.out.println("필요한 바구니의 수 :"+numOfBucket);
		//		
		//		int pp = 321;
		//		float ll = 10.0f;
		//		float mmm = pp / ll;
		//		System.out.printf("result : %20.20f", mmm);

		//		int uu = 10;
		//		int hh = 50;
		//		System.out.println("--절취선--"+ ++uu);		// 11
		//		System.out.println(hh += 1000);			// 1050
		//		System.out.println(uu+hh);				// 1061
		//		System.out.println("test:"+ ++uu);		// 12
		//		System.out.println(hh += 1000);			// 2050
		//		System.out.println(uu+hh);				// 2062
		//		System.out.println("HH값:  "+hh);		// 2050
		//		System.out.println(hh -= 1000 - hh++);	// 2050 -= -1050	hh = 2050 - -1050	3100
		//		System.out.println("HH값:  "+hh);		// 3100
		//		System.out.println("test:"+ hh + 100);	// String과 더해서 죄다 String화!
		//		System.out.println("test:"+ uu + hh);	// 이것도!

	}

	}
