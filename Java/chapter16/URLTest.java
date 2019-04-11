package basic;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {

	public static void main(String[] args) throws MalformedURLException {
		// URL클래스 ==> 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 다루는 클래스
		URL url = new URL("http", "ddit.or.kr", 80, "index.html?test=123");
		
		System.out.println("Protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("file : " + url.getFile());
		System.out.println("query : " + url.getQuery());
		System.out.println("path : " + url.getPath());
		System.out.println("port : " + url.getPort());
		System.out.println();
		
		System.out.println(url.getProtocol() + "://" + url.getHost()
					+ ":" + url.getPort() + "/" + url.getPath()
				);
		
		System.out.println("toExternalForm : " + url.toExternalForm());
		System.out.println("toString : " + url.toString());
	}

}
