package basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제) 5명의 사람 이름을 차례로 입력받아 ArrayList에 저장
		  저장이 모두 완료되면 이 ArrayList에 있는 데이터 중 '김'씨 성을 가진 데이터를 
		  모두 출력하시오.
 */
public class ArrayListTest2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list1 = new ArrayList<String>();
		System.out.println("5명의 이름을 입력해 주세요");
		for (int i = 0; i < 5; i++) {
			System.out.print(i+1 + "번째: ");
			String a = sc.next();
			list1.add(a);
		}
		
		for (int i = 0; i < list1.size(); i++) {
			if(list1.get(i).charAt(0)=='김')
				System.out.println("김씨 : "+list1.get(i));
		}
		
		
		System.out.println("-----------------------------------------------");
		
		for (int i = 0; i < list1.size(); i++) {
			if(list1.get(i).startsWith("김")){
				System.out.println("김씨 : "+list1.get(i));
			}
		}
		//향상된 for문
		for(String testName : list1){
			if(testName.startsWith("김")){
				System.out.println(testName);
			}
		}
		
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < args.length; i++) {
			if(list1.get(i).substring(0, 1).equals("김")){
				System.out.println("김씨 : "+list1.get(i));
			}
		}
		
		System.out.println("-----------------------------------------------");
		for (int j = 0; j < args.length; j++) {
			if(list1.get(j).indexOf("김")==0) {
				System.out.println("김씨 : "+list1.get(j));
			}
		}	
		
		
		}
	}

