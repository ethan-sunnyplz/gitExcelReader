package fileReader;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import util.SqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ExcelReader {

	// private static Log log = LogFactory.getLog(ExcelReader.class);
	private static SqlMapClient sqlMapClient = SqlConfig.getSqlMapClient();

	public static void main(String[] args) throws SQLException {
		ExcelReaderDAO dao = new ExcelReaderDAO(sqlMapClient);
		ExcelReaderHelper excelReaderHelper = new ExcelReaderHelper();
		
		try {
			sqlMapClient.startTransaction();
			String path = "C:/Users/Administrator/Desktop/�ڵ忬������ - ����.xls"; // ����
			// TODO ���̺� �� �����ؾ� ��
			final String[] tabSort = { "KNFC_DEV", "CODE_INSTALL_UPGRADE", "CODE_ERROR", "CODE_LOAN" }; // ������
																										// ���
																										// ���̺�

			final String STR0 = "CODE_NAME";
			final String STR1 = "CODE_VERSION";
			final String STR2 = "CODENAME";
			final String STR3 = "CODEVERSION";
			final String INPUT_USER_ID = "SYSTEM"; // code_install_rel

			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			int sheetNum = wb.getNumberOfSheets();

			List excelList = new ArrayList();
			List dataList = new ArrayList();
			Map excelMap = new HashMap();

			for (int s = 0; s < sheetNum; s++) {
				excelList = excelReaderHelper.getCellValue(wb, s);

				for (int i = 0; i < excelList.size(); i++) {
					excelMap = (Map) excelList.get(i);
					excelMap.put("table", tabSort[s]);

					if (s == 0 || s == 1 || s == 2) { // CODE_NAME, CODE_VERSION
						excelMap.put("cd_name", STR0);
						excelMap.put("cd_ver", STR1);
					} else { // �ڵ� ������ ��쿡�� �÷����� Ʋ��
						excelMap.put("cd_name", STR2);
						excelMap.put("cd_ver", STR3);
					}

					dataList = dao.readIdData(excelMap); // KNF Code�� Doc ���� ����
															// Ȯ��

					if (dataList.size() > 0) {// dataList�� ����� 1��

						// �ڵ� �����Ϳ��� Code Name�� �ش��ϴ� �ڵ� ����� �����´�.
						Map dataMap = (Map) dataList.get(0);
						List codeInfoList = dao.readCodeInfo(excelMap);// System.out.println("�ش�
																		// ���̺�
																		// ����ID��
																		// ������");

						int size = codeInfoList.size();

						if (codeInfoList.size() > 0) {
							// codeInfo ���̺� codeName�� ������
							// TODO �������� �Ʒ� �ҽ� �����ؾ� ��
							Map tempMap = (Map) codeInfoList.get(0); // codeInfoList
																		// �������
																		// 1��

							if (s != 1) {
								excelMap.put("REG_DATE", tempMap.get("REG_DATE"));
								excelMap.put("DRF_NO", tempMap.get("DRF_NO"));

								dao.updateInCodeName(excelMap);
							} else {
								// code_install_upgrade ����
								if ("1".equals(excelMap.get("SEQ"))) {
									dao.updateNotInCodeName(excelMap);
									// code_install_upgrade ���� update
									// (updateNotInCodeName �޼ҵ带 �̿���)
								}

								excelMap.put("CODE_INSTALL_DBID", dataMap.get("DBID"));
								excelMap.put("DRF_NO", tempMap.get("DRF_NO"));
								excelMap.put("REG_DATE", tempMap.get("REG_DATE"));
								excelMap.put("NUCLEARTYPE", tempMap.get("NUCLEARTYPE"));
								excelMap.put("INPUT_USER_ID", INPUT_USER_ID);

								dao.insertCodeInstall(excelMap);
							}
							excelMap.put("UPDATE_FLAG", "Y");
							excelMap.put("MAPPING_FLAG", "Y");
							excelMap.put("SIZE", size);
							dao.insertMappingData(excelMap);
						} else {
							// codeInfo ���̺� codeName�� �������
							dao.updateNotInCodeName(excelMap);

							excelMap.put("UPDATE_FLAG", "Y");
							excelMap.put("MAPPING_FLAG", "N");
							excelMap.put("SIZE", size);
							dao.insertMappingData(excelMap);
						}
					} else {
						excelMap.put("UPDATE_FLAG", "N");
						excelMap.put("MAPPING_FLAG", "N");
						dao.insertMappingData(excelMap);
					}
				}
			}
			sqlMapClient.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			sqlMapClient.endTransaction();
		}
	}
}
