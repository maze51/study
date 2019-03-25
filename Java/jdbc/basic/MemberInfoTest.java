package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;
import util.DBUtil2;
import util.DBUtil3;

/*
	회원 관리를 하는 프로그램을 작성하시오.
	
	조건)
		1. 아래 메뉴의 기능을 구현한다.(CRUD기능 구현하기)
		2. 자료 삭제는 회원ID를 입력 받아 삭제한다.
		3. 중복되는 ID가 입력되면 다시 입력 받아서 처리한다.
		4. 자료 수정은 ID만 수정할 수 없고 나머지 데이터 전부를 수정한다.
	
	메뉴예시)
		=== 작업 선택 ===
		1. 자료 입력			--> insert (C)
		2. 자료 삭제			--> delete (D)
		3. 자료 수정			--> update (U)
		4. 전체 자료 출력	--> select (R)
		0. 작업 끝.

*/
public class MemberInfoTest {
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new MemberInfoTest().startMember();
	}
	
	// 메뉴를 출력하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("=== 작업 선택 ===");
		System.out.println("  1. 자료 입력 ");
		System.out.println("  2. 자료 삭제 ");
		System.out.println("  3. 자료 수정 ");
		System.out.println("  4. 전체 자료 출력 ");
		System.out.println("  0. 작업 끝. ");
		System.out.println("-------------------");
		System.out.print("작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	// 작업을 시작하는 메서드
	public void startMember(){
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 : // 자료 입력
					insertMember();
					break;
				case 2 : // 자료 삭제
					deleteMember();
					break;
				case 3 : // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayAllMember();
					break;
				case 0 :  // 작업 끝.
					System.out.println("작업을 마칩니다.");
					return;
				default : System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
				
			}
		}
	}
	
	// 회원 정보를 수정하는 메서드
	public void updateMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			System.out.println();
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?"; 
			pstmt2 = conn.prepareStatement(sql);
			System.out.print("수정할 회원 ID 입력 >> ");
			String memId = scan.next();
			int count = 0;
			pstmt2.setString(1, memId);
			rs = pstmt2.executeQuery();
			if(rs.next()){
				count = rs.getInt("cnt");
			}
			if(count<1){  // 대상회원이 없을 때
				System.out.println(memId + "은(는) 없는 회원입니다.");
				System.out.println("수정 작업 종료...");
				return;
			}
			
			// 대상회원이 있을 때
			System.out.print("새로운 이름 >> ");
			String memName = scan.next();
			
			System.out.print("새로운 전화번호 >> ");
			String memTel = scan.next();
			
			scan.nextLine();
			System.out.print("새로운 주소 >> ");
			String memAddr = scan.nextLine();
			
			String sql2 = "update mymember set mem_name = ?, mem_tel = ?,"
					+ " mem_addr = ? where mem_id = ? ";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원의 회원정보를 수정했습니다.");
			}else{
				System.out.println("수정작업 실패!!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
	}
	
	
	// 회원 정보를 삭제하는 메서드
	public void deleteMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where mem_id = ? ";
			System.out.print("삭제할 회원 ID 입력 >> ");
			String memId = scan.next();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0){
				System.out.println(memId + "를(을) 삭제했습니다.");
			}else{
				System.out.println(memId + " 회원이 없거나 삭제 실패입니다.");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
	}
	
	
	
	// 전체 회원 정보를 출력하는 메서드
	public void displayAllMember(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("  ID     이름      전화번호       주소");
		System.out.println("------------------------------------------------");
		try {
			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				System.out.println( memId + "    " + memName + "    " 
							+ memTel + "    " + memAddr);
			}
			System.out.println("------------------------------------------------");
			System.out.println("출력 작업 완료...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
	}
	
	
	// 회원 정보를 추가(입력)하는 메서드
	public void insertMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			System.out.println("추가할 회원 정보를 입력하세요.");
			int count;
			String memId;
			String sql = "select count(*) cnt from mymember where mem_id = ?"; 
			pstmt2 = conn.prepareStatement(sql);
			do{
				System.out.print("회원 ID >> ");
				memId = scan.next();
				count = 0;
				pstmt2.setString(1, memId);
				rs = pstmt2.executeQuery();
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count>0){
					System.out.println(memId + "은(는) 이미 등록된 ID입니다.");
					System.out.println("다시 입력해 주세요.");
				}
				
			}while(count>0);
			
			System.out.print("회원 이름 >> ");
			String memName = scan.next();
			System.out.print("회원 전화번호 >> ");
			String memTel = scan.next();
			
			scan.nextLine();  // 버퍼 내용 비우기
			System.out.print("회원 주소 >> ");
			String memAddr = scan.nextLine();
			
			String sql2 = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
					+ " values(?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원 정보를 추가했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
	}
	

}













