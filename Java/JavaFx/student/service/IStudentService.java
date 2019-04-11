package student.service;

import java.util.List;

import student.vo.StudentVO;

public interface IStudentService {
	/**
	 * 매개변수로 받아온 StudentVO객체를 DB에 저장하는 메서드
	 * @param stdVo 저장할 정보가 들어있는 StudentVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertStudent(StudentVO stdVo);
	
	/**
	 * DB의 student2테이블의 전체 데이터를 가져와 List로 반환하는 메서드
	 * @return student2테이블의 전체 데이터를 담고 있는 List객체 반환
	 */
	public List<StudentVO> getAllStudent();
}
