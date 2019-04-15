package student.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import student.service.IStudentService;
import student.service.StudentServiceImpl;

public class StudentMain {

	public static void main(String[] args) {
		
		try {
			IStudentService service = new StudentServiceImpl();
			Registry reg = LocateRegistry.createRegistry(7777);
			
			reg.rebind("studentService", service); // 객체의 Alias, 객체의 인스턴스
			
			System.out.println("서버 시작...");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

}
