package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectStreamTest01 {

	public static void main(String[] args) {
		// 객체를 파일에 저장하는 예제
		
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "광주");
		Member mem3 = new Member("이순신", 40, "강원");
		Member mem4 = new Member("강감친", 50, "인천");
		
		try {
			// 출력용 스트림 객체 생성
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("d:/D_Other/memObj.bin")
					)
			);
			
			// 쓰기 작업
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			
			// 스트림 닫기
			oos.close();
			System.out.println("출력 끝");
			System.out.println();
			//----------------------------------------------------------
			
			// 저장한 객체 읽어오기
			// 입력용 스트림 객체 생성
			ObjectInputStream dis = new ObjectInputStream(
				new BufferedInputStream(
					new FileInputStream("d:/D_Other/memObj.bin")
				)
			);
			
			Object obj = null;	// 읽어온 객체를 저장할 변수
			try {
				while((obj=dis.readObject())!=null){
					// 읽어온 객체를 원래의 객체형으로 변환 후 사용한다.
					Member mem = (Member)obj;
					System.out.println("이름 : " + mem.getName());
					System.out.println("나이 : " + mem.getAge());
					System.out.println("주소 : " + mem.getAddr());
					System.out.println("------------------------------");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (EOFException e) { // ObjectInputStream에서 파일의 끝에 도달했을 때 발생하는 Exception
			//e.printStackTrace(); // IO익셉션 걸었을 경우 EOF익셉션 발생: 파일의 끝까지 왔다는 의미.
			System.out.println("출력 완료");
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}

// 저장할 객체 선언
class Member implements Serializable{
	private static final long serialVersionUID = 1432605378974552445L;
	/*
		transient ==> 직렬화 되지 않을 멤버변수에 지정한다.
					   직렬화가 되지 않는 멤버변수는 기본값으로 초기화되어 저장된다.
					  (참조변수 : null, 숫자변수 : 0)
	 */
	private String name;
	private transient int age;
	private transient String addr;
	
	// 생성자
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}