package basic;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {

	public static void main(String[] args) {
		
		// 문자 기반의 FileReader객체를 이용한 파일 내용 읽기
		// 인코딩이 일치한다면 한글이 정상적으로 출력된다(FileIOTest01번의 경우 Byte단위로 읽어오기 때문에 인코딩이 어떻든 깨진다)
		
		try {
			FileReader fr = new FileReader("d:/D_Other/test.txt");
			
			int c;
			
			while((c = fr.read()) != -1){
				System.out.print((char)c);
			}
			
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
