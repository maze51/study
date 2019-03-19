package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
	한글이 저장된 파일 읽어오기
	(한글의 인코딩을 지정해서 읽어온다)
 */

public class FileStreamTest01 {

	public static void main(String[] args) {
		try {
			// 파일 입출력의 인코딩 방식은 운영채제(OS)와 개발 환경에 영향을 받는다.
			
			FileInputStream fin = new FileInputStream("d:/D_Other/test_utf8.txt");
			//FileInputStream fin = new FileInputStream("d:/D_Other/test_ansi.txt");
			
			/*
			InputStreamReader 객체는 파일의 인코딩 방식을 지정할 수 있다.
				- MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI)
				- UTF-8 ==> 유니코드 UTF-8 인코딩 방식
				- US_ASCII ==> 영문 전용 인코딩 방식
			 */
			
			
			//InputStreamReader isr = new InputStreamReader(fin);
			//InputStreamReader isr = new InputStreamReader(fin, "ms949");
			InputStreamReader isr = new InputStreamReader(fin, "utf-8");
			
			int c;
			
			while((c=isr.read()) != -1){
				System.out.print((char)c);
			}
			
			isr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
