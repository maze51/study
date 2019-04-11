package basic.tcp;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileTransferClient {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버에 연결되었습니다.");
			
			FileInputStream fin = new FileInputStream("d:/D_Other/Tulips.jpg");
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			int c = 0;
			
			while((c=bin.read()) != -1){
				dos.write(c);
			}
			
			System.out.println("전송 완료");
			
			dos.close();
			os.close();
			fin.close();
			bin.close();
		} catch (Exception e) {
		}
	}
}
