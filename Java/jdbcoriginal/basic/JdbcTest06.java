package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*
	LPROD 테이블에 새로운 데이터를 추가하기
	
	조건)
		1. lprod_gu와 lprod_nm은 직접 입력받아 처리하고
		   lprod_id는 현재의 lprod_id값 중 제일 큰 값보다 1 증가된 값으로 한다.
		2. 입력받은 lprod_gu가 이미 등록된 데이터면 이 값을 다시 입력 받는다.(검사해서 있으면 다시)
 */


public class JdbcTest06 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String gu = null;
		
		while(true){
			int checkR = 0;
			System.out.print("추가할 lprod_gu를 입력해주세요");
			gu = scan.next();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC10", "java");
				String sqlFirst = "select count(*) from lprod where lprod_gu='" + gu + "'";
				pstmt = conn.prepareStatement(sqlFirst);
				rs = pstmt.executeQuery(sqlFirst);
				
				if(rs.next()){
					checkR = rs.getInt("COUNT(*)");
				}
				
				if(checkR==1){
					System.out.println("중복 lprod_gu존재");
					continue;
				} else {
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.print("추가할 lprod_nm를 입력해주세요");
		String nm = scan.next();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC10", "java");
			String sqlSelect = "select max(lprod_id) from lprod";
			pstmt = conn.prepareStatement(sqlSelect);
			rs = pstmt.executeQuery(sqlSelect);
			
			rs.next();
			int maxId = rs.getInt("MAX(LPROD_ID)");
			
			String sqlIn = "insert into lprod (lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sqlIn);
			
			pstmt.setInt(1, maxId+1);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			System.out.println("반환값 : " + cnt);
			System.out.println("작업 끝");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
		}
	}

}
