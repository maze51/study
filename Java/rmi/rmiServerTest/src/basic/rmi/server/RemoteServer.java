package basic.rmi.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import basic.rmi.inf.RemoteInterface;
import basic.rmi.vo.FileInfo;
import basic.rmi.vo.TestVO;

// RMI에서 메서드를 제공하는 클래스 작성		(작업시 메서드를 제공하는 클래스를 만들 때, 꼭 메인 메서드가 있는 클래스를 만들 필요는 없다)
// ==> UnicastRemoteObject를 상속하고 제공할 메서드를 선언해 놓은
//		인터페이스를 구현한다.

public class RemoteServer extends UnicastRemoteObject implements RemoteInterface {

	// RMI용 인터페이스를 구현하는 클래스는 생성자도 RemoteException을 throws해야 한다
	protected RemoteServer() throws RemoteException { }

	// RMI용 인터페이스에서 선언한 메서드의 매개변수에는 클라이언트에서 서버 쪽으로
	// 보내온 데이터가 저장되고, 반환값은 서버에서 클라이언트 쪽으로 보내는 데이터가 된다.
	@Override
	public int doRemotePrint(String str) throws RemoteException {
		System.out.println();
		System.out.println("doRemotePrint()메서드 처리중...");
		System.out.println("클라이언트에서 보내 온 메시지 : " + str);
		System.out.println("출력을 마칩니다.");
		
		return 100;
	}


	@Override
	public void doPrintList(ArrayList<String> list) throws RemoteException {
		System.out.println();
		System.out.println("doPrintList()메서드 처리중...");
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i));
		}
		System.out.println("list출력 끝...");
	}


	@Override
	public void doPrintVO(TestVO vo) throws RemoteException {
		System.out.println();
		System.out.println("doPrintVO()메서드 처리중...");
		System.out.println("ID : " + vo.getTestId());
		System.out.println("NUM : " + vo.getTestNum());
		System.out.println("VO 출력 끝...");

	}
	
	@Override
	public void setFile(FileInfo finfo) throws RemoteException {
		System.out.println();
		FileOutputStream fout = null;
		String dir = "d:/D_Other/test/";
		System.out.println("파일 저장 시작...");
		
		try {
			fout = new FileOutputStream(dir + finfo.getFileName());
			
			// 클라이언트에서 전달한 파일데이터(byte[])를 서버측의 디스크에 기록한다.
			fout.write(finfo.getFileData());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fout!=null) try { fout.close();} catch(IOException e2) {}
		}
		System.out.println("파일 저장 끝...");
		
	}
	
	//------------------------------------------------------------------
	public static void main(String[] args) {
		// RMI를 사용할 수 있는 서비스 제공하기
		try {
			// RMI용 인터페이스를 구현한 클래스의 인스턴스를 만든다(객체 생성) - 인터페이스 이름을 이용해 만드는 것이 좋다
			RemoteInterface inf = new RemoteServer();
			
			// 구현한 객체를 클라이언트에서 찾을 수 있도록 관리하는 Registry객체를 생성한다.
			// RMI용 기본 포트번호는 1099
			Registry reg = LocateRegistry.createRegistry(8888);
			
			// Registry에 RMI용 인터페이스를 구현한 객체를 등록한다.
			// 형식) Registry객체변수.rebind("객체의 Alias", 객체의 인스턴스);
			//		"객체의 Alias"는 클라이언트에서 등록된 객체를 찾을 때 사용된다.
			reg.rebind("server", inf); // rebind는 기존 등록된 다른 것이 있다면 자동으로 지워준다
			
			// 위와 같이만 쓰면 서버가 작동중인지 잘 알기 어렵다
			System.out.println("서버가 준비되었습니다");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
