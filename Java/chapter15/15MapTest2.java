package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고
이 Phone클래스를 이용하여 전화번호 정보를 관리하는 프로그램을 완성하시오.
이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.
(단, 전체 전화번호 정보는 Map을 이용하여 관리한다.
	- key는 이름으로 하고 value는 Phone클래스의 인스턴스로 한다)

	실행예시)
	=======================
		전화번호 관리 프로그램
	=======================

	메뉴를 선택하세요.
	1. 전화번호 등록
	2. 전화번호 수정
	3. 전화번호 삭제
	4. 전화번호 검색
	5. 전화번호 전체 출력
	6. 전화번호 자료 저장
	0. 프로그램 종료
	-----------------------
	번호입력 >> 1

	새롭게 등록할 전화번호 정보를 입력하세요.
	이름 >> 홍길동
	전화번호 >> 010-1111-1111
	주소 >> 대전시

	홍길동 정보가 저장되었습니다. (나오고 메뉴가 다시 나오도록)

	5번
	-------------------------------------
	번호		이름		전화번호			주소
	-------------------------------------
	 1		홍길동	010-1111-1111	대전시
	 ...
	-------------------------------------
	출력 완료 (나오고 메뉴가 다시 나오도록)

	0번
	프로그램을 종료합니다.
	
	0. 저장할 파일명 ==> 'phoneBook.dat'로 한다.
	1. 6번 메뉴를 구현한다
	2. 프로그램이 처음 실행될 때 저장된 데이터를 읽어오도록 처리한다
	3. 프로그램을 종료할 때 저장이 안 되어 있으면 저장하고 종료하도록 한다.
	// 데이터를 변경하는 작업이 있었는가 판단해서 저장 안 돼 있으면 저장하도록
*/

class Phone1  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4180730697530070849L;
	private String name;
	private String phone;
	private String addr;

	//생성자
	public Phone1() {}

	public Phone1(String name, String addr, String phone){
		this.name = name;
		this.addr = addr;
		this.phone = phone;

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}


}
public class MapTest2{
	private HashMap<String, Phone1> phoneBookMap;
	private Scanner sc;
	private boolean dataChange = false;

	public MapTest2() {
		sc = new Scanner(System.in);
		phoneBookMap = load();
		if(phoneBookMap == null){
			phoneBookMap = new HashMap<String, Phone1>();
		}
	}

	// 메뉴 출력하고 작업할 번호를 입력받는 메서드
	public int displayMenu(){
		while(true){
			System.out.println();
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체 출력");
			System.out.println("6. 전화번호 자료 저장");
			System.out.println("0. 프로그램 종료");
			System.out.println("-----------------------");
			System.out.print("번호입력 >> ");

			int num = sc.nextInt();
			return num;
		}
	}
	// 프로그램을 시작하는 메서드
	public void phoneBookStart(){
		load();
		System.out.println("=======================");
		System.out.println("	전화번호 관리 프로그램");
		System.out.println("=======================");

		while(true){
			int choice = displayMenu(); // 메뉴 출력 및 작업 번호 입력

			switch(choice){
			case 1: // 등록
				insert();
				break;
			case 2: // 수정
				update();
				break;
			case 3: // 삭제
				delete();
				break;
			case 4: // 검색
				search();
				break;
			case 5: // 전체출력
				displayAll();
				break;
			case 6:
				save();
				break;
			case 0:
				if(dataChange == true){
					System.out.println("변경된 내용을 저장합니다.");
					save();
				}
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 선택하세요");
			}
		}
	}
	
