package prodInfo.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import prodInfo.service.IProdService;
import prodInfo.service.ProdServiceImpl;

public class ServerMain {

	public static void main(String[] args) {
		
		try {
			IProdService service = new ProdServiceImpl();
			Registry reg = LocateRegistry.createRegistry(9999);
			
			reg.rebind("prodInfoService", service);
			System.out.println("서비스 시작...");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
