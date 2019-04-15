package rmi.chat.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import rmi.chat.inf.Chat;
import rmi.chat.inf.ChatClientMessage;

public class ChatServer extends UnicastRemoteObject implements Chat{
	
	ArrayList<ChatClientMessage> clientList;
	
	// 생성자
	public ChatServer() throws RemoteException{
		clientList = new ArrayList<>();
	}
	
	// 접속한 클라이언트 객체를 리스트에 추가하는 메서드 구현
	// 매개변수에 접속한 클라이언트 객체가 담겨져서 호출된다.
	@Override
	public void setClient(ChatClientMessage ccm) throws RemoteException {
		clientList.add(ccm);
		System.out.println("새로운 클라이언트 객체 등록 완료");
	}
	
	// 리스트에 등록된 모든 클라이언트에게 메시지를 전달하는 메서드 구현
	// 매개변수에는 클라이언트가 보낸 메시지가 저장되어 호출된다. 
	@Override
	public void setMessage(String msg) throws RemoteException {
		System.out.println("클라이언트가 보내온 메시지 : " + msg); // 굳이 할 필요는 없다
		for(ChatClientMessage ccm : clientList) {
			ccm.setMessage(msg);
		}
	}
	
	
	//------------------------------------------------------------------
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			
			Chat chatServer = new ChatServer();
			
			reg.bind("chat", chatServer);
			System.out.println("채팅 서버가 준비되었습니다.");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
