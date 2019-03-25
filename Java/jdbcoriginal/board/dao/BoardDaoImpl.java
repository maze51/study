package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.DBUtil3;
import board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private ResultSet rs;
	
	// 사용한 자원을 반납하는 메서드
	public void disConnect(){
		if(rs!=null) try{ rs.close(); }catch(SQLException e){}
		if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
		if(pstmt2!=null) try{ pstmt2.close(); }catch(SQLException e){}
		if(conn!=null) try{ conn.close(); }catch(SQLException e){}
	}
	
	// BoardVO의 내용을 DB에 insert하는 메서드
	@Override
	public int insertPost(BoardVO boVO) {

		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_date, board_content)values(board_seq.nextVal, ?, ?, sysdate, ?)";
			//board_cnt도 넣는다면 0으로 한다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boVO.getBoardTitle());
			pstmt.setString(2, boVO.getBoardWriter());
			pstmt.setString(3, boVO.getBoardContent());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}

		return cnt;
	}

	// 게시글을 삭제하는 메서드
	@Override
	public int deletePost(int boardNo) {

		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
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

	// 게시글을 수정하는 메서드
	@Override
	public int updatePost(BoardVO boardVo) {

		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_title = ? , board_content = ? where board_no = ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoardTitle());
			pstmt.setString(2, boardVo.getBoardContent());
			pstmt.setInt(3, boardVo.getBoardNo());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}

		return cnt;
	}

	// 게시글 전체 정보를 List에 담아 반환하는 메서드
	@Override
	public List<BoardVO> getAllPost() {
		List<BoardVO> bList = new ArrayList<BoardVO>();

		try {
			conn = DBUtil3.getConnection();
			String sql="select boardNo, boardTitle, boardWriter, to_char(boardDate, 'YYYY-MM-DD') board_date, boardCnt, boardContent from jdbc_board order by board_no desc";
			// board_date는 table alias
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 반복문 안에서 가져온 레코드 하나 하나를 VO객체에 매핑하고 이 VO객체를 List에 추가한다.
			while(rs.next()){
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("board_no"));
				bv.setBoardTitle(rs.getString("board_title"));
				bv.setBoardWriter(rs.getString("board_writer"));
				bv.setBoardDate(rs.getString("board_date"));
				bv.setBoardCnt(rs.getInt("board_cnt"));
				bv.setBoardContent(rs.getString("board_content"));
				bList.add(bv);
			}

		} catch (SQLException e) {
			bList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return bList;
	}

	// 게시글 제목에 검색어를 포함하는 게시글의 정보를 List에 담아 반환하는 메서드
	@Override
	public List<BoardVO> searchPost(String keyword) {
		List<BoardVO> bList = new ArrayList<BoardVO>();

		try {
			conn = DBUtil3.getConnection();
			keyword = keyword.trim();
			String sql="select boardNo, boardTitle, boardWriter, to_char(boardDate, 'YYYY-MM-DD') board_date, boardCnt, boardContent from jdbc_board where board_title like '%' || ? || '%'" + "order by board_no desc ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();

			while(rs.next()){
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("board_no"));
				bv.setBoardTitle(rs.getString("board_title"));
				bv.setBoardWriter(rs.getString("board_writer"));
				bv.setBoardDate(rs.getString("board_date"));
				bv.setBoardCnt(rs.getInt("board_cnt"));
				bv.setBoardContent(rs.getString("board_content"));
				bList.add(bv);
			}

		} catch (SQLException e) {
			bList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return bList;
	}

	// 게시글 번호에 해당하는 게시글 하나를 DB에서 읽어오는 메서드
	@Override
	public List<BoardVO> getSelectedPost(int boardNo) {
		List<BoardVO> bList = new ArrayList<BoardVO>();

		int cnt;
		
		try {
			conn = DBUtil3.getConnection();
			String sql="update jdbc_board set board_cnt = board_cnt + 1 where board_no = ?";

			pstmt2 = conn.prepareStatement(sql);

			pstmt2.setInt(1, boardNo);
			cnt = pstmt2.executeUpdate();
			//--------------------------------------------
			//conn = DBUtil3.getConnection();
			String sql2="select * from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			rs.next();
			BoardVO bv = new BoardVO();
			bv.setBoardNo(rs.getInt("board_no"));
			bv.setBoardTitle(rs.getString("board_title"));
			bv.setBoardWriter(rs.getString("board_writer"));
			bv.setBoardDate(rs.getString("board_date"));
			bv.setBoardCnt(rs.getInt("board_cnt"));
			bv.setBoardContent(rs.getString("board_content"));
			bList.add(bv);

		} catch (SQLException e) {
			bList = null; // 두 경우 중 어디에서 오류가 나더라도 반환값은 null이다
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return bList;
		/*
		BoardVO bv = new BoardVO();
		List<BoardVO> bList = new ArrayList<BoardVO>();

		try {
			conn = DBUtil3.getConnection();
			keyword = keyword.trim();
			String sql="select boardNo, boardTitle, boardWriter, to_char(boardDate, 'YYYY-MM-DD') board_date, boardCnt, boardContent from jdbc_board where board_no = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();

			if(rs.next()){
				bv.setBoardNo(rs.getInt("board_no"));
				bv.setBoardTitle(rs.getString("board_title"));
				bv.setBoardWriter(rs.getString("board_writer"));
				bv.setBoardDate(rs.getString("board_date"));
				bv.setBoardCnt(rs.getInt("board_cnt"));
				bv.setBoardContent(rs.getString("board_content"));
			}

		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		 */
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set board_cnt = board_cnt + 1 where board_no = ? ";
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
