package cfg.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import code.file.FileUtils;

public class WorkbookUtil {
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";

	/**
	 * 取得Workbook对象(xls,xlsx)
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 一个Excel文件对应的数据对象 (Workbook)
	 */
	public static Workbook getWorkbook(String filePath) {
		if (FileUtils.isExists(filePath) && !FileUtils.isFolder(filePath)) {
			return loadWorkbook(filePath);
		} else {
			return null;
		}
	}

	/**
	 * 取得Workbook对象(xls,xlsx)
	 * 
	 * @param file
	 *            文件
	 * @return 一个Excel文件对应的数据对象 (Workbook)
	 */
	public static Workbook getWorkbook(File file) {
		if (file.isFile()) {
			return loadWorkbook(file.getAbsolutePath());
		} else {
			return null;
		}
	}

	/**
	 * 读取一行数据
	 * 
	 * @param sheet
	 *            Excel中的一个Sheet表
	 * @param rowNum
	 *            行索引
	 * @param len
	 *            长度
	 * @return 一行数据的文本数组
	 */
	public static String[] getContentArray(Sheet sheet, int rowNum, int len) {
		Row row = sheet.getRow(rowNum);
		String[] contentArray = new String[len];
		int rowLen = row.getLastCellNum();
		// System.out.println(rowNum + ": " + rowLen + ": " + len);
		Cell cell;
		for (int index = 0; index < len; index++) {
			if (index < rowLen) {
				cell = row.getCell(index);
				// System.out.println(index + "\t" + (null != cell));
				// System.out.println("\t\t" + CellUtil.toStringValue(cell));
				contentArray[index] = CellUtil.toStringValue(cell);
			} else {
				contentArray[index] = "";
			}
		}
		System.out.println("WorkbookUtil.getContentArray(" + rowNum + "):" + Arrays.toString(contentArray));
		return contentArray;
	}

	/**
	 * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类) <br>
	 * xls:HSSFWorkbook<br>
	 * xlsx：XSSFWorkbook<br>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 一个Excel文件对应的数据对象 (Workbook)
	 */
	private static Workbook loadWorkbook(String filePath) {
		Workbook workbook = null;
		InputStream is;
		try {
			is = new FileInputStream(filePath);
			if (filePath.endsWith(EXTENSION_XLS)) {
				workbook = new HSSFWorkbook(is);
			} else if (filePath.endsWith(EXTENSION_XLSX)) {
				workbook = new XSSFWorkbook(is);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workbook;
	}
}
