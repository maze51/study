package board.service;

import java.util.List;
import java.util.Map;

import board.dao.BoardDaoImpl;
import board.dao.IBoardDao;
import board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	
	private IBoardDao dao;
	
	// 생성자
	public BoardServiceImpl() {
		dao = new BoardDaoImpl();
	}

	@Override
	public int insertPost(BoardVO boVo) {
		return dao.insertPost(boVo);
	}

	@Override
	public int deletePost(int boardNo) {
		return dao.deletePost(boardNo);
	}

	@Override
	public int updatePost(BoardVO boardVo) {
		return dao.updatePost(boardVo);
	}

	@Override
	public List<BoardVO> getAllPost() {
		return dao.getAllPost();
	}

	@Override
	public List<BoardVO> searchPost(String keyword) {
		return dao.searchPost(keyword);
	}
	
	// 글 하나를 읽을 때 조회수가 하나 증가해야 한다.
	// 서비스는 보통 처리할 일이 없지만, DAO나 Controller 처리도 가능하지만, 이런 종류의 작업은 서비스에서 처리해 준다
	@Override
	public List<BoardVO> getSelectedPost(int boardNo) {
		// 해당 게시글을 기져오기 전에 조회수를 1 증가시킨다
		int cnt = dao.setCountIncrement(boardNo);
		
		// 조회수 증가가 성공하면 해당 게시글을 가져온다.
		if(cnt>0){
			return dao.getSelectedPost(boardNo);
		} else {
			return null;
		}
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
