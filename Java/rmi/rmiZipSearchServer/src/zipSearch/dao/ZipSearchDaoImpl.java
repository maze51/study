package zipSearch.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import zipSearch.vo.ZipSearchVO;

public class ZipSearchDaoImpl implements IZipSearchDao{
	private static ZipSearchDaoImpl dao;
	
	private SqlMapClient smc;
	
	private ZipSearchDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ZipSearchDaoImpl getInstance() {
		if(dao==null) {
			dao = new ZipSearchDaoImpl();
		}
		return dao;
	}
	
	// 매개변수로 받은 검색어로 일치하는 우편번호를 검색하는 메서드
	@Override
	public List<ZipSearchVO> searchZip(String fieldName, String keyword) {
		Map<String, String> searchMap = new HashMap<String, String>();
		List<ZipSearchVO> zipList = null;
		
		searchMap.put("searchField", fieldName);
		searchMap.put("searchValue", keyword);
		
		try {
			zipList = smc.queryForList("zipSearch.getSearchZip", searchMap);
		} catch (SQLException e) {
			zipList = null;
			e.printStackTrace();
		}
		
		return zipList;
	}
}
