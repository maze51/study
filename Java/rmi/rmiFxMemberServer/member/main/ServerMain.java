package member.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import member.service.IMemberService;
import member.service.MemberServiceImpl;

public class ServerMain {

	public static void main(String[] args) {
		
		try {
			IMemberService service = new MemberServiceImpl();
			Registry reg = LocateRegistry.createRegistry(8888);
			
			reg.rebind("memService", service);
			
			System.out.println("서버 시작...");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
