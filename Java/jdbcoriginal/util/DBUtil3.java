package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
	db.properties파일의 내용으로 각 정보를 설정하는 방법
	
	2. ResourceBundle객체 이용하기
*/
public class DBUtil3 {
	static ResourceBundle bundle; // ResourceBundle객체 변수 선언
	
	static {
		bundle = ResourceBundle.getBundle("db"); // 객체 생성
		
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("DB 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			return null;
		}
	}
}
