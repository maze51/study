package util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {
	private static SqlMapClient client;
	
	static{
		try {
			Charset charset = Charset.forName("UTF-8"); // UTF-8 설정되어 있지만, 확실하게 하기 위해서 읽어올 형식 지정
			Resources.setCharset(charset); // 문자 인코딩 캐릭터셋 설정하기

			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");

			// 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			client = SqlMapClientBuilder.buildSqlMapClient(rd);

			rd.close(); // Reader객체 닫기
		} catch (IOException e) {
			throw new RuntimeException("SqlMapClient 생성 실패! : " + e, e);
		}
	}
	
	public static SqlMapClient getSqlMapClient(){
		return client;
	}
}
