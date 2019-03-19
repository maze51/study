package basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력 성능을 향상시키기 위해 Buffered 스트림을 사용한다.
		
		try {
			// 기반이 되는 스트림 생성
			FileOutputStream fout = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			// 크기가 5인 버퍼스트림 객체 생성
			// 크기를 지정하지 않으면 기본 크기는 8192byte(8kb) 이다.
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for (int i = '1'; i <= '9'; i++) {
				bout.write(i);
			}
			//bout.flush(); // flush()해주지 않으면 1~5까지만 출력된다. flush() 또는 close()해주면 잘 나온다.
			
			System.out.println("작업 끝...");
			
			bout.close();
		} catch (IOException e) {
		}
	}

}
