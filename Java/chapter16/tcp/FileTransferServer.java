package basic.tcp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer {
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSoc = new ServerSocket(7777);
			Socket socket = serverSoc.accept();
			System.out.println("서버가 준비되었습니다.");
			
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			FileOutputStream fout = new FileOutputStream("d:/D_Other/연습용/Tulips복사본.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			int c = 0;
			
			while((c=dis.read()) != -1){
				bout.write(c);
			}
			System.out.println("저장 완료");
			
			bout.close();
			fout.close();
			dis.close();
			is.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
