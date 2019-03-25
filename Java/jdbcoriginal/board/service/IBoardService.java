package board.service;

import java.util.List;
import java.util.Map;

import board.vo.BoardVO;

public interface IBoardService {
	
	/**
	 * BoardVO에 담겨진 게시글을 DB에 insert하는 메서드
	 * @param boVo insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1, 실패하면 0을 반환한다.
	 */
	public int insertPost(BoardVO boVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아 해당 게시글을 DB에서 삭제하는 메서드
	 * @param boardNo 삭제할 게시글 번호
	 * @return 삭제성공 : 1, 삭제실패 : 0
	 */
	public int deletePost(int boardNo);
	
	/**
	 * 수정할 게시글 제목과 내용, 게시물 번호를 매개변수로 받아서 DB에 update하는 메서드
	 * @param params update할 게시글 정보가 저장된 BoardVO 객체
	 * @return DB작업이 성공하면 1, 실패하면 0을 반환한다.
	 */
	public int updatePost(BoardVO boardVo);
	
	/**
	 * DB의 JDBC_BOARD테이블의 전체 자료를 가져와서 List에 담아 반환하는 메서드
	 * @return BoardVO객체를 담고 있는 List객체
	 */
	public List<BoardVO> getAllPost();
	
	/**
	 * 검색어를 매개변수로 받아 검색어가 글 제목에 포함된 게시글을 List에 담아 반환하는 메서드
	 * @param keyword 검색할 검색어
	 * @return BoardVO객체를 담고 있는 List객체
	 */
	public List<BoardVO> searchPost(String keyword);
	
	/**
	 * 게시글 번호를 매개변수로 받아 해당 게시글을 DB에서 읽어오는 메서드
	 * @param boardNo 읽을 게시글 번호
	 * @return BoardVO객체를 담고 있는 List객체
	 */
	public List<BoardVO> getSelectedPost(int boardNo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * @param boardNo 조회수를 증가시킬 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int setCountIncrement(int boardNo);
}
