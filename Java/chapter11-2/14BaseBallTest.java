package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class BaseBallTest {
	
	// 각 기능별로 메서드를 만들어 처리하는 것을 권장
	
	ArrayList<Integer> numList; // 난수를 저장할 리스트
	ArrayList<Integer> userList = new ArrayList<Integer>(); // 사용자가 입력한 값을 저장할 리스트
	
	int strike; // 스트라이크 개수가 저장될 변수
	int ball; // 볼의 개수
	
	Scanner sc = new Scanner(System.in);
	
	// 1~9사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드
	public void getNum(){
		Set<Integer> numSet = new HashSet<Integer>();
		
		// Set을 이용한 3개의 난수 만들기
		while(numSet.size()<3) {
			numSet.add((int)(Math.random()*9+1));
		}
		
		// Set의 값들을 List에 저장하기
		numList = new ArrayList<Integer>(numSet);
		
		// 리스트의 데이터 섞기
		Collections.shuffle(numList);
	}
	
	// 사용자로부터 3개의 정수를 입력받아 리스트에 저장하는 메서드
	public void inputNum(){
		int n1, n2, n3;
		do{
			System.out.print("중복되지 않은 정수 3개 입력 >> ");
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			n3 = sc.nextInt();
			
			if(n1==n2 || n1==n3 || n2==n3){
				System.out.println("입력값이 중복됩니다. 다시 입력하세요");
			}
		} while(n1==n2 || n1==n3 || n2==n3); // 사실 while안 조건만으로도 중복체크는 가능하다
		
		userList.clear(); // 반복시 이전 데이터 삭제
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);

	}

	// 스트라이크와 볼 판정 및 출력하는 메서드
	public void ballCount(){
		strike = 0;
		ball = 0; // 스트라이크와 볼의 개수 초기화

		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if(numList.get(i) == userList.get(j)){ // 값이 같은지 비교
					if(i==j){ // 위치가 같으면 스트라이크
						strike++;
					} else { // 위치가 다르면 볼
						ball++;
					}
				}
			}
		}
		// 판정 결과 출력
		System.out.println(userList.get(0) + ", " + userList.get(1) + ", "
							+ userList.get(2) + ", " + strike + "S " + ball + "B");
	}
	
	// 전체 게임을 진행하는 메서드
	public void gameStart(){
		// 난수 만드는 메서드 호출
		getNum();
		
		// 확인용 출력
//		System.out.println("난수값 => " + numList.get(0) + ", " + numList.get(1) + ", " + numList.get(2));
		
		int cnt = 0;
		
		do{
			cnt++;
			
			// 사용자 입력
			inputNum();
			
			// 볼카운트 메서드 호출
			ballCount();
			
			
		} while(strike != 3); // 스트라이크가 3이 될 때 까지 반복한다.
		
		System.out.println(cnt + " 번째만에 맞췄군요!!");
	}

	public static void main(String[] args) {
		new BaseBallTest().gameStart(); // 메서드 하나만 호출할 때는 굳이 변수를 만들지 않아도 된다.
		
	}
}






		/*
		HashSet<Integer> num1 = new HashSet<Integer>();

		while(num1.size() < 3){
			int num = (int)(Math.random()*9+1);
			num1.add(num);
		}
//		System.out.println("컴퓨터의 번호 : " + num1);
		int[] number = new int[3]; // 컴퓨터 랜덤값 저장

		Iterator it = num1.iterator();

		for (int i = 0; i < number.length; i++) {
			number[i] = (int)it.next();
		}
		
		int strike;
		int ball;
		int count = 0;
		System.out.println("숫자 야구 게임을 시작합니다");
		while(true){
			System.out.println("1~9 사이의 숫자 세 개를 입력해주세요(숫자 사이는 쉼표(,)로 구분해주세요).>");
			Scanner sc = new Scanner(System.in);
			
			String str = sc.next();
			boolean right = false;
			Pattern p = Pattern.compile("([1-9],){2}[1-9]$");
			Matcher m = p.matcher(str);
			right = m.matches();
			if(right==false){
				System.out.println("숫자 형식이 맞지 않습니다. 다시 입력하세요");
				continue;
			}
			String[] arr = str.split(",");

			int[] iarr = new int[arr.length]; // 입력값 세 개가 저장

			for (int i = 0; i < arr.length; i++) {
				iarr[i] = Integer.parseInt(arr[i]);
			}
			strike = 0;
			ball = 0;
			for (int i = 0; i < iarr.length; i++) { // 입력값 하나 당 1번씩 반복
				
				for (int j = 0; j < iarr.length; j++) { // 컴퓨터 랜덤값과 각각 비교
					if(iarr[i] == number[i]){
						strike++;
						break;
					} else if (iarr[i] == number[j]) {
						ball++;
					}
				}
			} // end of for
			count++;
			System.out.println(strike + "S " + ball + "B");
			if(strike==3){
				System.out.println(count + "번 만에 맞췄군요!!");
				break;
			} else {
				continue;
			}
		}
		*/
