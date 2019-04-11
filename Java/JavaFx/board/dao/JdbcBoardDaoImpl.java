package board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.vo.JdbcBoardVO;
import util.BuildedSqlMapClient;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	
	// 자기 클래스의 참조값을 저장할 변수 선언
	private static JdbcBoardDaoImpl dao;
	
	// SqlMapClient 생성/저장용 변수
	private SqlMapClient smc;
	
	private JdbcBoardDaoImpl(){
		// SqlMapClient 객체 생성 후 저장
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	// 자기 클래스의 객체를 생성해서 반환하는 메서드
	public static JdbcBoardDaoImpl getInstance(){
		// 참조값을 저장할 변수가 비었다면 인스턴스화 작업을 수행하고 변수에 담아 저장한다
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
	public List<JdbcBoardVO> getSearchBoardList(String fieldName, String keyword) {
		List<JdbcBoardVO> boardList = new ArrayList<JdbcBoardVO>();
		Map<String, String> searchMap = new HashMap<String, String>();
		
		keyword = keyword.trim();
		
		searchMap.put("fieldName", fieldName);
		searchMap.put("keyword", keyword);
		
		try {
			boardList = smc.queryForList("board.getSearchBoard", searchMap);
			
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
