package student.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import student.dao.IStudentDao;
import student.dao.StudentDaoImpl;
import student.vo.StudentVO;

public class StudentServiceImpl extends UnicastRemoteObject implements IStudentService {
	private IStudentDao dao;
	
	public StudentServiceImpl() throws RemoteException {
		dao = StudentDaoImpl.getInstance();
	}
	@Override
	public int insertStudent(StudentVO stdVo) throws RemoteException {
		return dao.insertStudent(stdVo);
	}

	@Override
	public List<StudentVO> getAllStudent() throws RemoteException {
		return dao.getAllStudent();
	}

}
