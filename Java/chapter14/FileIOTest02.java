package basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		
		// FileOutputStream 객체를 이용하여 데이터를 파일에 저장하는 예제
		try {
			FileOutputStream fout = new FileOutputStream("d:/D_Other/out.txt");
			
			for (char ch = 'A'; ch <= 'Z'; ch++) {
				fout.write(ch); // 출력하기
			}
			
			System.out.println("출력작업 끝!");
			
			//-------------------------------------------
			// 저장한 파일 읽어오기
			
			FileInputStream fin = new FileInputStream("d:/D_Other/out.txt");
			
			int ch;
			
			while((ch=fin.read()) != -1){
				System.out.print((char) ch);
			}
			System.out.println();
			System.out.println("읽기 끝");
			
			fout.close();
			fin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
