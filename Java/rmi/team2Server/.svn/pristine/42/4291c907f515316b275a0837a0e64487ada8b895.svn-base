package prodInfo.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import prodInfo.dao.IProdDao;
import prodInfo.dao.ProdDaoImpl;
import prodInfo.vo.LprodVO;
import prodInfo.vo.ProdVO;

public class ProdServiceImpl extends UnicastRemoteObject implements IProdService{
	
	private IProdDao dao;
	
	public ProdServiceImpl() throws RemoteException{
		dao = ProdDaoImpl.getInstance();
	}

	@Override
	public List<String> viewLprod() throws RemoteException {
		return dao.viewLprod();
	}

	@Override
	public List<String> searchProd(String lprodName) throws RemoteException {
		return dao.searchProd(lprodName);
	}

	@Override
	public List<ProdVO> viewprod(String prodName) throws RemoteException {
		return dao.viewprod(prodName);
	}

	

	

}
