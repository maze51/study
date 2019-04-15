package basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 수신용, 송신용 패킷 변수 선언
		DatagramPacket inpacket, outpacket;			// 송수신 전에 필요하다. 패킷 안에는 바이트형이 들어간다
		
		// 수신받을 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];
		
		try {
			// UDP용 소켓 객체 생성
			DatagramSocket socket = new DatagramSocket();
			
			// 받을 곳의 주소 생성
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			while(true) {
				// 전송할 메시지 입력 받기
				System.out.print("보낼 메시지 입력 : ");
				String msg = scan.nextLine();
				
				if("/end".equals(msg.trim())) {
					break;
				}
				// 송신용 패킷 객체 생성
				outpacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length,		// getBytes() String객체를 byte형으로 바꿔준다
								address, 8888);												// 보낼 데이터, 데이터의 크기(길이), 어디로 보낼지, 포트번호
				
				// 서버로 전송
				socket.send(outpacket);				// 패킷을 담아 상대에게 보낸다
				
				// 서버에서 수신한 데이터 출력하기
				// 수신용 패킷 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);		// 수신할 바이트형 배열을 만들어 놓고
				// 수신
				socket.receive(inpacket);			// 수신할 패킷을 지정한다
				
				System.out.println("서버 응답 : " + 
							new String(inpacket.getData(), 0, inpacket.getLength()));	// getData()로 패킷에 저장된 데이터를 가져온다
				System.out.println();				// 뒤를 지정하지 않으면 512바이트(지정한 크기)의 문자가 된다. 데이터 중 0번째부터inpacket.getLength()까지를 잘라 문자로 만든다. trim()과 유사
			}
			
			System.out.println("통신 끝...");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
