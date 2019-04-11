package basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

// TCP소켓 프로그래밍의 클라이언트 역할을 하는 클래스

public class TcpClient1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 자기 자신 컴퓨터를 나타내는 방법
		// 1. IP주소 (192.168.0.118)
		// 2. Local 컴퓨터를 나타내는 IP주소 : 127.0.0.1
		// 3. 컴이름 => localhost
		String serverIp = "localhost";
		System.out.println(serverIp + "서버에 연결 중입니다...");
		
		// Socket객체를 생성해서 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 이 이후에는 서버와 연결된 상태에서 처리할 내용을 기술한다.
		
		System.out.println("연결된 서버 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " + socket.getLocalPort());
		System.out.println();
		
		// 서버에서 보내온 메세지를 받아서 출력하기
		
		// 보내온 메세지를 받기 위해 InputStream객체를 생성한다.
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is); // 보조 스트림
		
		// 소켓으로부터 받은 메세지를 출력한다
		System.out.println("서버에서 온 메세지 : " + dis.readUTF());
		System.out.println("연결을 종료합니다.");
		
		// 소켓과 스트림 닫기
		dis.close();
		socket.close();
		
		
	}

}
