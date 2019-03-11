package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
문제 1) 5명의 별명을 입력하여 ArrayList에 저장하고
		길이가 가장 긴 별명을 출력하시오. (하나가 나온다)
		(단, 각 별명의 길이는 모두 다르게 입력한다)
		
문제 2) 문제1에서 별명의 길이가 같은 것이 여러개일 경우를 처리하시오 (여러개가 나올 수도 있다)
 */
public class ArrayListTest3 {
	Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		ArrayListTest3 a = new ArrayListTest3();
//		a.first();
		a.second();
		
	}
	public void first(){
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("서로 다른 길이로 5명의 별명을 입력해 주세요");
		for (int i = 0; i < 5; i++) {
			System.out.print(i+1 + "번째 별명: ");
			String a = sc.next();
			list.add(a);
		}
		
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).length()>result.length()){
				result = list.get(i);
			} 
		}
		System.out.println(result);
	}
	
	// 제일 긴 별명이 들어있는 index값을 저장할 int변수를 선언하고, list.size()반복문에서 비교하고 해당 index의 저장값 출력
	
	public void second(){
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("5명의 별명을 입력해 주세요");
		
		for (int i = 0; i < 5; i++) {
			System.out.print(i+1 + "번째: ");
			String a = sc.next();
			list.add(a);
		}
		
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).length()>result.length()){
				result = list.get(i);
			} 
		}
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).length()==result.length()){
				System.out.println(list.get(i));
			}
		}
		/*
		2번 풀이 다른방법
		// 제일 긴 글자수가 지정될 변수 선언.
		// 이 변수는 List의 첫번째 데이터의 길이값으로 초기화된다.
		int maxLength = list.get(0).length();
		for(int i=1 ; i<list.size() ; i++) {
			int len = list.get(i).length();
			if(maxLength < len){
				maxLength = len; // 가장 긴 글자수가 들어가게 된다
			}
		}
		syso("제일 긴 별명 : ")
		for(int i=0;i<list.size();i++){
			if(list.get(i).length() == maxLength){
				syso(list.get(i));
			}
		}
		 */
	
	}
}
