
public class Exam_exercise {
	public static void main(String[] args) {


		//[4-2] 1부터 20까지의 정수 중에서 2 또는 3의 배수가 아닌 수의 총합을 구하시오.

		//		1. 2 또는 3의 배수가 아닌 수를 구하기	!(i%2 == 0 || i%3 == 0)
		//		2. 이들의 총합 구하기 sum += i

//		int sum = 0;
//
//		for(int i=1;i<=20;i++) {
//			if(!(i%2 == 0 || i%3 == 0)) {
//				sum += i;
//			} // end of if
//		} // end of for
//		System.out.println(sum);

		//		[4-3]
		//		1. 1부터 10까지의 결과
		//		2. 그 결과를 구하기 위해 10번 중첩되는 동안, 매 과정마다 결과값을 더하기

//		int sum2 = 0;
//		int sum3 = 0;
//		for(int i=1;i<=3;i++){
//			sum2 += i;
//			sum3 += sum2;
//		}
//
//		System.out.println("sum2: "+sum2);
//		System.out.println("sum3: "+sum3);
		
		// [4-4] 1+(-2)+3+(-4)+... 과 같은 식으로 계속 더해나갔을 때, 몇까지 더해야 총합이 100이상이 되는지 구하시오.
		// 1. 총합이 100이상이면 while문을 벗어나고 그때까지 더한 값 hh를 출력한다
		//		while(hh<100) {} System.out.println(hh);
		// 2. 1, -2에서 시작해 2씩 증가하는 중첩 for문을 만들고 그 모두를 더한 값을 hh에 저장한다
		// 중첩 for문보다는 for문 하나에서 하나는 증가하고 하나는 감소하도록 만드는 게?
		// for(int i=1;;i+=2) {a+=i; b=-i-1;}
		// 왜 hh<100이 의미없을까?		전체를 while문으로?
		
		int hh = 0;
		int i = 1;
		int j = -2;
		
		while(hh<100) {
			hh = i + j;
			i += 2;
			j -= 2;
			hh = i + j + hh;
			if(hh + i>100) {
				hh = hh + i;
			}
		}
		System.out.println(hh);
			
		
		
		
//		[4-9] 		[실행결과] 15
		String str = "12345";
		int sum = 0;
		for(int l=0; l < str.length(); l++) {
		
		sum = (str.charAt(l));
		System.out.println(sum);
		
		}
//		System.out.println("sum="+sum);
		
		

//		[4-8] 방정식 2x+4y=10의 모든 해를 구하시오. 단, x와 y는 정수이고 각각의 범위는
//		0<=x<=10, 0<=y<=10 이다.	[실행결과] x=1, y=2	x=3, y=1	x=5, y=0
//		for(int i=0;i<=10;i++) {
//			for(int j=0;j<=10;j++) {
//				if(2*i+4*j==10) {
//					System.out.println("x="+i+", y="+j);
//				}
//			}
//		}

		
//		[4-6] 두 개의 주사위를 던졌을 때, 눈의 합이 6이 되는 모든 경우의 수를 출력하는 프
//		로그램을 작성하시오.
		
//		for(int i=1;i<=6;i++) {
//			for(int j=1;j<=6;j++) {
//				if(i+j==6) {
//					System.out.println("["+ i +", "+ j +"]");
//				}
//			}
//		}
		
		
//		[4-5]
//		int i=0;
//		int j=0;
//
//		while(i<=10) {
//			while(j<=i) {
//				System.out.print("*");
//				j++;
//			} // end of j
//			j=0;		// for문에서 j가 0부터 시작되어야 정상적으로 print가 반복됨. 때문에 초기화
//			i++;
//			System.out.println();
//		} // end of i
		

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
