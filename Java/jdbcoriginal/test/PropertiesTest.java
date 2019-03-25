package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTest {
	// properties 파일을 읽어와 Properties객체로 처리하는 예제
	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		try {
			// 읽어올 파일과 연결된 스트림 객체 생성
			FileInputStream fin = new FileInputStream("res/db.properties");
			
			// Properties객체로 파일 내용 읽어서 Properties객체에 데이터를 세팅하기
			
			// 파일 내용을 읽어서 key값과 value값을 분류한 후 Properties객체에 세팅한다.
			prop.load(fin);
			
			// 읽어온 정보 출력해 보기
			
			// key값만 읽어와 Enumeration객체로 변환하기
			Enumeration<String> names = (Enumeration<String>) prop.propertyNames();
			
			// key값 개수만큼 반복해서 처리한다.
			while(names.hasMoreElements()){ // 데이터가 있는지 검사
				String key = names.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " ====> " + value);
			}
			System.out.println("출력 끝!");
			
			fin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
