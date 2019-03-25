package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

/*
	LPROD 테이블에 새로운 데이터를 추가하기
	
	조건)
		1. lprod_gu와 lprod_nm은 직접 입력받아 처리하고
		   lprod_id는 현재의 lprod_id값 중 제일 큰 값보다 1 증가된 값으로 한다.
		2. 입력받은 lprod_gu가 이미 등록된 데이터면 이 값을 다시 입력 받는다.

*/
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "sem", "java");
			conn = DBUtil.getConnection();
			
			//String sql = "select max(lprod_id) from lprod";
			String sql = "select max(lprod_id) maxid from lprod";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			int num = 0;
			if(rs.next()){
				//num = rs.getInt("max(lprod_id)");  // 엘리어스가 없을 때
				num = rs.getInt("maxid");  	// 엘리어스가 있을 때
				//num = rs.getInt(1);		// 컬럼 번호로...
			}
			num++;
			//--------------------------------
			
			int count;  // 중복 여부 값을 저장할 변수 (중복되면 1, 그렇지 않으면 0)
			String gu;  // 입력한 lprod_gu값이 저장될 변수
			
			String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
			pstmt = conn.prepareStatement(sql2);
			
			do{
				System.out.print("상품 분류 코드 입력 : ");
				gu = scan.next();
				
				pstmt.setString(1, gu);
				rs = pstmt.executeQuery();
				count = 0;
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count>0){
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count>0);
			
			System.out.print("상품 분류명 입력 : ");
			String nm = scan.next();
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " values (?, ?, ? )";
			pstmt2 = conn.prepareStatement(sql3);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, gu);
			pstmt2.setString(3, nm);
			
			int cnt = pstmt2.executeUpdate();
			
			if(cnt>0){
				System.out.println("등록 성공!!");
			}else{
				System.out.println("등록 실패~~");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		

	}

}