	// 파일에서 전화번호 자료를 불러오는 메서드
	private HashMap<String, Phone1> load() {
		HashMap<String, Phone1> pMap = null;
		File file = new File("d:/D_Other/phoneBook.dat");
		if(!file.exists()){
			return null;
		}
		ObjectInputStream dis = null;
		try {
			dis = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("d:/D_Other/phoneBook.dat")
							)
					);
			pMap = (HashMap<String, Phone1>) dis.readObject();

		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			if(dis!=null) try{ dis.close(); } catch(IOException e){}
		}
		return pMap;
	}

	// 전화번호 자료를 파일에 저장하는 메서드
	private void save() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("d:/D_Other/phoneBook.dat")
					)
			);
			oos.writeObject(phoneBookMap);
			System.out.println("저장이 완료되었습니다.");
			dataChange = false;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 스트림 객체 닫기
			if(oos!=null){
				try{
					oos.close();
				} catch(IOException e){
				}
			}
		}
	}

	// 전화번호 정보를 검색하는 메서드
	public void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = sc.next();
		
		if(phoneBookMap.containsKey(name)==false){
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return;
		}
		
		Phone1 p = phoneBookMap.get(name);
		
		System.out.println(name + "씨 전화번호 정보");
		System.out.println("이름 : " + p.getName());
		System.out.println("주소 : " + p.getAddr());
		System.out.println("전화번호 : " + p.getPhone());
		System.out.println();
		System.out.println("검색 완료.");
		
	}

	// 전화번호 정보를 삭제하는 메서드
	public void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = sc.next();
		
		if(phoneBookMap.containsKey(name)==false){
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return;
		}
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨 정보를 삭제했습니다.");
		dataChange = true;
	}

	// 전화번호 정보를 수정하는 메서드
	public void update(){
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = sc.next();
		
		if(phoneBookMap.containsKey(name)==false){
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return;
		}
		sc.nextLine();
		System.out.print("새로운 주소 >> ");
		String addr = sc.nextLine();
		
		System.out.print("새로운 전화번호 >> ");
		String tel = sc.next();
		
		phoneBookMap.put(name, new Phone1(name, addr, tel));
		System.out.println(name + "씨 정보를 수정했습니다.");
		dataChange = true;
	}
	
	// 전체 자료를 출력하는 메서드
	public void displayAll(){
		Set<String> keySet = phoneBookMap.keySet(); // key값을 저장할 Set
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("번호	이름	전화번호	주소");
		System.out.println("------------------------------------");
		if(keySet.size()==0){
			System.out.println("	등록된 전화번호 정보가 하나도 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			int num = 0;
			while(it.hasNext()){
				num++;
				String name = it.next(); // key값이면서 이름인 데이터 구하기
				Phone1 p = phoneBookMap.get(name);
				System.out.println(num + "  " + name + "  " + p.getPhone() + "  " + p.getAddr());
			}
		}
		System.out.println("------------------------------------");
		System.out.println("출력 끝");
	}
	
	// 전화번호 정보를 등록하는 메서드
	public void insert(){
		System.out.println();
		System.out.println("등록할 새로운 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = sc.next();

		// 이미 등록된 사람인지 검사
		//if(phoneBookMap.get(name)!=null){ // key값에 해당하는 value값이 없으면 null	방법1
		if(phoneBookMap.containsKey(name)){ // 주어진 값이 key값에 있으면 true			방법2
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		sc.nextLine(); // 입력 버퍼의 내용(앞 엔터)을 없애준다. nextLine()전에 next()등이 있다면 필요한 부분.
		System.out.print("주소 >> ");
		//String addr = sc.next(); // 가나다 라마바사		입력시 가나다만 들어간다(스페이스, 탭, 엔터로 끊기면 앞만 들어간다)
		String addr = sc.nextLine(); // 띄어쓰기도 모두 인식한다. 아래와 다르게 입력값 전부+엔터값까지 전부 가져간다.
		
		System.out.print("전화번호 >> ");
		String tel = sc.next(); // 111 333 이렇게 넣었다면 일단 버퍼로 다 넣고, 111만 떼어 저장한다.
								// 그 다음 명령이 다음 부분을 가져간다.
								// 333 엔터라면? 333이 들어가고 엔터값이 버퍼에 남는다.

		phoneBookMap.put(name, new Phone1(name, addr, tel));
		System.out.println(name + "씨 정보가 저장되었습니다.");
		dataChange = true;
	}

	public static void main(String[] args) {
		new MapTest2().phoneBookStart();
	}

}