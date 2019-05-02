package rmi.chat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 클라이언트용 인터페이스
public interface ChatClientMessage extends Remote{
	
	// 서버가 보내 온 메시지를 처리(출력)하는 메서드
	public void setMessage(String msg) throws RemoteException;
	
	
}
