package zipSearch.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import zipSearch.dao.IZipSearchDao;
import zipSearch.dao.ZipSearchDaoImpl;
import zipSearch.vo.ZipSearchVO;

public class ZipSearchServiceImpl extends UnicastRemoteObject implements IZipSearchService {
	
	private IZipSearchDao dao;
	
	public ZipSearchServiceImpl() throws RemoteException {
		dao = ZipSearchDaoImpl.getInstance();
	}
	
	@Override
	public List<ZipSearchVO> searchZip(String fieldName, String keyword) throws RemoteException {
		return dao.searchZip(fieldName, keyword);
	}

}
