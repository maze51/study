package basic.udp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.imageio.ImageIO;

public class FileTUDPClient {
	
	private OutputStream os;
	private static FileInputStream fis;
	
	public static void main(String[] args) {
		
		File file = new File("d:/D_Other/Tulips.jpg");
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
		}
		
		DatagramPacket outpacket;
		
//		byte[] bImg = new byte[1024];
		
		try {
			BufferedImage img = ImageIO.read(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", baos);
			baos.flush();
			byte[] buffer = new byte[1024];
			buffer = baos.toByteArray();
			
			DatagramSocket socket = new DatagramSocket();
			
			InetAddress address = InetAddress.getByName("localhost");
			
			outpacket = new DatagramPacket(buffer, buffer.length, address, 7788);
			
			socket.send(outpacket);
			System.out.println("성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
