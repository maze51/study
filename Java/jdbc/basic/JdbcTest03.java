package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest03 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "sem", "java");
			
			System.out.println("은행 계좌 정보 추가하기");
			
			System.out.print("계좌번호 : ");
			String bankNo = scan.next();
			
			System.out.print("은 행 명 : ");
			String bankName = scan.next();
			
			System.out.print("예금주명 : ");
			String bankUserName = scan.next();
			/*
			//------------------------------------------
			// Statement객체를 이용한 작업
			String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values ('" + bankNo + "' , '" + bankName + "' , '" + bankUserName 
					+ "', sysdate )";
			
			stmt = conn.createStatement();
			
			// executeQuery()메서드는 select문을 실행할 때 사용하고
			// executeUpdate()메서드는 insert, update, delete문을 실행할 때 사용한다.
			int cnt = stmt.executeUpdate(sql);
			//--------------------------------------
			*/
			
			// PreparedStetment객체를 이용한 작업
			
			// SQL문의 ?(물음표)자리에는 나중에 데이터가 셋팅될 자리라는 표식이다.
			String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values (? , ? , ? , sysdate )";
			
			// SQL문을 포함하는 PreparedStatement객체를 생성한다. 
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표번호, 데이터);  // 물음표 번호는 1번부터시작
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUserName);
			
			// 데이터 셋팅 후 쿼리문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값 cnt = " + cnt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
	}
}










