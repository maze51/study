package basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/D_Other/test.dat");
			
			// DataOutputStream은 출력용 데이터를 기본 자료형에 맞게 출력해 준다.
			DataOutputStream dout = new DataOutputStream(fout);
			
			dout.writeInt(200);			// 정수형 데이터 출력
			dout.writeFloat(131.5f);	// 실수형(float) 데이터 출력
			dout.writeBoolean(false);	// 논리형 데이터 출력
			dout.writeUTF("문자열출력");	// 문자열 데이터 출력
			
			System.out.println("출력 완료");
			dout.close();
			//-------------------------------------------------
			
			// 출력한 자료 읽어오기
			FileInputStream fin = new FileInputStream("d:/D_Other/test.dat");
			
			// DataInputStream을 읽어올 때는 출력할 때의 순서에 맞게 읽어와야 된다.
			DataInputStream din = new DataInputStream(fin);
			
			System.out.println("정수형 자료 : " + din.readInt());
			System.out.println("실수형 자료 : " + din.readFloat());
			System.out.println("논리형 자료 : " + din.readBoolean());
			System.out.println("문자열 자료 : " + din.readUTF());
			
			din.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
