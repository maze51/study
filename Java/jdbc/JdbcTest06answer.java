package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

public class JdbcTest06answer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select max(lprod_id) maxId from lprod";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			int num = 0; // max(lprod_id)를 담을 값
			
			if(rs.next()){ // 나올 데이터가 하나 뿐이라면 굳이 while을 쓸 필요 없다
				num = rs.getInt("maxId"); // table alias 활용
				//num = rs.getInt(1); // 컬럼 번호 활용
			}
			num++; // 새로 넣을 값은 기존 num값중 가장 큰 값+1이므로
			// max값 구하는 부분-------------------------------------------------
			
			int count; // 중복 여부 값을 저장할 변수 (중복되면 1, 그렇지 않으면 0)
			String gu; // 입력한 lprod_gu값이 저장될 변수
			
			String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
			pstmt = conn.prepareStatement(sql2);
			do{
				System.out.print("상품 분류 코드 입력 : ");
				gu = scan.next();
				
				pstmt.setString(1, gu); // sql문에 들어갈 값
				rs = pstmt.executeQuery();
				count = 0;
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count>0){
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
			} while(count>0);
			
			System.out.print("상품 분류명 입력 : ");
			String nm = scan.next();
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			
			pstmt2 = conn.prepareStatement(sql3);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, gu);
			pstmt2.setString(3, nm);
			
			int cnt = pstmt2.executeUpdate();
			
			if(cnt>0){
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
		}
		
		
	}

}
