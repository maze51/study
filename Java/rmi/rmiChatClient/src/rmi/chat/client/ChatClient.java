package rmi.chat.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import rmi.chat.inf.Chat;
import rmi.chat.inf.ChatClientMessage;

public class ChatClient extends UnicastRemoteObject implements ChatClientMessage{
	// 생성자
	public ChatClient() throws RemoteException{	}
	
	// 서버가 보내온 메시지를 화면에 출력하는 메서드
	@Override
	public void setMessage(String msg) throws RemoteException {
		System.out.println(msg);
		
	}
	
	//-------------------------------------------------------------------
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			ChatClientMessage ccm = new ChatClient();
			
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			
			Chat chatServer = (Chat) reg.lookup("chat"); // 서버 쪽에 등록한 것을 불러온 것
			
			// 서버에 현재 객체(현재 클라이언트 객체)를 등록한다.
			chatServer.setClient(ccm);
			
			System.out.print("대화명 입력 : ");
			String name = scan.next();
			
			scan.nextLine(); // 입력 버퍼 지우기
			
			while(true) {
				System.out.print(">> ");
				String msg = name + " : " + scan.nextLine();
				chatServer.setMessage(msg);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
