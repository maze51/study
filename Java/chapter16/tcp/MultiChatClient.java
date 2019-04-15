package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	
	public void clientStart() {
		Socket socket = null;
		try {
			String serverIp = "localhost";
			socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다...");
			
			// 메시지 전송용 쓰레드 객체 생성
			Thread sender = new ClientsSender(socket);
			
			// 메시지 수신용 쓰레드 객체 생성
			Thread receiver = new ClientReceiver(socket);
			
			// 쓰레드 실행
			sender.start();
			receiver.start();
			
			
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
	}
	
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
	
	//----------------------------------
	// 메시지 전송용 Thread
	class ClientsSender extends Thread{
		Socket socket;
		DataOutputStream out;
		DataInputStream in;
		String name;
		Scanner scan = new Scanner(System.in);
		
		// 생성자
		public ClientsSender(Socket socket) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				
				if(out!=null) {
					// 클라이언트는 프로그램이 시작하면 대화명을 입력받아 서버로
					// 전송하고, 대화명 중복 여부를 feedback으로 받아서 확인한다.
					System.out.print("대화명 입력 : ");
					String name = scan.next();
					
					while(true) {
						out.writeUTF(name); // 대화명 전송
						String feedback = in.readUTF(); // 대화명의 중복 여부 수신
						
						if("이름중복!".equals(feedback)) { // 대화명이 중복될 때
							System.out.println(name + "은 중복 대화명입니다.");
							System.out.println("다른 대화명을 입력하세요.");
							System.out.print("대화명 입력 : ");
							name = scan.next();
						} else { // 대화명이 중복되지 않을 때
							scan.nextLine(); // 입력버퍼 지우기
							this.name = name;
							System.out.println(name + " 이름으로 대화방에 접속했습니다.");
							break; // 반복문 탈출
						}
					}
				}
			} catch (Exception e) {
			}
			
		} // 생성자 끝
		
		@Override
		public void run() {
			try {
				while(out!=null) {
					// 키보드로 입력받은 데이터를 서버로 전송
					out.writeUTF("[" + name + "] : " + scan.nextLine());
				}
			} catch (IOException e) { }
		} // run 끝
	}
	// 전송용 쓰레드 끝
	//--------------------------------------------------
	// 메시지 수신용 쓰레드
	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 서버로부터 메시지를 수신한 InputStream객체 생성
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
			}
		} // 생성자 끝
		
		@Override
		public void run() {
			while(in!=null) {
				try {
					// 서버로부터 수신한 메시지를 출력한다.
					System.out.println(in.readUTF());
				} catch (IOException e) {
				}
			}
		}
	}
}
