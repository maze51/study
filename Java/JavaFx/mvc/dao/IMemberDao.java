package mvc.dao;

import java.util.List;

import mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여
 * Service객체에 전달하는 DAO의 Interface
 * 
 * @author SEM
 *
 */

public interface IMemberDao {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param memvo insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상, 실패하면 0을 반환한다.
	 */
	public int insertMember(MemberVO memvo);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 DB에서 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 삭제성공 : 1, 삭제실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * 하나의 MemberVO객체를 매개변수로 받아서 DB에 update하는 메서드
	 * @param memvo update할 회원 정보가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memvo);
	
	/**
	 * DB의 MyMember테이블의 전체 자료를 가져와서 List에 담아서 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 검색할 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean getMember(String memId);
	
	/**
	 * 매개변수로 받은 필드명과 검색 내용을 이용하여 회원을 검색하는 메서드
	 * @param fieldName 검색할 컬럼명
	 * @param value 검색할 내용
	 * @return 검색된 데이터를 List에 담아 반환
	 */
	public List<MemberVO> getSearchMember(String fieldName, String value); 
}














