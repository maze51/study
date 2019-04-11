package student.service;

import java.util.List;

import student.dao.IStudentDao;
import student.dao.StudentDaoImpl;
import student.vo.StudentVO;

public class StudentServiceImpl implements IStudentService {
	private IStudentDao dao;
	private static StudentServiceImpl service;
	
	private StudentServiceImpl() {
		dao = StudentDaoImpl.getInstance();
	}
	
	public static StudentServiceImpl getInstance() {
		if(service==null) service = new StudentServiceImpl();
		
		return service;
	}

	@Override
	public int insertStudent(StudentVO stdVo) {
		return dao.insertStudent(stdVo);
	}

	@Override
	public List<StudentVO> getAllStudent() {
		return dao.getAllStudent();
	}

}
