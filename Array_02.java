package d_array;

import java.util.Arrays;

public class Array_02 {
	public static void main(String[] args) {
		
		
		/*
		1. 정렬
		 - 어떤 데이터를 빠르고 쉽게 찾기 위해 일정한 순서대로 데이터들을 가지런하게 나열하는 작업이다.
		 - 버블정렬, 선택정렬, 삽입정렬 
		
		2. 버블정렬(bubble sort)
		 - 인접한 데이터 간에 교환이 계속적으로 일어나면서 정렬이 이루어 진다.
		 - 한 회전이 끝날 때 가장 큰 값이 가장 뒤에 확정된다.
		 - 총 n-1회전 진행. 마지막 맨 앞 값은 자동 확정이므로.
		 
		3. 선택정렬(select sort)
		 - 정렬의 대상에서 최소값을 찾아서 맨 앞의 내용과 교체하는 방식이다.
		 - 한 회전이 끝날 때 가장 작은 값이 앞에 확정된다.
		 
		4. 삽입정렬(insert sort)
		
		 */
		
		int[] arr = new int[]{5,2,3,1,4};
		int tmp = 0;

		for(int j=0;j<arr.length-1;j++){			// 버블정렬은 정렬할 값(배열값)-1회전 수행됨.
			for(int i=0;i<arr.length-1;i++){		// length-1은 아래 if식에서 배열 범위를 넘어가지 않도록 제한하기 위해 설정
				if(arr[i] > arr[i+1]){				// i번방 값이 i+1번방보다 크다면
					tmp = arr[i];
					arr[i] = arr[i+1];				// 중간 변수 tmp를 활용해 arr[i]와 arr[i+1]의 위치를 바꿔줌
					arr[i+1] = tmp;
				}
			}
		}

//		for(int i=0;i<arr.length;i++){
//			System.out.print(arr[i]+" ");
//		}
		
		int[] arr2 = {5,2,3,1,4};
		
		// 회전 과정 전체를 그대로 출력하기+불필요한 부분을 빼고
		for(int j=0;j<arr2.length-1;j++){
			System.out.println(j+1+"회전");
			for(int i=0;i<arr2.length-1-j;i++){		// length 에서 j를 빼서 끝나는 시점을 점점 줄여나갈 수 있음 -> 2회전 이후 확정된 부분을 빼 줌  "확정 개념"
				if(arr2[i] > arr2[i+1]){				
					tmp = arr2[i];
					arr2[i] = arr2[i+1];				
					arr2[i+1] = tmp;
				}
				for(int k=0;k<arr2.length;k++){		// if문 안에 넣으면 if문이 true일 때만 출력됨. 거기서 빼서 모든 회전마다 출력하도록 한 것.
					System.out.print(arr2[k]+" ");
				} // 매 비교시 출력for 끝
				System.out.println();
			} // 매 회전 for 끝
		} // 전체 for 끝
		
		// 선택정렬
		// 1. 최소값을 가지고 있는 방번호를 구하세요
		
		int[] arr3 = {5,2,3,1,4};
		
		int min = arr3[0];
		int minBang = 0;				//처음은 0번방
		
		for(int i=1;i<arr3.length;i++){				// 회전수, 기준방의 역할
			if(min > arr3[i]) {
				min = arr3[i];
				minBang = i;
			}
			
		}
		
		System.out.println(minBang);
		
		
		
		// 2. 기준방과 최소값을 가지고 있는 방의 값을 교환해주세요. (처음 기준방은 0)
//		int temp2 = arr2[0];		// arr2의 기준방
//		arr2[0] = arr2[minBang];
//		arr[minBang] = temp2;
//		
//		for(int i=0;i<arr2.length;i++) {
//			System.out.print(arr2[i]+" ");
//		}
//		System.out.println();
		
//		선택정렬 전체를 하나로 나타내면
		int[] arr4 = {5,2,3,1,4};
		
		for(int j=0;j<arr4.length-1;j++){
			int min22 = arr4[j];
			int minBang5 = j;
			for(int i = j+1; i<arr4.length;i++){
				if(min22>arr4[i]){
					min22=arr4[i];
					minBang5=i;
				}
			}
			int temp2 = arr4[j];
			arr4[j] = arr4[minBang5];
			arr4[minBang5] = temp2;
			
			for(int i=0;i<arr4.length;i++){
				System.out.print(arr4[i]+" ");
			}
			System.out.println();
		}
		
		
	}
}
