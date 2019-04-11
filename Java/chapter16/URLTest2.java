package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest2 {

	public static void main(String[] args) throws IOException {
		
		// 특정 서버에 있는 파일 내용을 읽어와 출력하는 예제
		// URLConnection ==> 애플리케이션과 URL간의 통신 연결을 위한 추상클래스
		
		URL url = new URL("https://www.naver.com/index.html");
		
		// URLConnection 구하기
		URLConnection urlCon = url.openConnection();
		
		// 해당 호스트의 페이지 정보 가져오기
		// 즉 위의 index.html내용 가져오기
		
		/*
		// 방법1 ==> URLConnection의 getInputStream()이용하기
		
		// 파일을 읽어오기 위한 스트림 생성
		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8"); // inputStream객체를 Reader로 바꿔줌
		BufferedReader br = new BufferedReader(isr);
		
		// 내용 읽어와 출력하기
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			System.out.println(str);
		}
		// 스트림 닫기
		br.close();
		*/
		
		// 방법2 ==> URL객체의 OpenStream()메서드 이용
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8"); // inputStream객체를 Reader로 바꿔줌
		BufferedReader br = new BufferedReader(isr);
		
		// 내용 읽어와 출력하기
		while(true) {
			String str = br.readLine(); // 한줄씩 읽기
			if(str == null) break;
			System.out.println(str);
		}
		// 스트림 닫기
		br.close();
	}

}
