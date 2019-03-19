package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {
	// 문자 기반 Buffered 스트림 예제				ByteArrayIOTest02.java 파일을 그대로 읽어와 출력하기
	public static void main(String[] args) {
		try {
			//FileReader fr = new FileReader("D:\\A_TeachingMaterial\\3.HighJava\\workspace\\javaIOTest\\src\\basic\\ByteArrayIOTest02.java"); // 이게 보통이지만 너무 길다
			// 이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는 해당 프로그램이 속한 프로젝트 폴더가 기본 위치가 된다.
			FileReader fr = new FileReader("./src/basic/ByteArrayIOTest02.java");
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";
			
			// readLine() ==> 한 줄씩 데이터를 읽어오고 더 이상 읽어올 자료가 없으면 null을 반환한다.
			for (int i = 1; (temp=br.readLine())!=null;i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
