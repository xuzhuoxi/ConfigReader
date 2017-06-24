package cfg.source.data;

import java.util.Arrays;

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
	 * @return
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * 数据文件名
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 有效字段索引
	 * 
	 * @return
	 */
	public Integer[] getValidIndexs() {
		return Arrays.copyOf(validIndexs, validIndexs.length);
	}

	/**
	 * 保存数据
	 * 
	 * @param className
	 * @param fileName
	 * @param validIndexs
	 */
	public void setInfo(String className, String fileName, Integer[] validIndexs) {
		this.className = className;
		this.fileName = fileName;
		this.validIndexs = validIndexs;
	}
}
