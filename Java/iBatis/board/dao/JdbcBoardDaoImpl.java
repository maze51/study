package board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import board.vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private static JdbcBoardDaoImpl dao;
	
	private SqlMapClient smc;
	
	private JdbcBoardDaoImpl(){
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImpl getInstance(){
		if(dao==null) dao = new JdbcBoardDaoImpl();
		return dao;
	}

	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("board.insertBoard", jBoardVo);
			if(obj==null){
				cnt = 1;
			} else {
				cnt = 0;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			cnt = smc.update("board.updateBoard", jBoardVo);
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
			
			boardList = smc.queryForList("board.getAllBoard");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		List<JdbcBoardVO> boardList = new ArrayList<JdbcBoardVO>();
		jBoardTitle = jBoardTitle.trim();
		try {
			boardList = smc.queryForList("board.getSearchBoard", jBoardTitle);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		
		return boardList;
	}
	// 글번호로 하나의 레코드를 가져오는 메서드
	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		try {
			jBoardVo = (JdbcBoardVO) smc.queryForObject("board.getBoard", boardNo);
		} catch (SQLException e) {
			jBoardVo = null;
			e.printStackTrace();
		} 
		return jBoardVo;
	}
	// 조회수 증가 메서드
	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.update("board.setCountIncrement", boardNo);
					
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

}
