package board.service;

import java.util.List;

import mvc.service.MemberServiceImpl;
import board.dao.IJdbcBoardDao;
import board.dao.JdbcBoardDaoImpl;
import board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService{
	private IJdbcBoardDao dao;
	
	// 자기 클래스의 참조값을 저장할 변수 선언
	private static JdbcBoardServiceImpl service;
	
	// 생성자의 접근제어자는 private으로 설정
	private JdbcBoardServiceImpl(){
		// dao와 연결하기 위해 JdbcBoardDaoImpl을 인스턴스화. 연결하는 길을 뚫기.
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	// 자기 클래스의 객체를 생성해서 반환하는 메서드
	public static JdbcBoardServiceImpl getInstance(){
		// 참조값을 저장한 변수가 비었다면 인스턴스화 작업을 수행하고 변수에 담아 반환한다
		if(service==null) service = new JdbcBoardServiceImpl();
		return service;
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
