package prodInfo.dao;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapConfigParser;

import prodInfo.vo.LprodVO;
import prodInfo.vo.ProdVO;
import util.BuildedSqlMapClient;

public class ProdDaoImpl implements IProdDao{
	
	private static ProdDaoImpl dao;
	
	private SqlMapClient smc;
	
	private ProdDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ProdDaoImpl getInstance() {
		if(dao==null) {
			dao = new ProdDaoImpl();
		}
		return dao;
	}

	@Override
	public List<String> viewLprod() throws RemoteException {
		List<String> lpNameList = null;
		
		try {
			lpNameList = smc.queryForList("prodSearch.searchLprod");
			
		} catch (SQLException e) {
			lpNameList = null;
			e.printStackTrace();
		}
		return lpNameList;
	}

	@Override
	public List<String> searchProd(String lprodName) throws RemoteException {
		List<String> pNameList = null;
		
		try {
			pNameList = smc.queryForList("prodSearch.searchProdName",lprodName);
			
		} catch (SQLException e) {
			pNameList = null;
			e.printStackTrace();
		}
		return pNameList;
	}

	@Override
	public List<ProdVO> viewprod(String prodName) throws RemoteException {
		List<ProdVO> PVOList = null;
		try {
			PVOList = smc.queryForList("prodSearch.searchProd",prodName);
		} catch (SQLException e) {
			PVOList = null;
			e.printStackTrace();
		}
		return PVOList;
	}
	

	

	
	
	

}
