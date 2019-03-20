package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;
/*
	회원 관리를 하는 프로그램을 작성하시오.

	조건)
	1. 아래 메뉴의 기능을 구현한다(CRUD기능 구현하기).
	2. 자료 삭제는 회원ID를 입력받아 수행한다.
	3. 중복되는 ID가 입력되면 다시 입력받아서 처리한다.
	4. 자료 수정은 ID만 수정할 수 없고 나머지 데이터 전부를 (한꺼번에)수정한다.

	메뉴예시)
		=== 작업 선택 ===
		1. 자료 입력		--> insert (C)
		2. 자료 삭제		--> delete (D)
		3. 자료 수정		--> update (U)
		4. 전체 자료 출력	--> select (R)
		0. 작업 끝
 */

public class MemberInfoTest {
	public Scanner scan = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	Statement stmt = null;
	ResultSet rs = null;

	String sqlIdSearch = "select count(*) cnt from mymember where MEM_ID = ?";

	int count;
	String id;

	public static void main(String[] args) {
		new MemberInfoTest().memberInfoStart();
	}

	public void memberInfoStart(){
		System.out.println("=======================");
		System.out.println("	회원 관리 프로그램");
		System.out.println("=======================");

		while(true){
			int choice = displayMenu(); // 메뉴 출력 및 작업 번호 입력

			switch(choice){
			case 1: // 입력
				insert();
				break;
			case 2: // 삭제
				delete();
				break;
			case 3: // 수정
				update();
				break;
			case 4: // 검색
				select();
				break;
			case 0: // 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 선택하세요");
			}
		}
	}

	private int displayMenu() {
		while(true){
			System.out.println();
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1. 회원정보 입력");
			System.out.println("2. 회원정보 삭제");
			System.out.println("3. 회원정보 수정");
			System.out.println("4. 전체 자료 출력");
			System.out.println("0. 프로그램 종료");
			System.out.println("-----------------------");
			System.out.print("번호입력 >> ");

			int num = scan.nextInt();
			return num;
		}
	}

	private void insert() {
		try{
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement(sqlIdSearch);
			do{
				System.out.print("회원의 ID를 입력하세요.");
				id = scan.next();

				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				count = 0;
				if(rs.next()){
					count=rs.getInt("cnt");
				}
				if(count>0){
					System.out.println("입력한 회원 ID는 이미 등록된 ID입니다. 다시 입력하세요");
				}
			} while(count>0);

			System.out.print("회원의 이름을 입력하세요.");
			String name = scan.next();
			System.out.print("회원의 전화번호를 입력하세요.");
			String tel = scan.next();
			System.out.print("회원의 주소를 입력하세요.");
			String addr = scan.next();

			String sqlInsert = "insert into mymember(mem_id, mem_name, mem_tel, mem_addr) values (?, ?, ?, ?)";
			pstmt2 = conn.prepareStatement(sqlInsert);
			pstmt2.setString(1, id);
			pstmt2.setString(2, name);
			pstmt2.setString(3, tel);
			pstmt2.setString(4, addr);

			int cnt = pstmt2.executeUpdate();

			if(cnt>0){
				System.out.println("회원정보 입력 성공");
			} else {
				System.out.println("회원정보 입력 실패");
			}
			return;
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
		}
	}

	private void delete() {
		try {
			conn = DBUtil.getConnection();
			pstmt2 = conn.prepareStatement(sqlIdSearch);

			String sqlDelete="delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sqlDelete);

			do{
				System.out.print("삭제할 회원의 ID를 입력하세요");
				id = scan.next();
				pstmt2.setString(1, id);
				rs = pstmt2.executeQuery();
				count = 0;
				if(rs.next()){
					count=rs.getInt("cnt");
				}
				if(count==0){
					System.out.println("입력한 회원 ID는 없는 ID입니다. 다시 입력하세요");
				}
			} while(count==0);

			pstmt.setString(1, id);
			int cnt = pstmt.executeUpdate();

			if(cnt>0){
				System.out.println("회원정보 삭제 성공");
			} else {
				System.out.println("회원정보 삭제 실패");
			}
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
		}
	}

	private void update() {
		try {
			conn = DBUtil.getConnection();
			pstmt2 = conn.prepareStatement(sqlIdSearch);
			String sqlUpdate="update mymember set mem_name = ? , mem_tel = ? , mem_addr = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sqlUpdate);

			do{
				System.out.print("수정할 회원의 ID를 입력하세요");
				id = scan.next();
				pstmt2.setString(1, id);
				rs = pstmt2.executeQuery();
				count = 0;
				if(rs.next()){
					count=rs.getInt("cnt");
				}
				if(count==0){
					System.out.println("입력한 회원 ID는 없는 ID입니다. 다시 입력하세요");
				}
			} while(count==0);
			System.out.print("수정할 회원의 이름을 입력하세요.");
			String name = scan.next();
			System.out.print("수정할 회원의 전화번호를 입력하세요.");
			String tel = scan.next();
			System.out.print("수정할 회원의 주소를 입력하세요.");
			String addr = scan.next();

			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, addr);
			pstmt.setString(4, id);

			int cnt = pstmt.executeUpdate();

			if(cnt>0){
				System.out.println("회원정보 수정 성공");
			} else {
				System.out.println("회원정보 수정 실패");
			}
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
		}
	}

	private void select() {
		try {
			conn = DBUtil.getConnection();
			String sqlSelect="select * from mymember";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sqlSelect);
			System.out.println("==========================================");
			System.out.println("회원ID\t회원이름\t전화번호\t\t주소");
			System.out.println("==========================================");
			while(rs.next()){
				System.out.println(rs.getString("mem_id")+"\t"+rs.getString("mem_name")
						+"\t"+rs.getString("mem_tel")+"\t"+rs.getString("mem_addr"));
			}
			System.out.println("==========================================");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

