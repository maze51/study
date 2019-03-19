package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) throws IOException{
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc); // 입력용 스트림 객체 생성
		output = new ByteArrayOutputStream(); // 출력용 스트림 객체 생성
		
		int data; // 읽어온 자료를 저장할 변수
		
		// 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = input.read()) != -1){ // 자료 읽기 작업
			output.write(data); // 자료 쓰기 작업
		}
		
		// 스트림으로 출력된 값들을 배열로 변환하기
		outSrc = output.toByteArray(); // 배열로 변환해서 반환한다.
		
		System.out.println(" inSrc => " + Arrays.toString(inSrc));
		System.out.println(" outSrc => " + Arrays.toString(outSrc));
		
		// 스트림 닫기
		input.close();
		output.close();
	}

}
