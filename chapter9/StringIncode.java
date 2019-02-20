package i_javaLang;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringIncode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String str = "غانا"; // 영문은 어떤 인코딩 방식이든 상관없지만, 한글/기타 언어는 방식마다 바이트가 다르다
		// 나중에 해외 관련 개발할 일이 있다면 그 나라에 맞는 인코딩 방식을 취해 만들어야 한다
		byte[] cpStr = str.getBytes("CP949"); // 문자열 형태를 넣을 때 오타 가능성이 항상 있다. 그래서 예외지정이 필요
		byte[] msStr = str.getBytes("MS949");
		byte[] utfStr = str.getBytes("UTF-8");
		
		System.out.println(Arrays.toString(cpStr));
		System.out.println(Arrays.toString(msStr));
		System.out.println(Arrays.toString(utfStr));
		
	}
}
