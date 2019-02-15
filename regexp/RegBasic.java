package h_reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegBasic {
	public static void main(String[] args) {
		/*
		1. 정규식
		  - 텍스트데이터 중에서 원하는 조건과 일치하는 문자열을 찾아내기 위해 사용하는 것
		  - 정규식의 구성
		  	: Pattern이라는 클래스를 이용하여 정규식을 정의한다.
		  	: Matcher라는 클래스를 이용하여 Pattern과 일치하는 데이터를 찾는다.
		 */
		//패턴등록
		Pattern p = Pattern.compile("[a-z]*"); // *는? 소문자가 0개~무한대라는 것을 의미.
		
		Matcher m = p.matcher("123");
		
		System.out.println(m.matches()); // 매칭을 확인
		
		
		
		
		
		
		
		
		
		
	}
}
