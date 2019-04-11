package basic.tcp;

import java.net.ServerSocket;
import java.net.Socket;

// 1:1 채팅 ==> 데이터를 받는 클래스와 데이터를 보내는 클래스를
//		  ==> 따로 구성하고 이 클래스들은 쓰레드로 작동되도록 한다.

public class TcpServer2 {
	public static void main(String[] args) {
		// 서버 소켓을 만들고, 클라이언트가 접속해 오면 소켓을 만들어
		// 데이터를 받는 클래스와 데이터를 보내는 클래스에
		// 이 소켓을 넘겨준다
		try {
			ServerSocket serverSoc = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			Socket socket = serverSoc.accept();
			
			System.out.println("클라이언트가 접속했습니다...");
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			// 쓰레드 작동
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
		}
	}
}
