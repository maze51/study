package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];	// 읽기에 사용할 배열 
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			// available() ==> 읽어올 수 있는 데이터 개수(여기서는 byte수) 반환
			while(input.available()>0){
				//input.read(temp); // 다 읽어오면? 8, 9만 읽어다 저장하고 뒤 6, 7은 예전 데이터가 그대로 남는다.
				//output.write(temp);
				
				int len = input.read(temp); // 실제 읽어온 byte수를 반환한다.
				
				// temp[]의 내용 중에서 0번째부터 len개수만큼 출력한다.
				output.write(temp, 0, len);
				
				System.out.println("temp = " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			System.out.println(" inSrc => " + Arrays.toString(inSrc));
			System.out.println(" temp => " + Arrays.toString(temp));
			System.out.println(" outSrc => " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
