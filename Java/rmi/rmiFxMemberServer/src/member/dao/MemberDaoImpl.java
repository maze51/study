package member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import member.vo.MemberVO;
import util.BuildedSqlMapClient;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;

	private SqlMapClient smc;

	private MemberDaoImpl(){
//		try{
//			Charset charset = Charset.forName("UTF-8"); // UTF-8 설정되어 있지만, 확실하게 하기 위해서 읽어올 형식 지정
//			Resources.setCharset(charset); // 문자 인코딩 캐릭터셋 설정하기
//
//			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
//
//			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
//			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
//
//			rd.close(); // Reader객체 닫기
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
		smc = BuildedSqlMapClient.getSqlMapClient();
	}

	public static MemberDaoImpl getInstance(){
		if(dao==null){
			dao = new MemberDaoImpl();
		}
		return dao;
	}

	// MemberVO의 내용을 DB에 insert하는 메서드
	@Override
	public int insertMember(MemberVO memvo) {

		int cnt = 0;
		try {
			Object obj = smc.insert("mvcMymember.insertMember", memvo);
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

	// 회원 정보를 삭제하는 메서드
	@Override
	public int deleteMember(String memId) {

		int cnt = 0;

		try {
			cnt = smc.delete("mvcMymember.deleteMember", memId);

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	// 회원 정보를 수정하는 메서드
	@Override
	public int updateMember(MemberVO memvo) {

		int cnt = 0;

		try {
			cnt = smc.update("mvcMymember.updateMember", memvo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	// 회원 전체 정보를 List에 담아 반환하는 메서드
	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			memList = smc.queryForList("mvcMymember.getAllMember");

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return memList;
	}

	// 회원 정보가 DB에 존재하는지 확인하는 메서드
	@Override
	public boolean getMember(String memId) {
		boolean flag = false;
		try {
			int cnt = (int)smc.queryForObject("mvcMymember.getMember", memId);
			// 이럴 때 매개변수는 한 개만 쓸 수 있다.
			if(cnt>0){
				flag = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return flag;
	}

	@Override
	public List<MemberVO> getSearchMember(String fieldName, String value) {
		// 매개변수는 하난데 여러 데이터를 옮기려면? Map을 사용한다.
		Map<String, String> searchMap = new HashMap<String, String>();
		List<MemberVO> memList = null;
		
		searchMap.put("searchField", fieldName);
		searchMap.put("searchValue", value);
		try {
			memList = smc.queryForList("mvcMymember.getSearchMember", searchMap);
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}
		
		return memList;
	}
}
