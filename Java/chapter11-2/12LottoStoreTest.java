package basic;

import java.util.HashSet;
import java.util.Scanner;

public class LottoStoreTest {
	public static void main(String[] args) {
		while(true){
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.println("메뉴선택 : ");

			Scanner sc = new Scanner(System.in);

			int select = 0;
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch(select) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println("감사합니다.");
				return;
			default:
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
	}

	private static void buyLotto() {
		while(true){
			System.out.println("Lotto 구입 시작");
			System.out.println();
			System.out.println("(1000원에 로또번호 하나입니다.)");
			System.out.print("금액 입력 : ");

			int money = 0;
			Scanner sc = new Scanner(System.in);
			try {
				money = sc.nextInt();
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			if(money >= 101000){
				System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
				return;
			} else if(money < 1000){
				System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
				return;
			} else {
				int extract = money/1000;
				System.out.println("행운의 로또번호는 아래와 같습니다.");
				for (int i = 0; i < extract; i++) {
					HashSet<Integer> lotto = new HashSet<Integer>();
					while(lotto.size()<6){
						int num = (int)(Math.random()*(45-1+1)+1);
						lotto.add(num);
					}
					System.out.print("로또번호"+(i+1)+" : " + lotto+"\n");

				} // end of for
				System.out.println("받은 금액은 "+money+"원이고 거스름돈은 "+money%1000+"원입니다.");
				return;
			}
		}
	}
}