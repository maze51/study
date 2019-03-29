package basic;

import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// FileInputStream객체를 이용한 파일 내용 읽기
		try {
			// 파일과 연결된 바이트스트림객체 생성하기

			// 방법1 ==> 연결할 파일정보를 문자열로 지정한다.
			FileInputStream fin = new FileInputStream("d:/D_Other/test.txt");

			// 방법2 ==> 연결할 파일정보를 갖고 있는 File개체로 지정한다.
			//File file = new File("d:/D_Other/test.txt");
			//FileInputStream fin = new FileInputStream(file);

			int c; // 읽어온 데이터를 저장할 변수
			while((c = fin.read()) != -1){ // 한 문자씩 읽어오기
				System.out.print((char) c); // 읽어온 문자를 콘솔에 출력하기
			}

			fin.close();

		} catch (IOException e) {
			System.out.println("입출력 오류");
			e.printStackTrace();
		}
	}

}
