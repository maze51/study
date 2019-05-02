package rmi.chat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 서버용 인터페이스
public interface Chat  extends Remote{
	
	// 접속한 클라이언트 객체를 처리(리스트에 추가)하는 메서드
	public void setClient(ChatClientMessage ccm) throws RemoteException;
	
	// 클라이언트가 보낸 메시지를 리스트에 등록된 모든 클라이언트에게
	// 전달하는 메서드
	public void setMessage(String msg) throws RemoteException;
}
