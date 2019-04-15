package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MultiChatServer {
	// 대화명(이름), 클라이언트의 Socket 저장용 Map 변수 선언
	private Map<String, Socket> clients;
	
	public MultiChatServer() {
		// 동기화 처리가 가능한 Map객체 생성 ==> 대화방Map
		clients = Collections.synchronizedMap(new HashMap<>());
	}
	
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress() + ":" 
							+ socket.getPort() + "]에서 접속했습니다.");
				
				//----------------------------------------------
				
				// 클라이언트 전송용 쓰레드를 생성해서 작동시키기
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(server!=null) try {server.close();} catch(IOException e2) {}
		}
	}
	
	// 대화방Map에 있는 전체 접속자에게 메시지를 보내는 메서드
	public void sendToAll(String msg) {
		// 대화방Map에 저장된 key값(사용자이름) 구하기 (iterator 또는 향상된 for문)
		try {
			for(String name : clients.keySet()) {
				DataOutputStream dos = new DataOutputStream(
						clients.get(name).getOutputStream());
				dos.writeUTF(msg); // 채팅이니까 문자만 보낸다
			}
		} catch(IOException e) { }
	}
	
	//----------------------------------------------
	
	public static void main(String[] args) {
		new MultiChatServer().serverStart();
	}
	
	//----------------------------------------------
	// 서버에서 클라이언트로 메시지를 전송하는 Thread
	class ServerReceiver extends Thread{
		Socket socket;
		DataInputStream dis;
		DataOutputStream dos;
		
		// 생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 클라이언트에서 보내온 데이터를 수신하기 위한 InputStream객체 생성
				dis = new DataInputStream(socket.getInputStream());
				// 클라이언트로 메시지를 송신하기 위한 OutputStream객체 생성
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) { }
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				while(true) {
					// 클라이언트가 최초로 보내온 대화명을 받아서
					// 대화명의 중복 여부를 feedback으로 클라이언트에게 보내준다
					name = dis.readUTF();
					
					if(clients.containsKey(name)) { // 대화명이 중복될 때
						dos.writeUTF("이름중복!");
					} else {
						dos.writeUTF("OK!");
						break;
					}
				}
				
				// 대화명을 현재 접속된 전체 사용자에게 알림메시지로 보낸다.
				sendToAll("### [" + name + "] ### 님이 입장했습니다.");
				
				// 대화명과 접속한 클라이언트 Socket을 '대화방Map'에 저장한다.
				clients.put(name, socket);
				System.out.println("현재 접속자 수 : " + clients.size() + "명");
				
				// 한 클라이언트가 보내온 메시지를 전체 클라이언트에게 그대로 보낸다.
				while(dis!=null) {
					sendToAll(dis.readUTF());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// finally절이 실행된다는 것은 해당 클라이언트가 빠져나갔다는 것을 의미한다
				sendToAll("### [" + name + "] ### 님이 퇴장했습니다.");
				
				// 대화방Map에서 대상자를 삭제한다
				clients.remove(name);
				System.out.println("[" + socket.getInetAddress() + ":" 
							+ socket.getPort() + "]에서 접속을 종료했습니다.");
				
				System.out.println("현재 접속자 수 : " + clients.size() + "명");
				
			}
		}
	}

}
