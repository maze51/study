package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
	db.properties파일의 내용으로 각 정보를 설정하는 방법

	1. Properties객체 이용하기
 */

public class DBUtil2 {
	static Properties prop; // Properties객체 변수 선언. static영역에서 써야 되니까 static으로 만든다

	static{
		prop = new Properties(); // Properties객체 생성
		try {
			FileInputStream fin = new FileInputStream("res/db.properties");
			prop.load(fin);

			Class.forName(prop.getProperty("driver"));


		} catch (IOException e) {
			System.out.println("DB 설정 파일 입출력 오류입니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			return null;
		}
	}
}

