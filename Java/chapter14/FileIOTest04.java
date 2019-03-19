package basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {
	// 사용자가 입력한 내용을 그대로 파일로 저장하기
	public static void main(String[] args) {
		// 출력용 문자 기반 스트림을 이용하여 작업한다
		
		// 사용자의 입력도 스트림을 사용해서 처리한다
		// InputStreamReader객체 ==> 바이트기반 스트림을 문자기반 스트림으로 변환해 주는 보조 스트림 객체이다.
		InputStreamReader isr = new InputStreamReader(System.in);
		
		try {
			// 파일 출력용 문자기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int c; // 키보드로 입력한 내용을 저장할 변수
			
			System.out.println("아무 내용이나 입력하세요.");
			
			while( (c=isr.read()) != -1 ){
				fw.write(c); // 콘솔로 입력한 값들을 파일에 출력하기
			}
			
			isr.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // 입력을 마치려면 Ctrl + z를 입력한다

}
