package basic.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileTransferClient {
	private Socket socket;
	private OutputStream os;
	private FileInputStream fis;
	
	public void clientStart() {
		File file = new File("d:/D_Other/Tulips.jpg");
		if(!file.exists()) {
			System.out.println("전송할 파일이 없습니다. 프로그램을 종료합니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			
			System.out.println("파일 전송 시작...");
			fis = new FileInputStream(file);
			os = socket.getOutputStream();
			
			byte[] tmp = new byte[1024];
			int length = 0;
			while((length=fis.read(tmp))!=-1) { // 파일 내용 읽기
				os.write(tmp, 0, length); // 읽어온 내용을 서버로 전송하기
			}
			
			System.out.println("파일 전송 완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis!=null) try {fis.close();} catch(IOException e2) {}
			if(os!=null) try {os.close();} catch(IOException e2) {}
			if(socket!=null) try {socket.close();} catch(IOException e2) {}
		}
	}
	
	public static void main(String[] args) {
		new FileTransferClient().clientStart();
		
		/*
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
		*/
	}
}
