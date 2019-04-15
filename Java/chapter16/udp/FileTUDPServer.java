package basic.udp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.imageio.ImageIO;

public class FileTUDPServer {
	
	private static InputStream is;
	private static FileOutputStream fos;
	
	public static void main(String[] args) {
		File file = new File("d:/D_Other/연습용/Tulips복사본.jpg");
		
		try {
			DatagramSocket socket = new DatagramSocket(7788);
			
			DatagramPacket inpacket;
			
			System.out.println("서버 대기중");
			
			while(true) {
				byte[] bImg = new byte[1024];
				
				inpacket = new DatagramPacket(bImg, bImg.length);
				
				System.out.println("서버");
				
				socket.receive(inpacket);
				
				System.out.println("파일 다운로드 시작");
				
				bImg = inpacket.getData();
				
				BufferedImage img = null;
				
				img = ImageIO.read(new ByteArrayInputStream(bImg));
				
				ImageIO.write(img, "jpg", file);
				System.out.println("됐나");
//				String msg = new String(inpacket.getData(), 0, inpacket.getLength());
//				fos = new FileOutputStream(file);
				
				
//				int length = 0;
//				while((length=is.read(bImg))!=-1) {	// 클라이언트가 보내온 자료 읽기
//					fos.write(bImg, 0, length); // 읽어온 데이터를 파일에 저장하기
//				}
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
