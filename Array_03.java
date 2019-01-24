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
//		뒤는 각 배열의 수가 다를 수 있기 때문에 생략가능. 생략했을 경우 작은 배열은 참조형 기본값인 null. 지목할 수 없다. 새 작은 배열(새 1차원 배열)을 만들어야 다른 기본값이 정해짐. 
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
		int[][] score = new int[6][7];
		//2. score각방의 점수를 0~100점 사이의 랜덤한 점수로 변경해주세요.
		for(int i=0;i<score.length;i++){
			for(int j=0;j<score[i].length;j++){
				score[i][j] = (int)(Math.random()*101);
			}
		}
		
		//3. 아래와 같이 출력해주세요
		for(int i=0;i<score.length;i++){
			for(int j=0;j<score[i].length;j++){
				System.out.print(score[i][j]+"\t");
			}
			System.out.println();
		}
		/*
		44	55	66	77	55	54	55
		33
		11
		44
		55
		55
		 */
		
		

		
		
	}
}
