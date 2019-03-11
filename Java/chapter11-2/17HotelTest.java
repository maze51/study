package basic;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
/*
class Room {
	private int roomNo;
	private String roomType;
	private String guestName;

	Room() {

	}

	public Room(int roomNo, String roomType) {
		super();
		this.roomNo = roomNo;
		this.roomType = roomType;
	}

	public Room(int roomNo, String roomType, String guestName) {
		super();
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.guestName = guestName;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
}

public class HotelTest {
	private TreeMap<Integer, Room> RoomManage = new TreeMap<Integer, Room>();
	Scanner sc = new Scanner(System.in);

	{
		for (int i = 1; i < 10; i++) {
			RoomManage.put(200+i, new Room(200+i, "싱글룸", "-"));
		}
		
		for (int i = 1; i < 10; i++) {
			RoomManage.put(300+i, new Room(300+i, "더블룸", "-"));
		}
		
		for (int i = 1; i < 10; i++) {
			RoomManage.put(400+i, new Room(400+i, "스위트룸", "-"));
		}
	}


	// 메뉴 출력
	public int displayMenu(){
		while(true){
			System.out.println();
			System.out.println("--------------------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
			System.out.println("--------------------------------------------------");
			System.out.print("선택>> ");
			int num = 0;
			try {
				num = sc.nextInt();
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해주세요.");
				continue;
			}
			
			return num;
		}
	}
	// 프로그램 시작
	public void roomManageStart() {
		System.out.println("***************************************");
		System.out.println("\t 호텔문을 열었습니다. 어서오십시오.");
		System.out.println("***************************************");

		while(true){
			int choice = displayMenu();

			switch(choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomList();
				break;
			case 4:
				System.out.println("***************************************");
				System.out.println("\t 호텔문을 닫았습니다.");
				System.out.println("***************************************");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 선택하세요");
			}
		}
	}

	// 체크인
	private void checkIn() {
		System.out.println("--------------------------------------------------");
		System.out.println("  체크인 작업");
		System.out.println("--------------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("--------------------------------------------------");
		System.out.print("방 번호 입력 >> ");

		int num = sc.nextInt();
		String roomType = null;

		if(RoomManage.containsKey(num)){
			if(201 <= num && num <= 209){
				roomType = "싱글룸";
			} else if(301 <= num && num <= 309){
				roomType = "더블룸";
			} else if(401 <= num && num <= 409){
				roomType = "스위트룸";
			} 
			
			if(RoomManage.get(num).getGuestName().equals("-")){
				System.out.println("누구를 체크인 하시겠습니까?");
				System.out.print("이름 입력 >> ");
				String name = sc.next();
				RoomManage.put(num, new Room(num, roomType, name));
				System.out.println("체크인이 완료되었습니다.");
			} else {
				System.out.println(num + "호 객실은 이미 손님이 있습니다.");
			}
			
		} else {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
		}

	}

	// 체크아웃
	private void checkOut() {
		System.out.println("--------------------------------------------------");
		System.out.println("  체크아웃 작업");
		System.out.println("--------------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");

		int num = sc.nextInt();
		String roomType = null;

		if(RoomManage.containsKey(num)){
			if(201 <= num && num <= 209){
				roomType = "싱글룸";
			} else if(301 <= num && num <= 309){
				roomType = "더블룸";
			} else if(401 <= num && num <= 409){
				roomType = "스위트룸";
			} 

			if(RoomManage.get(num).getGuestName().equals("-")){
				System.out.println(num + "호 객실에는 체크인 한 사람이 없습니다.");
			} else {
				String name = RoomManage.get(num).getGuestName();
				RoomManage.remove(num);
				RoomManage.put(num, new Room(num, roomType, "-"));
				System.out.println(num + "호 객실의 " + name + "님 체크아웃을 완료하였습니다.");
			} 

		} else {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
		}

	}

	// 객실상태
	private void roomList() {
		Set<Integer> keySet = RoomManage.keySet();

		System.out.println("--------------------------------------------------");
		System.out.println("현재 객실 상태");
		System.out.println("--------------------------------------------------");
		System.out.println("방번호  방종류  투숙객 이름");
		System.out.println("--------------------------------------------------");
		Iterator<Integer> it = keySet.iterator();
		
		while(it.hasNext()){
			int room = it.next();
			Room r = RoomManage.get(room);
			System.out.println(r.getRoomNo() + "   " + r.getRoomType() + "   " + r.getGuestName());
		}
		
		
	}

	public static void main(String[] args) {

		HotelTest a = new HotelTest();
		a.roomManageStart();
	}

} // end of HotelTest class
*/