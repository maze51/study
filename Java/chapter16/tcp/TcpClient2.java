package basic.tcp;

import java.net.Socket;

public class TcpClient2 {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 7777); // 상대편 ip주소로 바꾸고 상대가 서버 접속 상태를 설정하면 연결할 수 있다
			System.out.println("서버에 연결되었습니다.");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
		} catch (Exception e) {
		}
	}
}
