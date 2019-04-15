package member.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import member.dao.IMemberDao;
import member.dao.MemberDaoImpl;
import member.vo.MemberVO;

public class MemberServiceImpl extends UnicastRemoteObject implements IMemberService {
	private IMemberDao dao;
//	private static MemberServiceImpl service;
//	
//	// 생성자
//	private MemberServiceImpl() {
//		//dao = new MemberDaoImpl();
//		dao = MemberDaoImpl.getInstance();
//	}
//	
//	public static MemberServiceImpl getInstance(){
//		if(service==null) service = new MemberServiceImpl();
//		return service;
//	}
	
	// 생성자
	public MemberServiceImpl() throws RemoteException {
		dao = MemberDaoImpl.getInstance();
	}
	
	@Override
	public int insertMember(MemberVO memvo) throws RemoteException {
		return dao.insertMember(memvo);
	}

	@Override
	public int deleteMember(String memId) throws RemoteException {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memvo) throws RemoteException {
		return dao.updateMember(memvo);
	}

	@Override
	public List<MemberVO> getAllMember() throws RemoteException {
		return dao.getAllMember();
	}

	@Override
	public boolean getMember(String memId) throws RemoteException {
		return dao.getMember(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(String fieldName, String value) throws RemoteException {
		return dao.getSearchMember(fieldName, value);
	}

}
