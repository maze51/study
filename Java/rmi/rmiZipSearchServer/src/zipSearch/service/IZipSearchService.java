package zipSearch.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import zipSearch.vo.ZipSearchVO;

public interface IZipSearchService extends Remote{
	
	/**
	 * 매개변수로 받은 검색어로 일치하는 우편번호를 검색하는 메서드
	 * @param fieldName 검색할 컬럼명
	 * @param keyword 검색할 동이름 또는 우편번호
	 * @return 검색된 데이터를 List에 담아 반환
	 * @throws RemoteException 
	 */
	public List<ZipSearchVO> searchZip(String fieldName, String keyword) throws RemoteException;
	
	
}
