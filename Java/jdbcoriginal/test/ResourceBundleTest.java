package test;

import java.util.Enumeration;
import java.util.ResourceBundle;

/*
	ResourceBundle객체 ==> 확장자가 properties인 파일 정보를 읽어와
						 key값과 value값을 분리해 정보를 갖고 있는 객체
		==> 읽어올 파일은 'key값=value값' 형태로 구성되어 있어야 한다.
 */
public class ResourceBundleTest {

	public static void main(String[] args) {
		// ResourceBundle객체를 이용하여 파일 읽어오기
		
		// ResourceBundle객체 생성하기
		
		// 파일을 지정할 때 '파일명'만 지정하고 확장자는 생략한다.
		// 이유? 확장자가 properties인 파일만 읽어오기 때문에
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		
		// key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		// key값 개수만큼 반복해서 처리하기
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			
			// key값을 이용해서 value값을 읽는 방법 ==> getString() 사용
			// 형식) bundle.getString("key값");
			String value = bundle.getString(key);
			
			System.out.println(key + " <===> " + value);
		}
		System.out.println("출력 끝!");
	}
}
// Properties객체를 쓰는 방법과 다른 점? try-catch블럭이 없다