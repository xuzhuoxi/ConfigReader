package cfg.serialize.exceptions;

import code.math.NumberSystemUtil;

@SuppressWarnings("serial")
public class SheetDataException extends Exception {

	// 列
	private int col;
	// 行
	private int row;

	/**
	 * 记录列索引
	 * 
	 * @param col
	 *            索引号
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * 记录行索引
	 * 
	 * @param row
	 *            索引号
	 */
	public void setRow(int row) {
		this.row = row;
	}

	public String getLoc() {
		return "[行索引:" + row + ", 行号:" + (row + 1) + ", 列索引:" + col + ", 列号:"
				+ NumberSystemUtil.toNumberSystem26(col + 1) + "]";
	}

	public SheetDataException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SheetDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SheetDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SheetDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SheetDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
