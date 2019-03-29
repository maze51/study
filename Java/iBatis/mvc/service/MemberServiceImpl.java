package mvc.service;

import java.util.List;

import mvc.dao.IMemberDao;
import mvc.dao.MemberDaoImpl;
import mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao;
	private static MemberServiceImpl service;
	
	// 생성자
	private MemberServiceImpl() {
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance(){
		if(service==null) service = new MemberServiceImpl();
		return service;
	}

	@Override
	public int insertMember(MemberVO memvo) {
		return dao.insertMember(memvo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memvo) {
		return dao.updateMember(memvo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public boolean getMember(String memId) {
		return dao.getMember(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(String fieldName, String value) {
		return dao.getSearchMember(fieldName, value);
	}

}
