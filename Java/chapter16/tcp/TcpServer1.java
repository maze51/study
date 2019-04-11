package basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// TCP소켓 프로그래밍의 서버 역할을 하는 클래스

public class TcpServer1 {

	public static void main(String[] args) throws IOException {
		// TCP 소켓 통신을 하기 위해 ServerSocket객체 생성
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 준비되어 접속을 기다립니다...");
		
		// accept()메서드는 클라이언트에서 연결 요청이 올때까지 기다린다.
		// 클라이언트의 연결 요청이 오면 Socket객체를 생성해서 클라이언트의
		// Socket과 연결한다.
		Socket socket = server.accept();
		
		// 이 부분부터는 클라이언트와 연결된 후에 처리할 내용들을 기술한다(어떻게 작업이 이뤄질지 미리 시나리오를 그려 놓고 코딩하는 것이 좋다).
		System.out.println("클라이언트와 연결되었습니다.\n");
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();
		
		System.out.println("연결된 서버 정보");
		System.out.println("서버의 IP주소 : " + socket.getLocalAddress());
		System.out.println("서버의 Port번호 : " + socket.getLocalPort());
		System.out.println();
		
		// 클라이언트에 메세지 보내기
		// 소켓의 OutputStream을 구성해서 전송한다.
		// 소켓의 getOutputStream()메서드를 이용하여 OutputStream객체를 구한다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		// 클라이언트에게 메세지 전송하기
		dos.writeUTF("어서오세요. 반갑습니다.");
		System.out.println("메세지를 보냈습니다.");
		
		// 소켓과 스트림을 닫는다.
		dos.close();
		server.close();
		socket.close();
	}

}
