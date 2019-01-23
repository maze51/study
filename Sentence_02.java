package c_sentence;

import java.util.Scanner;			// java.util 은 패키지명 Scanner 는 클래스명. 이걸 갖다 쓰겠다. 이렇게 안 하면 매번 쓸 때마다 java.util.Scanner라고 넣어줘야 함.

public class Sentence_02 {
	public static void main(String[] args) {
		/*
		 1. 반복문( for, while, do while )
		  - 어떤 작업을 반복적으로 수행되도록 할 때 사용된다.
		  - 반복문은 주어진 조건이 만족하는 동안 주어진 문장을 반복적으로 수행함으로
		      조건식을 포함한다.
		  - for문은 반복횟수를 알고 있을 때 활용, while문은 반복횟수를 모를 때 사용한다.
		  - 공통 부분을 찾아내는 것이 가장 중요하다!
		    
		 2. for문
		  - 기본구조
		    for( 초기화 ; 조건식 ; 증감식 ){
		 		조건식을 만족할 때 "수행"될 문자
		    }
		 
		 */
		
//		System.out.println(0);
//		System.out.println(1);
//		System.out.println(2);
//		System.out.println(3);
//		System.out.println(4);
//		System.out.println(5);
//		System.out.println(6);
//		System.out.println(7);
//		System.out.println(8);
//		System.out.println(9);
//		System.out.println(10);
		
		// 범위는? 0 -> 10 증가량은? 1씩

		for(int i=0;i<11;i++) {
			System.out.println(i);
		}

		// 1~10까지의 합계를 구하세요.
//		int sum = 0;
//		sum += 1;
//		sum += 2;
//		sum += 3;
//		sum += 4;
//		sum += 5;
//		sum += 6;
//		sum += 7;
//		sum += 8;
//		sum += 9;
//		sum += 10;
		
		int sum = 0;
		for(int i=1;i<11;i++){			// i는 for문 영역 안에서 선언된 것. for문을 벗어나면 i는 없어진다.
			sum+=i;
		}
		System.out.println(sum);		// 반복되는 부분에 Sysout은 없다. 그러므로 수행될 부분에 Sysout을 넣는 실수를 하지 않도록
		
		// 34(미포함) ~ 95(포함)까지의 홀수일 때의 합계를 구하세요
		// 35 ~ 96
		
		int sum2 = 0;
		
		for(int i=35;i<96;i++){
			if(i%2!=0) {
				sum2+=i;
			}
		}
		System.out.println(sum2);
		
		// 2 * 1 = 2
		// 2 * 2 = 4
		// 2 * 3 = 6		앞 2는 숫자, 두번째는 1증가하는 변수, 마지막은 결과. sysout하나로 문장을 만들기
		

//		System.out.println( 2 + " * " + 1 + " = " + 2*1);
//		System.out.println( 2 + " * " + 2 + " = " + 2*2);
//		System.out.println( 2 + " * " + 3 + " = " + 2*3);
//		System.out.println( 2 + " * " + 4 + " = " + 2*4);
//		System.out.println( 2 + " * " + 5 + " = " + 2*5);
//		System.out.println( 2 + " * " + 6 + " = " + 2*6);
//		System.out.println( 2 + " * " + 7 + " = " + 2*7);
//		System.out.println( 2 + " * " + 8 + " = " + 2*8);
//		System.out.println( 2 + " * " + 9 + " = " + 2*9);
		
//		for(int i=1;i<10;i++) {
//			System.out.println( 2 + " * " + i + " = " + 2*i);
//		}
//		
//		for(int i=1;i<10;i++) {
//			System.out.println( 3 + " * " + i + " = " + 3*i);
//		}
//		
//		for(int i=1;i<10;i++) {
//			System.out.println( 4 + " * " + i + " = " + 4*i);
//		}
//		
//		for(int i=1;i<10;i++) {
//			System.out.println( 5 + " * " + i + " = " + 5*i);
//		}
//
//		for(int i=1;i<10;i++) {
//			System.out.println( 6 + " * " + i + " = " + 6*i);
//		}
//
//		for(int i=1;i<10;i++) {
//			System.out.println( 7 + " * " + i + " = " + 7*i);
//		}		
//		
//		for(int i=1;i<10;i++) {
//			System.out.println( 8 + " * " + i + " = " + 8*i);
//		}
//		
//		for(int i=1;i<10;i++) {
//			System.out.println( 9 + " * " + i + " = " + 9*i);
//		}
		
//		for(int i=3;i<10;i+=2) {
//			for(int j=2;j<10;j+=2) {
//					System.out.println( i + " * " + j + " = " + j*i);
//			}
//		}
		// 홀수단 이면서 짝수곱일때 구구단 출력
		for(int i=2;i<10;i++) {
			for(int j=1;j<10;j++) {
				if(i%2==1 && j%2==0) {
					System.out.println( i + " * " + j + " = " + j*i);
				}
			}
		}
		
		/*
		 3. while문
		  - 조건식과 수행되어야 할 블럭{}만으로 구성되어 있다.
		      카운터로 사용할 변수의 증감식을 함께 사용하여 for문과 같이 구성할 수 있다.
		  - 구조
		     while(조건식){
		     	조건식이 true일 때 수행될 문장
		     }
		 */
		
//		for (int i=0;i<11;i++) {
//			System.out.println(i);
//		}
		
		int num = 0;
		while(num<11){
			System.out.println(num);
			num++;
		}
		
		// 1-10까지 합계를 while문으로 나타내라
		
		int hap = 1;
		int result = 0;
		while(hap<11){
			result+=hap;
			hap++;
		}
		System.out.println(result);
		
		// 34(미포함) ~ 95(포함)까지의 홀수일 때의 합계를 while문으로 나타내라
		
		int h2 = 35;
		int result2 = 0;
		while(h2<96) {
			if(h2%2==1) {
				result2 += h2;
			}
			h2++;
		}
		System.out.println(result2);
		
		// 구구단 전부 출력하기를 while문으로
		
//		int dan = 2;
//		int gob = 1;
//		while(dan<10) {
//			while(gob<10) {
//				System.out.println(dan + " * " + gob + " = " + dan*gob);
//				gob++;
//			}
//			dan++;
//			gob=1;
//		}
		
		// 홀수단 이면서 짝수곱일 때 구구단 출력하기를 while문으로
		int dan = 2;
		int gob = 1;
		while(dan<10) {
			while(gob<10) {
				if(dan%2==1 && gob%2==0) {
					System.out.println(dan + " * " + gob + " = " + dan*gob);
				}
				gob++;
			}
			dan++;
			gob=1;
		}
		//1~? 까지 더했을 때 합이 100보다 커지는 ?의 값은 무엇인가? 정답은 14
		//int n(순차적으로 증가하는 변수) = 1;
		//int sum(합계를 저장할 변수) = 0;	
		//조건: sum이 100보다 작거나 같을 때 (sum<=100)
		//sum+=n;
		//n++;
		//Sysout(number);
		//여기서 15가 나오는 이유는? 조건 완료 후에 ++가 붙어 쓸데없이 증가하기 때문
		//고치는 방법 1. n++를 위로 보낸다&n을 0으로 초기화한다 
		//if(sum>100){ break; } 추가
		
//		int gg = 0;
//		int ff = 0;
//		while(gg<101) {
//			ff++;
//			gg += ff;
//		}
//		System.out.println(ff);
		int gg = 0; // 합계를 저장할 변수
		int ff = 1; // 순차적으로 증가하는 변수
		while(true) {
			gg += ff;
			if(gg > 100) {
				break;
			}
			ff++;
		}
		System.out.println(ff);
		
		/*
		 4. do-while
		  - while문의 변형으로 기본 구조는 while문과 유사하나
		    {}을 먼저 수행하고 조건식을 판단한다.
		  - 기본구조
		    do{
		    	수행될 문장
		    } while(조건식);
		 
		 */
		
//		Scanner sc = new Scanner(System.in);		// 참조형 변수		콘솔창에서 입력받을 수 있는 환경 구성. in이 받아들일 준비
		
//		System.out.println("문자를 입력해주세요");
//		String str = sc.next();						// 콘솔창에 "문자열"을 받을 준비. 기다렸다 내가 친 문자열을 그대로 저장. 문자열 형태로만 저장됨.
//		System.out.println(str);
		
		
		// 문장을 입력 받아서 입력받은 문장을 출력		단 역슬러시(\)는 뒤가 특수문자라 인식불가
		// 단, 입력받은 문장이 "x"이면 종료 
//		String str = "";
//		do{
//			System.out.println("문자를 입력해주세요");
//			str = sc.next();
//			System.out.println(str);
//		} while(!str.equals("x"));								//do {}블럭과 while()의 각 괄호 영역은 다른 영역임에 주의
		
//		int result5 = sc.nextInt();							// int값("정수값")만 받음. 문자가 입력되면 에러
//		System.out.println(result5);
		
		for (int i = 0; i < 11; i++) {
			if(i%2==1){
				System.out.println(i);		// 둘 다 만족해야 수행
			}
		}
											// 위 아래 식은 같은 식
		for (int i = 0; i < 11; i++) {
			if(i%2==0){
				continue;					// if조건문이 false면 다음 문장 Sysout수행. true면 continue를 만나 아무것도 수행하지 않고 다음 단계로
			}								// 짝수일 때는 진행해라. 뒷문장은 모조리 건너뛰고 반복문의 다음 단계(for문은 for문의 초기단계인 증감식)를 수행한다. 중첩 반복문일 경우는 가장 가까운 반복문으로 간다.
			System.out.println(i);
		}
		
//		while문의 초기단계는 조건식. 그러므로 continue를 만나면 조건식으로 간다.
		
		
		
		



	}

}
