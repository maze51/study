package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil3;
import board.vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 사용한 자원을 반납하는 메서드
	public void disConnect(){
		if(rs!=null) try{ rs.close(); }catch(SQLException e){}
		if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
		if(conn!=null) try{ conn.close(); }catch(SQLException e){}
	}

	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board(board_no, board_title, "
					+ "board_writer, board_date, board_cnt, board_content) "
					+ "values(board_seq.nextval, ?, ?, sysdate, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, jBoardVo.getBoard_title());
			pstmt.setString(2, jBoardVo.getBoard_writer());
			pstmt.setString(3, jBoardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ "board_title = ? ,"
					+ "board_date = sysdate, "
					+ "board_content = ? "
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, jBoardVo.getBoard_title());
			pstmt.setString(2, jBoardVo.getBoard_content());
			pstmt.setInt(3, jBoardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = new ArrayList<JdbcBoardVO>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ "board_cnt, board_content from jdbc_board "
					+ "order by board_no desc ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 반복문 안에서 가져온 레코드 하나 하나를 VO객체에 매핑하고
			// 이 VO객체를 List에 추가한다.
			while(rs.next()){
				JdbcBoardVO jBoardVo = new JdbcBoardVO();
				jBoardVo.setBoard_no(rs.getInt("board_no"));
				jBoardVo.setBoard_title(rs.getString("board_title"));
				jBoardVo.setBoard_writer(rs.getString("board_writer"));
				jBoardVo.setBoard_date(rs.getString("board_date"));
				jBoardVo.setBoard_cnt(rs.getInt("board_cnt"));
				jBoardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(jBoardVo);
			}
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		List<JdbcBoardVO> boardList = new ArrayList<JdbcBoardVO>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, " // 처음부터 가져올 때 원하는 형식을 지정해서(변환해서) 가져오는 것이 좋다. Date to Date(java형식)는 문제가 생길 가능성이 있다.
					+ "board_cnt, board_content from jdbc_board "
					+ "where board_title like '%' || ? || '%'" // 검색어가 없다면 %%만 들어가고, 모든 컬럼이 나온다. 여기의 ||은 or가 아니라 문자를 연결하는 오라클 연산자.
					+ "order by board_no desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jBoardTitle);
			rs = pstmt.executeQuery();
			
			// 반복문 안에서 가져온 레코드 하나 하나를 VO객체에 매핑하고
			// 이 VO객체를 List에 추가한다.
			while(rs.next()){
				JdbcBoardVO jBoardVo = new JdbcBoardVO();
				jBoardVo.setBoard_no(rs.getInt("board_no"));
				jBoardVo.setBoard_title(rs.getString("board_title"));
				jBoardVo.setBoard_writer(rs.getString("board_writer"));
				jBoardVo.setBoard_date(rs.getString("board_date"));
				jBoardVo.setBoard_cnt(rs.getInt("board_cnt"));
				jBoardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(jBoardVo);
			}
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return boardList;
	}
	// 글번호로 하나의 레코드를 가져오는 메서드
	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ "board_cnt, board_content from jdbc_board "
					+ "where board_no = ? ";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				jBoardVo.setBoard_no(rs.getInt("board_no"));
				jBoardVo.setBoard_title(rs.getString("board_title"));
				jBoardVo.setBoard_writer(rs.getString("board_writer"));
				jBoardVo.setBoard_date(rs.getString("board_date"));
				jBoardVo.setBoard_cnt(rs.getInt("board_cnt"));
				jBoardVo.setBoard_content(rs.getString("board_content"));
			}
			
		} catch (SQLException e) {
			jBoardVo = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return jBoardVo;
	}
	// 조회수 증가 메서드
	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set "
					+ "board_cnt = board_cnt + 1 "
					+ "where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
					
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

}
