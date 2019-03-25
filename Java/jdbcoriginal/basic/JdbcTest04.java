package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		// insert작업은 resultset이 굳이 필요없음
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe"
					, "PC10"
					, "java");
			
			System.out.println("은행 계좌 정보 추가하기");
			
			System.out.print("계좌번호 : ");
			String bankNo = scan.next();
			
			System.out.print("은 행 명 : ");
			String bankName = scan.next();
			
			System.out.print("예금주명 : ");
			String bankUserName = scan.next();
			/*
			//----------------------------------------------------------------------------------
			// Statement객체를 이용한 작업
			String sql = "insert into bankinfo(bank_no, bank_name, bank_user_name, bank_date) values ('"
			+ bankNo + "', '" + bankName + "', '" + bankUserName + "', sysdate )";
			
			stmt = conn.createStatement();
			
			// executeQuery() 메서드는 select문을 실행할 때 사용하고
			// executeUpdate() 메서드는 insert, update, delete문을 실행할 때 사용한다.
			int cnt = stmt.executeUpdate(sql); // 반환 int값은 실제 성공한 레코드 수
			//----------------------------------------------------------------------------------
			*/
			
			// PreparedStatement객체를 이용한 작업(쿼리문이 다르다. 데이터가 들어갈 자리에 변수명을 쓰지 않고 ?로 대체)
			
			// SQL문의 ?(물음표)자리는 나중에 데이터가 세팅될 자리라는 표식이다.
			String sql = "insert into bankinfo(bank_no, bank_name, bank_user_name, bank_date) values ( ? , ? , ? , sysdate )";
			
			// SQL문을 포함하는 PreparedStatement객체를 생성한다.
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 세팅한다.
			// 형식) pstmt.set자료형이름(물음표번호, 데이터); // 물음표번호는 1번부터 시작
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUserName);
			
			// 데이터 세팅 후 쿼리문을 실행한다
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값 cnt = " + cnt); // 숫자가 잘 뜨면 작업에 성공한 것
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
	}
}
// Statement객체는 같은 작업을 반복해서 여러번 할 때(인서트를 100번 한다면?) 보낼 때마다 문장을 그때그때 컴파일(해석)한다.
// PreparedStatement객체는 처음 접속시 한 번 컴파일 해놓고 정보를 갖고 있다. 데이터 세팅 부분만 반복된다. ==> 반복 작업시 속도가 더 빠르다.

// Statement객체는 데이터를 세팅할 때 문자를 쭉 연결해서 쓴다. 그 때 조건식을 변조해 쿼리문이 무조건 참이 된다면? 권한 없는 데이터도 획득할 수 있다는 위험성이 있다. 
// PreparedStatement는 ? String이라면 ''를 자동으로 붙여준다. 조건식 변조가 상대적으로 어렵다.

//조건이랄 게 없는 select * from table 같은 경우에는 뭘 쓰든 상관없다(Statement가 더 간단)