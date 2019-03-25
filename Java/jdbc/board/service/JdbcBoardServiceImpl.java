package board.service;

import java.util.List;

import board.dao.IJdbcBoardDao;
import board.dao.JdbcBoardDaoImpl;
import board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService{
	IJdbcBoardDao dao;
	
	public JdbcBoardServiceImpl() {
		dao = new JdbcBoardDaoImpl();
	}
	
	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		return dao.insertBoard(jBoardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		return dao.updateBoard(jBoardVo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		return dao.getSearchBoardList(jBoardTitle);
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		// 해당 게시글을 가져오기 전에 조회수를 1 증가 시킨다.
		int cnt = dao.setCountIncrement(boardNo);
		
		// 조회수 증가가 성공하면 해당 게시글을 가져온다.
		if(cnt>0) 
			return dao.getBoard(boardNo);
		else 
			return null;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
