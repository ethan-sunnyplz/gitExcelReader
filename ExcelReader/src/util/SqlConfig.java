package util;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.io.Reader;

public class SqlConfig {

	private static final SqlMapClient sqlMap;

	static {
		try {
			String resource = "sqlMap-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private SqlConfig() {
	}

	public static SqlMapClient getSqlMapClient() {
		return sqlMap; //�ڽ��� .�������� �ʰ� sqlMap�� ����, ������ �̱��� ���� 
	}
}