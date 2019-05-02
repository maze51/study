package basic.rmi.vo;

import java.io.Serializable;

// RMI에서 데이터 전달용으로 사용할 객체는 네트워크를 통해서
// 전달되어야 하기 때문에 직렬화가 필요하다.
// 그래서 Serializable을 구현해야 한다.

public class TestVO implements Serializable{
	
	private static final long serialVersionUID = -5147699094233327390L;
	
	private String testId;
	private int testNum;
	
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public int getTestNum() {
		return testNum;
	}
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	
}
