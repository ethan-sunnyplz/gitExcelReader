package fileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReaderHelper {

	public List getCellValue(HSSFWorkbook workbook, int sheetNo) {

		HSSFSheet sheet = workbook.getSheetAt(sheetNo); // ��Ʈ��ȣ
		int rows = sheet.getPhysicalNumberOfRows(); // ��ü rows ��������

		String val = "";
		String key = "";
		List list = new ArrayList();

		for (int j = 1; j < rows; j++) { // ù��° �� ����

			HSSFRow row = sheet.getRow(j); // row ������ ��������

			if (row != null) {
				Map map = new HashMap();
				int count = 0;
				int cells = row.getPhysicalNumberOfCells(); // �� ���� ��������

				for (int c = 0; c < cells; c++) {
					HSSFCell cell = row.getCell(c);
					if (c == 0) {
						key = "KNF_CODE";
					} else if (c == 1) {
						key = "CODE_NAME";
					} else if (c == 2) {
						key = "VERSION";
					}
					if (c == 3 && sheetNo == 0) {
						key = "DRF_NO";
					} else if (c == 3 && sheetNo == 1) {
						key = "SEQ";
					}

					if (c == 4 && sheetNo == 1) {
						key = "HARDWARE";
					} else if (c == 5 && sheetNo == 1) {
						key = "OS";
					} else if (c == 6 && sheetNo == 1) {
						key = "PL";
					} else if (c == 7 && sheetNo == 1) {
						key = "PATH";
					}

					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							val = cell.getCellFormula();
							map.put(key, val);
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							val = cell.getNumericCellValue() + "";
							map.put(key, val);
							break;
						case HSSFCell.CELL_TYPE_STRING:
							val = cell.getStringCellValue(); // String
							map.put(key, val);
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							val = "";
							map.put(key, val);
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							val = cell.getErrorCellValue() + "ERROR"; // byte
							map.put(key, val);
							break;
						default:
							val = "���û��� ����";
							map.put(key, val);
						}
					}
				}
				list.add(map);
			}
		}
		return list;
	}
}
