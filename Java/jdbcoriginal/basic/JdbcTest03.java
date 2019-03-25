package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/*
	문제2) lprod_id값을 2개 입력받아서 두 값중 작은 값부터 큰 값 사이의 자료들을 출력하시오.
		
 */
public class JdbcTest03 {

	public static void main(String[] args) {
		int user = Integer.parseInt(JOptionPane.showInputDialog("검색할 lprod_id 첫번째 값을 입력해주세요"));
		int user1 = Integer.parseInt(JOptionPane.showInputDialog("검색할 lprod_id 두번째 값을 입력해주세요"));
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		if(user<user1){
			int temp = 0;
			temp = user;
			user = user1;
			user1 = temp;
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"PC10",
					"java");
			
			String sql = "select lprod_id, lprod_gu, lprod_nm from lprod where lprod_id between " + user1 + " and " + user;
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("실행 쿼리문 : " + sql);
			System.out.println("== 쿼리문 실행 결과 ==");
			
			while(rs.next()){
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
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
