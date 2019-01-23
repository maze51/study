package d_array;

public class Array_01 {
	public static void main(String[] args) {
		
		/*
		 1. 배열(Array)이란?
		  - "같은 타입"의 여러 변수를 하나의 묶음으로 다루는 것
		  - 3과목 점수
		    : int mathScore = 20;
		    : int korScore = 70;
		    : int engScore = 60;
		  - int[] a;	int형 배열. 참조형.
		 
		 2. 배열의 선언
		  - 원하는 타입의 변수를 선언하고 변수 또는 타입에 배열임을 알리는 대괄호[]를 붙이면 된다.
		  
		    ex) 변수타입[] 변수명; - 권장. 아래처럼 하면 변수타입만 보고 기본형으로 잘못 인식할 수 있음.
		            변수타입 변수명[];
		 
		 3. 배열의 생성			생성이라고 하는 이유? 주소를 새로 생성해서 여러개를 쑤셔넣기 위해.
		  - 배열을 선언한 다음에는 배열을 생성해야 한다.
		  - 배열 생성을 위해서는 연산자 'new'와 함께 배열의 크기를 지정해 주어야 한다.
		    ex) int[] a = new int[5];			각 방은 해당 타입의 기본값으로 초기화된다(int=0).
		    ex) int[] b = new int[]{0,10,20,30,40};  
		    ex) int[] c = {0,10,20,30,40};		이 형태는 둘로 분할할 수 없다. 선언과 생성을 함께해야.
		  
		 */
		
		int[] a = new int[5];	// 5개의 방 => 방의 크기는? a.length : 5
		
		
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		
		// 0 : 10
		a[0] = 10;
		// 1 : 20
		a[1] = 20;
		// 2 : 30
		a[2] = 30;
		// 3 : 40
		a[3] = 40;
		// 4 : 50
		a[4] = 50;
		
		for(int i=0;i<a.length;i++){
			a[i] = 10*(i+1);
			System.out.println(a[i]);
		}
		
		int [] b;
		b = new int[]{0,10,20,30,40};		//int[] a = new int[]{0,10,20,30,40};를 선언부/생성부 분할하면 이렇게 됨
		
		int[] c = {0,10,20,30,40};
		
		// 1. 7개의 정수값을 저장할 수 있는 변수 score를 선언 및 생성해주세요.
		int[] score = new int[7];
		
		// 2. score변수 각 방의 값을 출력해주세요.
//		System.out.println(score[0]);
//		System.out.println(score[1]);
//		System.out.println(score[2]);
//		System.out.println(score[3]);
//		System.out.println(score[4]);
//		System.out.println(score[5]);
//		System.out.println(score[6]);
		
		for(int i=0;i<score.length;i++) {
			System.out.println(score[i]);
		}
		// 3. score의 각 방의 값을 0~100사이의 랜덤한 값을 저장해주세요.
		// 수작업
//		score[0] = (int)(Math.random()*101);
//		score[1] = (int)(Math.random()*101);
//		score[2] = (int)(Math.random()*101);
//		score[3] = (int)(Math.random()*101);
//		score[4] = (int)(Math.random()*101);
//		score[5] = (int)(Math.random()*101);
//		score[6] = (int)(Math.random()*101);
		// 반복문
		for(int i=0;i<score.length;i++){
			score[i] = (int)(Math.random()*101);
		}
		
		for(int i=0;i<score.length;i++){			// score[i]값 확인용
			System.out.println(score[i]);
		}
		
		// 4. score 각 방 값의 합을 구해주세요
		// 수작업
//		int sum = 0;
//		sum = score[0] + score[1] + score[2] + score[3] + score[4] + score[5] + score[6];
		
		// 반복문
		int sum2 = 0;
		for(int i=0;i<score.length;i++){
			sum2 += score[i];
		}
		
		// 5. score 모든 방의 평균을 구해주세요. 단, 소수점 셋째자리에서 반올림하여 둘째 자리까지 표현해주세요.
		float avg = 0.0f;
		avg = (int)((sum2 / (float)score.length)*100)/100f;
		System.out.println("평균: "+avg);
		
		// 6. (랜덤 범위에 관계없이) 최대값과 최소값 구하기
		int max = score[0];
		int min = score[0];
		for(int i=0;i<score.length;i++){
			if(score[i]>max) {
				max = score[i];
			} //end of max
			
			if(score[i]<min) {
				min = score[i];
			} //end of min
		}
		System.out.println("최대값: "+max+"  최소값: "+min);
		
		
	}
}
