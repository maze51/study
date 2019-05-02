package basic.rmi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import basic.rmi.inf.RemoteInterface;
import basic.rmi.vo.FileInfo;
import basic.rmi.vo.TestVO;

// interface와 VO객체가 있는 package구조는 
// 클라이언트쪽의 프로젝트와 서버쪽의 프로젝트에 같은 구조로 구성되어 있어야 한다.

public class RemoteClient {

	public static void main(String[] args) {
		try {
			// 서버에서 등록한 객체를 찾기 위해 Registry 객체를 생성한 후
			// 사용할 등록된 객체를 불러온다.
			Registry reg = LocateRegistry.getRegistry("localhost", 8888); // ("서버쪽의 ip주소", 지정한 포트번호)
			
			// 서버에서 등록한 '객체의Alias'를 이용하여 사용할 객체를 불러온다.
			// 형식) Registry 객체변수.lookup("서버에서 등록한 객체의Alias");
			RemoteInterface inf = (RemoteInterface) reg.lookup("server");
			
			// 이제부터는 위에서 불러온 객체(inf)의 메서드를 호출해서 사용할 수 있다.
			int rtn = inf.doRemotePrint("안녕하세요. 클라이언트입니다.");
			System.out.println("반환값 : " + rtn);
			
			ArrayList<String> list = new ArrayList<String>();
			list.add("대전");
			list.add("서울");
			list.add("논산");
			list.add("세종");
			list.add("옥천");
			inf.doPrintList(list);
			System.out.println("list 처리 끝...");
			
			TestVO testVo = new TestVO();
			testVo.setTestId("dditMan");
			testVo.setTestNum(1234);
			inf.doPrintVO(testVo);
			System.out.println("VO 처리 끝...");
			
			// 파일 전송 처리 시작
			System.out.println("파일 전송 처리 시작...");
			File file = new File("D:/D_Other/Tulips.jpg");
			if(!file.exists()) {
				System.out.println("전송할 파일이 없습니다.");
				return;
			}
			
			// 파일을 읽어서 FileInfo객체의 filedata에 담아 서버측 메서드에 매개변수로 전달하면 된다
			int len = (int) file.length(); // 파일의 크기가 배열의 크기와 같다.
			FileInputStream fin = new FileInputStream(file);
			byte[] data = new byte[len]; // 파일크기만한 배열 생성
			fin.read(data); // 파일 내용을 읽어서 배열에 저장한다
			
			// FileInfo객체에 데이터를 세팅한다.
			FileInfo finfo = new FileInfo();
			finfo.setFileName(file.getName());
			finfo.setFileData(data);
			
			// RMI용 메서드를 호출한다
			inf.setFile(finfo);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
