package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
	문제1) 사용자로부터 Lprod_id값을 입력 받아 입력한 값보다
		   Lprod_id값이 큰 자료들을 출력하시오.
		   
	문제2) Lprod_id값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의
		   자료들을 출력하시오.
*/
public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		// 1번 문제
		System.out.print("Lprod_id값 입력 : ");
		int lprodId = scan.nextInt();
		*/
		
		// 2번 문제
		System.out.print("첫번째 Lprod_id값 입력 : ");
		int num1 = scan.nextInt();
		
		System.out.print("두번째 Lprod_id값 입력 : ");
		int num2 = scan.nextInt();
		
		int max, min;
		if(num1>num2){
			max = num1;
			min = num2;
		}else{
			max = num2;
			min = num1;
		}
		
		
		// DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe", 
						"sem", 
						"java");

			// 1번
			//String sql = "select * from lprod where lprod_id > " + lprodId;
			
			// 2번
//			String sql = "select * from lprod where lprod_id >= " + min 
//					+ " and lprod_id <= " + max;
			String sql = "select * from lprod where lprod_id between " + min 
					+ " and " + max ;
			
			stmt = conn.createStatement();  // Statement객체 생성
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("== 쿼리문 실행 결과 ==");
			
			while(rs.next()){
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
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}

	}

}
