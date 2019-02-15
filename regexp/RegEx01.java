package h_reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx01 {
	public static void main(String[] args) {
		/*
		^ 문자열의 시작을 의미(무조건 이걸로 시작해야 한다)
		$ 문자열의 끝을 의미
		. 임의의 한 문자(문자열의 종류를 가리지 않음), 단, \는 넣을 수 없다.
		* 문자가 없을 수도, 무한정 있을 수도 있다.
		+ 앞 문자가 하나 이상(최소 1개는 있어야).
		? 앞 문자가 없거나 하나(0 or 1).
		[abcd] 문자의 집합이나 범위를 나타낸다. 이 때는 a,b,c,d중 하나
		{} 횟수 또는 범위를 나타낸다.
		(abc) 소괄호 안의 문자를 하나의 문자로 인식한다. 이 때는 무조건 abc순으로 나와야 한다.
		| 패턴 안에서 or연산을 수행할 때 사용한다. ()와 함께 많이 사용. a문자열 or b문자열
		\s 공백문자
		\S 공백을 제외한 모든 문자
		\w 알파벳이나 숫자 [A-Za-z0-9]
		\W 알파벳이나 숫자를 제외한 모든 문자
		\d 숫자
		\D 숫자를 제외한 모든 문자
		
		영문자로 시작: ^[A-Za-z]
		반복 :  {2} 무조건 2번 반복
			  {2,4} 2번에서 4번 (2,3,4회) // 이건 자바에서 만든 게 아님
			  {2,} 2회 이상 반복
		*/
		
		//1. 영어 소문자가 2회에서 3회 반복
//		Pattern p = Pattern.compile("[a-z]{2,3}");
//		Matcher m = p.matcher("AAA");
//		System.out.println(m.matches());
		
		String regEx1 = "[a-z]{2,3}";
		System.out.println(Pattern.matches(regEx1, "a2")); // 위와 같은 문장
		
		//2. 문자열 업그레이드
		// 영문자가 3회 반복 후 숫자가 하나 이상
		
//		String regEx2 = "[A-Za-z]{3}[0-9]+";
		String regEx2 = "[A-Za-z]{3}\\d+";	// 역슬러시 2개면 정상적으로 작동
		System.out.println(Pattern.matches(regEx2, "aaa6665"));
		
		//3. 핸드폰 번호
		// 숫자 3개-숫자4개-숫자4개
		
		String regEx3 = "\\d{3}-\\d{4}-\\d{4}";
		System.out.println(Pattern.matches(regEx3, "010-9999-9999"));
		
		//4. 핸드폰 번호 업그레이드
		// 01 016789-1~9숫자3개-숫자4개
		
		String regEx4 = "01[016789]-[1-9]\\d{3}-\\d{4}"; // 1회반복시 {1}을 넣어도 되지만 굳이 필요 없다
		System.out.println(Pattern.matches(regEx4, "010-1000-9999"));
		
		//5. 주민등록번호
		// 연도: 그냥 숫자 2개	월: 0일때는 1~9 1일때는 0~2	일: 0일때는1~9  1 0~9  2 0~9  3 0~1
		// 1~4 숫자6자리
		
		String regEx5 = "\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[0-1])-[1-4]\\d{6}";
		System.out.println(Pattern.matches(regEx5, "990130-1012345"));
		
		
		//이메일 정규식
		//1. 영문자로 시작해야 하고 특수기호는 - _ \ . 4가지만 사용가능. 특수기호는 있을 수도, 없을 수도, 여러 개 있을수도 있다.
		//		나머지 녀석들은 영문자 or 숫자들이 들어간다
		//2. @이후에는 영문자나 숫자가 1개~7개까지 가능하다.
		//3. .이후에는 영문자가 2~3자가 올 수 있다.
		//4. .kr이 하나 있을 수도 있고 없을 수도 있다.
		
		String email = "^[a-zA-Z][-_\\\\.\\w]*(@)\\w{1,7}(.)[a-zA-Z]{2,3}*(.kr)?";
		System.out.println(Pattern.matches(email, "qw._\\-sf-tg@hanmail.net"));
		
		//비속어 정규식 응용법
		
		
		String text = " ";
		
		String result = filterText(text); // 이렇게만 쓰고 f2누르면 알아서 만들어 준다
		System.out.println(result);
		
	}

	private static String filterText(String text) {
		Pattern p = Pattern.compile("개나리|십장생|조카신발|병일신", Pattern.CASE_INSENSITIVE); // 하나하나씩 찾아서
		Matcher m = p.matcher(text);
		
		StringBuffer sb = new StringBuffer();		// 문자열 합치기용
		while(m.find()){		// 쭉 찾다가 찾으면 true반환. 못 찾을 때까지 돌린다
			m.appendReplacement(sb, maskword(m.group())); // 문자열을 받아주는 건 group(). 쪼개서 개만 남기고 뒤를 하트로
		}
		m.appendTail(sb);	// 나머지 부분을 모아서 반환
		
		return sb.toString();
	}

	private static String maskword(String group) {
		StringBuffer sb = new StringBuffer(); 		//0번째, "개"가 들어옴	1번째 이하는 하트하트
		char[] ch = group.toCharArray();			//하나씩 잘라서 0번째, 1번째,...char형 배열에 넣어줌
		for (int i = 0; i < ch.length; i++) {
			if(i==0){
				sb.append(ch[i]);
			} else {
				sb.append("♥");
			}
		}
		
		return sb.toString();
	}
}
