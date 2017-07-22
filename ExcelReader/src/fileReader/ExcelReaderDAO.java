package fileReader;

import java.util.List;
import java.util.Map;

import util.ErrorCode;
import util.SystemException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ExcelReaderDAO {
	private SqlMapClient sqlMapClient;

	public ExcelReaderDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public List readIdData(Map parameter) {
		try {
			return sqlMapClient.queryForList("uploader.readIdData", parameter);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new SystemException(ErrorCode.DB_SELECT_ERROR, null);
		}
	}

	public List readCodeInfo(Map parameter) {
		try {
			return sqlMapClient.queryForList("uploader.readCodeInfo", parameter);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new SystemException(ErrorCode.DB_SELECT_ERROR, null);
		}
	}

	public void updateInCodeName(Map map) {
		try {
			sqlMapClient.update("uploader.updateInCodeName", map);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new SystemException(ErrorCode.DB_INSERT_ERROR, null);
		}
	}

	public void updateNotInCodeName(Map map) {
		try {
			sqlMapClient.update("uploader.updateNotInCodeName", map);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new SystemException(ErrorCode.DB_INSERT_ERROR, null);
		}
	}

	public void insertMappingData(Map map) {
		try {
			sqlMapClient.insert("uploader.insertMappingData", map);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new SystemException(ErrorCode.DB_INSERT_ERROR, null);
		}
	}

	public void insertCodeInstall(Map map) {
		try {
			sqlMapClient.insert("uploader.insertCodeInstall", map);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new SystemException(ErrorCode.DB_INSERT_ERROR, null);
		}
	}
}
