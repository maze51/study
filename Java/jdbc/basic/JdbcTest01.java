package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC (Java DataBase Connectivity)
public class JdbcTest01 {
/*
	데이터베이스 처리 순서

1. 드라이버 로딩 ==> JDBC라이브러리를 JVM의 메모리에 적재한다.
	Class.forName("oracle.jdbc.driver.OracleDriver");

2. DB시스템에 접속하기 ==> 접속이 완료되면 Connection객체가 만들어진다.
	DriverManager.getConnection()메서드를 이용한다.

3. 질의하기 ==> Statement객체 또는 PreparedStatement객체를 이용하여
			   SQL문을 실행하고 그 결과를 받아온다.

4. 받아온 결과를 처리한다.
	1) SQL문 select문일 경우 ==> ResultSet객체가 만들어진다.
		(ResultSet객체에는 select한 결과가 저장된다.)
	2) SQL문이 insert, update, delete 등일 경우 ==> 정수값을 반환한다.
		(정수값은 보통 실행에 성공한 레코드 수를 말한다.)

5. 사용한 자원을 반납한다.
*/	
	public static void main(String[] args) {
		// DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. 접속 ==> Connection객체 생성
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe", 
						"sem", 
						"java");
			// 3. 실행할 SQL문을 작성하고, Statement객체를 생성한다.
			//		Statement객체와 PreparedStatement객체는 Connection객체를
			//		이용하여 생성한다.
			String sql = "select * from lprod";
			stmt = conn.createStatement();  // Statement객체 생성
			
			// 4. SQL문을 실행하고 그 결과를 받아온다.
			//    (실행은 Statement객체나 PreparedStatement객체를 이용하여 실행한다.)
			//    (select문의 실행 결과는 ResultSet객체에 담는다.)
			rs = stmt.executeQuery(sql);
			
			// 5. 받아온 결과를 처리한다.
			//	  select문의 결과가 ResultSet객체에 저장되었으므로
			//	  반복문과 next()메서드를 이용하여 전체 자료를 차례로 읽어와 처리한다.
			System.out.println("실행 쿼리문 : " + sql);
			System.out.println("== 쿼리문 실행 결과 ==");
			
			// rs.next() ==> ResultSet객체의 데이터를 가리키는 포인터를
			//				 다음 레코드 위치로 이동시키고, 그 곳의 데이터가 있으면
			//				 true를 반환하고 없으면 false를 반환한다.
			while(rs.next()){
				// ResultSet객체의 포인터가 가리키는 위치의 레코드에서
				// 각 컬럼값을 가져오는 방법
				// 형식1) rs.get자료형명("컬럼명")
				// 형식2) rs.get자료형명("엘리어스명")
				// 형식3) rs.get자료형명(컬럼번호)   // 컬럼번호는 1번부터 시작
				System.out.println("lprod_id : " + rs.getInt("lprod_id")); // 컬럼명 이용
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString(3));  // 컬럼번호 이용
				System.out.println("-----------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 6. 사용했던 자원들을 모두 반납한다.
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}

	}

}












