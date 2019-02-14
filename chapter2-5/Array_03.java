package d_array;

import java.util.Arrays;

public class Array_03 {
	public static void main(String[] args) {
		/*
		1. 다차원 배열
		 - 자바에서는 1차원 배열뿐만 아니라 2차원 이상의 다차원 배열도 허용한다.
		 
		2. 2차원 배열
		 - 큰 배열 안에 작은 배열을 여러개 가지고 있다.
		 - 배열의 선언
		   int[][] a;	모두 가능하지만 이것을 추천
		   int a[][];
		   int[] a[];
		   
		 - 배열의 생성
		   int[][] a = new int[3][4];
		 */
		
		int[][] a = new int[3][];			//앞은 큰 배열로 봤을 때 작은 배열이 몇개냐. 뒤는 작은 배열이 갖고 있는 개수들.
//		a.length = 3 이고 a[0].length = 2
//		뒤는 각 배열의 수가 다를 수 있기 때문에 생략가능. 생략했을 경우 작은 배열은 참조형 기본값인 null. 대상으로 지정할 수 없다. 새 작은 배열(새 1차원 배열)을 만들어야 다른 기본값이 정해짐. 
		a[0] = new int[2];
		a[1] = new int[4];
		a[2] = new int[3];
		
		
		
//		count += 10;		
//		a[0][0] = count;
//		count += 10;
//		a[0][1] = count;
		
//		for(int i=0; i<a[0].length;i++) {
//			count += 10;
//			a[0][i] = count;
//		}
//		count += 10;
//		a[1][0] = count;
//		count += 10;	
//		a[1][1] = count;
//		count += 10;
//		a[1][2] = count;
//		count += 10;
//		a[1][3] = count;
		
//		for(int i=0; i<a[1].length;i++) {
//			count += 10;
//			a[1][i] = count;
//		}
		
//		count += 10;
//		a[2][0] = count;
//		count += 10;
//		a[2][1] = count;
//		count += 10;
//		a[2][2] = count;
		
//		for(int i=0; i<a[2].length;i++) {
//			count += 10;
//			a[2][i] = count;
//		}
		
		int count = 0;
		
		for(int j=0; j<a.length;j++) {			//j 작은배열의 번호
			for(int i=0; i<a[j].length;i++) {	//i 작은배열 방의 번호
				count += 10;
				a[j][i] = count;
			}
		}
		
		// 각 자리의 점수 출력
//		System.out.println(a[0][0]);
//		System.out.println(a[0][1]);
//		System.out.println(a[1][0]);
//		System.out.println(a[1][1]);
//		System.out.println(a[1][2]);
//		System.out.println(a[1][3]);
//		System.out.println(a[2][0]);
//		System.out.println(a[2][1]);
//		System.out.println(a[2][2]);
		
//		System.out.println([바깥쪽for문][안쪽for문]);
		
		for(int j=0;j<a.length;j++){
			for(int i=0;i<a[j].length;i++){
				System.out.println(a[j][i]);
			}
		}
		
		System.out.println();
		
		//1. 6명의 7과목의 점수를 저장할 수 있는 변수 score를 선언 및 생성해주세요.
		
		// 6명의 이름을 저장할 수 있는 변수 name을 선언하고 주변 친구 6명의 이름으로 초기화하세요.
		String[] name = new String[]{"김진성", "이영훈", "박현승", "유영석", "한상기", "허상훈"};
		String[] studyc = new String[]{"국어", "영어", "수학", "사회", "과학", "오라클", "자바"};
		int[][] score = new int[name.length][studyc.length];

		//2. score각방의 점수를 0~100점 사이의 랜덤한 점수로 변경해주세요.
		for(int i=0;i<score.length;i++){
			for(int j=0;j<score[i].length;j++){
				score[i][j] = (int)(Math.random()*101);
			}
		}
		
		// 각자의 성적 합계를 저장할 변수를 선언 및 생성하고 각자의 성적 합계를 저장
		int[] sump = new int[name.length];
		
		for(int i=0;i<name.length;i++){
			for(int j=0;j<studyc.length;j++){
				sump[i] += score[i][j];
			}
		}
		
		//각자의 성적 평균을 저장할 변수를 선언 및 생성하고 각자의 성적 평균을 소수점 셋째 자리에서 반올림하여 둘째 자리에서 표현
		float[] avgp = new float[name.length];
		
		for(int i=0;i<name.length;i++){
			avgp[i] = ((int)((float)sump[i]/studyc.length*100+0.5f))/100f;
		}
		
		//과목합계와 과목평균
		
		int[] sumc = new int[studyc.length];
		
		for(int i=0;i<studyc.length;i++){		// 7과목이므로 7번 반복
			for(int j=0;j<name.length;j++){		// 6명이므로 6번 반복
				sumc[i] += score[j][i];			// 과목합계 = score [j][i]
			}
		}
		
		float[] avgc = new float[studyc.length];

		for(int i=0;i<studyc.length;i++){
			avgc[i] = ((int)((float)sumc[i]/name.length*100+0.5f))/100f;
		}
		
		//석차
		
		
		int[] rank = new int[name.length];
		
		for(int i=0;i<name.length;i++){		// 사람수 만큼 돌리기
			rank[i] = 1;					// rank배열 각각의 값을 1로 생성하고 시작(1등부터 시작해서 낮으면 밀리는 식)
			for(int j=0;j<name.length;j++){ // 석차는 모두와 한 번씩 비교해 봐야 나옴
				if(sump[i]<sump[j]){		// 여기서 처음부터 끝까지 다 비교하고
											// (sump[i]는 모두와 비교할 동안 고정되므로 굳이 별도의 변수를 선언할 필요는 없음)
					rank[i]++;				// 석차가 밀린다면 등수에 1 추가
				}
			}
		}
		
		
		
		
		
		//	[출력부]	
		/*		국	영	수	사	과	오라클	자바	합계 평균 석차
		가가가: 44	55	66	77	55	54	55
		나나나: 33
		가가가: 11
		가가가: 44
		가가가: 55
		가가가: 55
		과목합계
		과목평균
		 */
		for(int i=0;i<studyc.length;i++){
			System.out.print("\t" + studyc[i]);
		}
		System.out.print("\t" + "합계");
		System.out.print("\t" + "평균");
		System.out.println("\t" + "석차");
		System.out.println();
		for(int i=0;i<score.length;i++){				// 여섯 명
			System.out.print(name[i] + ":   ");
			for(int j=0;j<score[i].length;j++){			// 일곱 과목
				System.out.print(score[i][j]+"\t");
			}
			System.out.print(sump[i]+"\t");
			System.out.print(avgp[i]+"\t");
			System.out.print(rank[i]);
			System.out.println();
		}
		System.out.print("과목합계: ");
		for(int i=0;i<sumc.length;i++){
			System.out.print(sumc[i]+"\t");
		}
		System.out.println();
		System.out.print("과목평균: ");
		for(int i=0;i<avgc.length;i++){
			System.out.print(avgc[i]+"\t");
		}
		
		
		
		// 문제
		// 이름을 저장하고 좌측에 함께 출력. - 과목명을 넣고 위에 함께 출력 - 처음 선언한 2차원배열에서 숫자 대신 쓸 수 있는거
		// 6명의 합계를 구할거 선언 생성 출력부 맨오른쪽에 합계 찍어주기 - 평균 소수점 둘째자리까지 같은 방식 표현
		// 6. 과목별 합계 평균 구하고 아래에 찍기
		// 석차
		// 모든 사람의 석차를 낼 때, 시작할 때는 1등. 0등은 없다. 나보다 큰놈 없으면 내 등수가 올라간다
		// 한 명 씩의 석차부터 구해보기. 비교해 보니까 나보다 큰 놈이 3명. 그럼 나는? 4등
		// 비교대상 = sump[0~5]
		
		// 소트시 처음에는 점수만 빼고 합계 평균 석차 이름을 소트하는 걸로 하기
		// 합계 평균 석차는 어차피 같이감. 그런데 이름은 안 바꿈. 합계와 이름부터 바꿔보기
		

		
		
	}
}
