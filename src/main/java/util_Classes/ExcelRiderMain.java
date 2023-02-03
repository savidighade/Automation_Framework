package util_Classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelRiderMain {

	private static DataFormatter dataFormatter = new DataFormatter();

	private static Logger logger = Logger.getLogger(ExcelRiderMain.class.getName());

	public static final String excelPath = "C:\\Users\\gaura\\Downloads\\framework_code\\ExcelReader-master\\ExcelReader-master\\src\\test\\"
			+ "resources\\data\\ReadExcelData.xlsx";

	public static final String sheetName = "Sheet1";

	public ExcelRiderMain(Class<? extends TestDataProvider> class1) {
		// TODO Auto-generated constructor stub
	}

	public Object[][] readData() {

		List<Object[]> excelData = new ArrayList<Object[]>();

		Map<String, String> rowData = null;

		try

		{
			FileInputStream fis = new FileInputStream(excelPath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {

				throw new IllegalArgumentException("Sheet not found: " + sheetName);

			}

			Map<Integer, String> columnHeaders = getColumnHeaders(sheet);

			int numRows = sheet.getLastRowNum();

			for (int i = 1; i <= numRows; i++) {

				rowData = getRowData(sheet, i, columnHeaders);

				excelData.add(new Object[] { rowData });

			}

			// rowData.forEach((k,v) -> System.out.println("key: "+k+" value:"+v));

			for (int i = 0; i < excelData.size(); i++)

			{

				System.out.println("the " + excelData.get(i));

			}

		} catch (IOException e) {

			logger.warn(e.getMessage());

		}

		return excelData.toArray(new Object[0][]);

	}

	private static Map<Integer, String> getColumnHeaders(XSSFSheet sheet) {

		XSSFRow headerRow = sheet.getRow(0);

		if (headerRow == null) {

			return new HashMap<Integer, String>();

		}

		int numCells = headerRow.getLastCellNum();

		Map<Integer, String> columnHeaders = new HashMap<Integer, String>();

		for (int i = 0; i < numCells; i++) {

			String header = dataFormatter.formatCellValue(headerRow.getCell(i));

			columnHeaders.put(i, header);

		}

		return columnHeaders;

	}

	private static Map<String, String> getRowData(XSSFSheet sheet, int rowIndex, Map<Integer, String> columnHeaders) {

		XSSFRow row = sheet.getRow(rowIndex);

		if (row == null) {

			return new HashMap<String, String>();

		}

		Map<String, String> rowData = new HashMap<String, String>();

		int numCells = row.getLastCellNum();

		for (int i = 0; i < numCells; i++) {

			String header = columnHeaders.get(i);

			String value = dataFormatter.formatCellValue(row.getCell(i));

			rowData.put(header, value);

		}

		return rowData;

	}

	// @DataProvider(name = "ExcelTestdata")

	@Test

	public void m1()

	{

		System.out.println("Excel path is " + excelPath);

		System.out.println("Sheet Name is " + sheetName);

		try {

			readData();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
