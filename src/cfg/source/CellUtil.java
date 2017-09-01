package cfg.source;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class CellUtil {
	/**
	 * 获取Excel中某个单元格的值(字符串形式)
	 * 
	 * @param cell
	 *            EXCLE单元格对象
	 * @return 单元格内容
	 */
	public static String toStringValue(Cell cell) {
		if (null == cell) {
			return "";
		}
		String value = "";
		@SuppressWarnings("deprecation")
		CellType cellType = CellType.forInt(cell.getCellType());
		switch (cellType) {
		case NUMERIC: // 数值型
			if (HSSFDateUtil.isCellDateFormatted(cell)) { // 如果是时间类型
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				value = format.format(cell.getDateCellValue());
			} else { // 纯数字
				value = String.valueOf(cell.getNumericCellValue());
			}
			break;
		case STRING: // 字符串型
			value = cell.getStringCellValue();
			break;
		case BOOLEAN: // 布尔
			value = " " + cell.getBooleanCellValue();
			break;
		case FORMULA: // 公式型
			try {
				/*
				 * 此处判断使用公式生成的字符串有问题，因为HSSFDateUtil.isCellDateFormatted(cell)
				 * 判断过程中cell
				 * .getNumericCellValue();方法会抛出java.lang.NumberFormatException异常
				 */
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					value = format.format(date);
					break;
				} else {
					value = String.valueOf(cell.getNumericCellValue());
				}
			} catch (IllegalStateException e) {
				value = String.valueOf(cell.getRichStringCellValue());
			}
			break;
		case BLANK: // 空值
		case ERROR: // 故障
			value = "";
			break;
		default:
			value = cell.getStringCellValue().toString();
			break;
		}
		return value;
	}
}
