package zipSearch.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import zipSearch.service.IZipSearchService;
import zipSearch.service.ZipSearchServiceImpl;

public class ServerMain {

	public static void main(String[] args) {
		try {
			IZipSearchService service = new ZipSearchServiceImpl();
			Registry reg = LocateRegistry.createRegistry(8888);
			
			reg.rebind("zipService", service);
			
			System.out.println("서버 시작!!!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
