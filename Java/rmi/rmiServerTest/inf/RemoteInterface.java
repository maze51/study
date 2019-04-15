package basic.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import basic.rmi.vo.FileInfo;
import basic.rmi.vo.TestVO;

/**
 * RMI용 인터페이스
 * @author PC10
 *
 */

// RMI용 인터페이스는 Remote를 상속해야 한다.

public interface RemoteInterface extends Remote{
	// 이 인터페이스에 선언되는 모든 메서드들은 RemoteException을 throws해야 한다.
	
	public int doRemotePrint(String str) throws RemoteException;
	
	public void doPrintList(ArrayList<String> list) throws RemoteException;
	
	public void doPrintVO(TestVO vo) throws RemoteException;
	
	public void setFile(FileInfo finfo) throws RemoteException;
	
}
