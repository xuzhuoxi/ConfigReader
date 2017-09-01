package cfg.source.data;

import java.util.Arrays;

/**
 * 导出的字段范围类型有效字段索引数据
 * 
 * @author xuzhuoxi
 *
 */
public class SheetValidInfo {
	// 导出类类名
	private String className;
	// 数据文件名
	private String fileName;
	// 有效字段索引
	private Integer[] validIndexs;

	/**
	 * 导出类类名
	 * 
	 * @return 导出类类名
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * 数据文件名
	 * 
	 * @return 数据文件名
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 有效字段索引
	 * 
	 * @return 有效字段索引数组
	 */
	public Integer[] getValidIndexs() {
		return Arrays.copyOf(validIndexs, validIndexs.length);
	}

	/**
	 * 保存数据
	 * 
	 * @param className
	 *            导出类类名
	 * @param fileName
	 *            数据文件名
	 * @param validIndexs
	 *            有效字段索引
	 */
	public void setInfo(String className, String fileName, Integer[] validIndexs) {
		this.className = className;
		this.fileName = fileName;
		this.validIndexs = validIndexs;
	}
}
