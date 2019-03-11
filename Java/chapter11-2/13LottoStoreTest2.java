package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LottoStoreTest2 {
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new LottoStoreTest2().storeStart();
	}

	// 메뉴 출력 및 작업 번호를 입력받는 메서드
	public int displayMenu(){
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.println("메뉴선택 : ");
		int num = scan.nextInt();
		return num;
	}

	// 시작
	public void storeStart(){
		while(true){
			int choice = displayMenu();
			
			switch(choice){
			case 1: // 로또 구매
				buyLotto();
				break;
			case 2: // 프로그램 종료
				System.out.println();
				System.out.println("감사합니다.\n안녕히 가세요.");
				return;
			default:
				System.out.println("번호를 잘못 선택했습니다.('1' 또는 '2'를 입력하세요)");
				
			}
		}
	}
	
	// 로또를 구매하는 메서드
	public void buyLotto() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int money = scan.nextInt();
		
		System.out.println();
		if(money<1000){
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			return;
		} else if(money>=101000){
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			return;
		}
		getLottoNum(money); // 로또번호를 생성하는 메서드 호출
		
		System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + money%1000 + "원입니다.");
		
		
	}
	// 금액에 맞는 로또번호를 생성해 주는 메서드
	public void getLottoNum(int money){
		Set<Integer> lottoSet = new HashSet<Integer>();
		List<Integer> lottoList = null; // 출력 시 List를 사용한 이유는? 번호를 순서대로 정렬하기 위해
		
		int count = money / 1000; // 로또 구매 매수 구하기
		System.out.println();
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for (int i = 1; i <= count; i++) {
			
			//로또 번호 만들기
			while(lottoSet.size()<6){
				lottoSet.add((int)(Math.random() * 45 + 1));
			}
			
			lottoList = new ArrayList<Integer>(lottoSet);
			Collections.sort(lottoList);
			
			System.out.println("로또번호"+ i +" : " + lottoList);
			lottoSet.clear(); // Set정보를 모두 지운다.
		}
	}
}
