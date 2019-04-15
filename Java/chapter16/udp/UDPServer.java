package basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
	UDP방식 : 비연결지향, 비신뢰성, 데이터가 순서대로 도착한다는 보장이 없다.
			하지만 TCP방식보다 빠르게 전달된다.
			
	DatagramSocket, DatagramPacket을 사용한다.
	- DatagramSocket => 데이터 송수신과 관련된 클래스 (우체부)
	- DatagramPacket => 주고 받을 데이터와 관련된 클래스 (소포)
			==> 수신을 위한 생성자와  송신을 위한 생성자, 두 가지를 제공
			
	- TCP의 경우는 스트림을 이용하여 데이터를 송수신하지만
	  UDP의 경우 데이터그램을 이용하여 데이터를 송수신한다.
 */
public class UDPServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용(입력) 패킷 변수와 송신용(출력) 패킷 변수 선언
			DatagramPacket inpacket, outpacket;
			
			System.out.println("서버 실행 중...");
			while(true) {
				// 데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				// 수신용 패킷 생성 ==> 데이터가 저장될 byte형 배열과 길이를 설정한다.
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터를 수신한다(데이터가 올 때 까지 기다린다).
				// 수신된 데이터의 패킷 정보가 inpacket변수에 저장된다.
				socket.receive(inpacket);
				
				// 수신받은 패킷에서 상대방의 주소, 포트번호등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				System.out.println("상대방의 IP주소와 포트번호 : " + address + ":" + port);
				
				// 상대방이 보낸 메시지 출력하기
				String msg = new String(inpacket.getData(), 0, inpacket.getLength());
				System.out.println("상대방이 보낸 내용 : " + msg);
				
				//--------------------------------------
				// 상대방에게 메시지 보내기(수신받은 메시지를 그대로 송신할 예정임)
				// 송신용 패킷 생성
				// (전송할 데이터가 저장된 byte형배열, 전송할 자료의 개수(배열길이), 
				//  상대방 주소, 상대방 포트번호)등을 지정하여 생성한다.
				
				byte[] sendMsg = msg.getBytes(); // msg문자열을 byte형 배열로 반환
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 데이터 송신하기
				socket.send(outpacket);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
