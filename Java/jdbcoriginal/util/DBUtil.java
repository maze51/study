package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 클래스 작성

// 이 클래스는 오라클DB 기준. 다른 DBMS에서 돌린다면? 소스가 있다면 고치면 그만,
// 하지만 회사에 보통 납품할 때는 컴파일된 클래스 파일을 넘긴다(소스X).
// 회사 입장에서는 DBMS만 바꿨는데 소스 고치려고 유지보수비를 낭비하게 된다.
// 개발자 입장에서는 수정 없이 어디서든 쓸 수 있게 (자동 변경) 만들고 싶다. + 아이디/패스워드 수정되더라도 쓸 수 있게
//		-> txt파일 값을 읽어다가 클래스에서 사용하도록 수정할 필요가 있다!

public class DBUtil {

	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace(); // 여기서 처리했으므로 사용할 때 이 부분 예외처리는 굳이 필요없다.
		}
	}

	public static Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC10", "java");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			return null;
		}
	}
}
