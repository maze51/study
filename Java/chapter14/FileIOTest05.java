package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest05 {

	public static void main(String[] args) {
		
		try {
			// 복사할 파일 스트림 객체 생성(읽어올 파일 스트림 생성)
			FileInputStream fin = new FileInputStream("d:/D_Other/Tulips.jpg");
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			// 복사될 파일 스트림 객체 생성(저장할 파일 스트림 생성)
			FileOutputStream fout = new FileOutputStream("d:/D_Other/연습용/튤립.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			System.out.println("복사 시작");
			
			int c = 0; // 읽어온 데이터가 저장될 변수
			
//			while((c=fin.read()) != -1){
//				fout.write(c);
//			}
//			
			while((c=bin.read()) != -1){
				bout.write(c);
			}
			
			bout.flush();
//			fout.flush(); // 버퍼에 남은 데이터를 전부 강제적으로 출력(꺼내오기).
			// 왜? 버퍼는 가득 차야만 신호를 보내고, 그 전에는 기다린다(갖고 왔다는 신호를 보내지 않는다).
			// 그래서 데이터가 다 오지 않고 조금 끊긴 것 처럼 보일 때가 있다. 방지하려면 flush()사용.
			// close()에도 flush()기능이 포함되어 있기 때문에, close()만 잘 해도 예방할 순 있다.
			// 하지만 close()를 쓰기 어려울 때 같으면 flush()로 비우고 쓴다.
			// 보조스트림과 기반스트림이 같이 돌아간다면 기반쪽을 먼저 close()하는 것이 순서상 옳다.
			
			// @@@@ 버퍼를 사용할 때는 flush, close 필수
			System.out.println("복사가 완료되었습니다.");
//			fin.close();
//			fout.close();
			bin.close();
			bout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
