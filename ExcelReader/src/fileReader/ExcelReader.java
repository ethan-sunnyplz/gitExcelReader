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
			String path = "C:/Users/Administrator/Desktop/코드연계정보 - 적용.xls"; // 파일
			// TODO 테이블 명 변경해야 함
			final String[] tabSort = { "KNFC_DEV", "CODE_INSTALL_UPGRADE", "CODE_ERROR", "CODE_LOAN" }; // 구분할
																										// 대상
																										// 테이블

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
					} else { // 코드 대출의 경우에는 컬럼명이 틀림
						excelMap.put("cd_name", STR2);
						excelMap.put("cd_ver", STR3);
					}

					dataList = dao.readIdData(excelMap); // KNF Code로 Doc 존재 여부
															// 확인

					if (dataList.size() > 0) {// dataList는 사이즈가 1임

						// 코드 마스터에서 Code Name에 해당하는 코드 목록을 가져온다.
						Map dataMap = (Map) dataList.get(0);
						List codeInfoList = dao.readCodeInfo(excelMap);// System.out.println("해당
																		// 테이블에
																		// 엑셀ID가
																		// 존재함");

						int size = codeInfoList.size();

						if (codeInfoList.size() > 0) {
							// codeInfo 테이블에 codeName이 존재함
							// TODO 정해지면 아래 소스 변경해야 함
							Map tempMap = (Map) codeInfoList.get(0); // codeInfoList
																		// 사이즈는
																		// 1임

							if (s != 1) {
								excelMap.put("REG_DATE", tempMap.get("REG_DATE"));
								excelMap.put("DRF_NO", tempMap.get("DRF_NO"));

								dao.updateInCodeName(excelMap);
							} else {
								// code_install_upgrade 로직
								if ("1".equals(excelMap.get("SEQ"))) {
									dao.updateNotInCodeName(excelMap);
									// code_install_upgrade 먼저 update
									// (updateNotInCodeName 메소드를 이용함)
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
							// codeInfo 테이블에 codeName이 존재안함
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
