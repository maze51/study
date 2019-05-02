package prodInfo.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import prodInfo.vo.LprodVO;
import prodInfo.vo.ProdVO;

public interface IProdService extends Remote{
	
	/**
	 * 
	 * @return Lprod의 이름
	 * @throws RemoteException
	 */
	public List<String> viewLprod()throws RemoteException;

	/**
	 * 
	 * @param lprodName
	 * @return prod의 이름
	 * @throws RemoteException
	 */
	public List<String> searchProd(String lprodName)throws RemoteException;
	
	/**
	 * 
	 * @param prodName
	 * @return prod의 전체 정보
	 * @throws RemoteException
	 */
	public List<ProdVO> viewprod(String prodName)throws RemoteException;
	
}


