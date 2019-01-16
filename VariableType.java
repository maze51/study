package a_variable;

public class VariableType {
	public static void main(String[] args) {
		
		/*
		 변수의 타입
		 
		 1. 기본형 타입(primitive type): 값을 저장(숫자, 실수, 한 문자). 단 8개
		  - boolean, char, byte, short, int, long, float, double
		 2. 참조형 타입(reference type): (주소에 가면 여러 변수를 또 가지고 있을 수 있음). 숫자가 무한대
		  - 주소를 저장하는 데 사용된다.
		  - 4byte의 저장공간을 가진다.
		 */
		
//		정수 50을 저장할 수 있는 변수를 선언 및 초기화해주세요
		int a; // 변수의 선언		int는 변수타입, a는 변수명
		a = 50; // 변수의 초기화
		int b = 50; // 변수의 선언 및 초기화
		
		/*
		 1. 종류
		  - 논리형 : true, false 중 하나의 값을 가진다. ex) boolean
		  - 문자형 : 단 하나의 문자를 저장한다. ex) char 
		  - 정수형 : 정수만 저장 가능하다. ex) byte, short, int, long
		  - 실수형 : 실수를 저장 가능하다. ex) float, double
		 
		 2. 기본형 변수의 크기(1byte = 8bit)
		  - 1byte : boolean, byte  
		  - 2byte : char, short 
		  - 4byte : float, int
		  - 8byte : double, long
		 
		 
		 */
		boolean power = true;
		
		char lang = 'A'; // 싱글쿼테이션(싱글쿼트). "" 이건 더블쿼테이션.
		
		
		
		
	}

}
