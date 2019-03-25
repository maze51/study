package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
	lprod_id: 101, lprod_gu:N101, lprod_nm:농산물
	lprod_id: 102, lprod_gu:N102, lprod_nm:수산물
	lprod_id: 103, lprod_gu:N103, lprod_nm:축산물
*/
public class JdbcTest04 {

	public static void main(String[] args) {
		int[] lprodIds = new int[]{101, 102, 103};
		String[] lprodGus = new String[]{"N101", "N102", "N103"};
		String[] lprodNms = new String[]{"농산물", "수산물", "축산물"};
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "sem", "java");
			
			// PreparedStatement객체 이용하기
			String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " values (?, ?, ? )";
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<lprodIds.length; i++){
				// SQL문의 ?자리에 들어갈 데이터 셋팅
				pstmt.setInt(1, lprodIds[i]);
				pstmt.setString(2, lprodGus[i]);
				pstmt.setString(3, lprodNms[i]);
				
				// 데이터 셋팅 후 실행
				int cnt = pstmt.executeUpdate();
				System.out.println(i + "번째 반환값 : " + cnt);
			}
			
			
			/*
			// Statement객체 이용하기
			//-----------------------------------------
			stmt = conn.createStatement();
			for(int i=0; i<lprodIds.length; i++){
				String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
						+ " values (" + lprodIds[i] + ", '" + lprodGus[i] + "', '"
						+ lprodNms[i] + "') ";
				
				int cnt = stmt.executeUpdate(sql);
				System.out.println(i + "번째 반환값 : " + cnt);
			}
			//-----------------------------------------
			  
			*/
			System.out.println("작업 끝...");
			
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








