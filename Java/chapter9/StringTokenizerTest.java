package i_javaLang;

import java.util.StringTokenizer;

public class StringTokenizerTest {
	public static void main(String[] args) {
		String text = "100,200,300,400,500";
		
		StringTokenizer st = new StringTokenizer(text, ","); // 자를 때 몇 개가 나올지 모른다. 앞에서 읽어가면서 구분자 기준으로 찾아가는 것
		
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
		
		String data = "100,,,200,,300";
		
		System.out.println("==================== split ====================");
		String[] result = data.split(","); // String형 배열이므로 공백문자열을 인식한다. 총 6개로 잘림. "0,"처럼 쓰면 0,을 통째로 구분자로 인식함
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		
		System.out.println("==================== StringTokenizer ====================");
		StringTokenizer st2 = new StringTokenizer(data, ","); // 토큰 단위로 자른다. 공백은 토큰으로 인식하지 않는다. 총 3개로 잘림. "0,"처럼 쓰면 0과 ,을 모두 구분자로 인식함. 구분자는 단 한 문자만 가능하기 때문
		while(st2.hasMoreTokens()){
			System.out.println(st2.nextToken());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
