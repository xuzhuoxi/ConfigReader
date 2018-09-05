package cfg.settings.sys;

import java.nio.ByteOrder;

public class BuffSetting {
	private boolean _bigEndian;
	private int tokenBuffLen;
	private int itemBuffLen;
	private int sheetBuffLen;

	/**
	 * 这里忽略了配置数据，直接返回true<br>
	 * 原因BigInteger中使用了toByteArray方法，jdk中的这方法是采用BigEndian的
	 * 
	 * @return true
	 */
	public boolean isBigEndian() {
		return _bigEndian;
	}

	public ByteOrder byteOrder() {
		return isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
	}

	public int getTokenBuffLen() {
		return tokenBuffLen;
	}

	public int getItemBuffLen() {
		return itemBuffLen;
	}

	public int getSheetBuffLen() {
		return sheetBuffLen;
	}

	@Override
	public String toString() {
		return "BuffSetting [_bigEndian=" + _bigEndian + ", tokenBuffLen=" + tokenBuffLen + ", itemBuffLen="
				+ itemBuffLen + ", sheetBuffLen=" + sheetBuffLen + "]";
	}

	protected BuffSetting() {
		super();
	}

	private void setData(boolean isBigEndian, int tokenBuffLen, int itemBuffLen, int sheetBuffLen) {
		this._bigEndian = isBigEndian;
		this.tokenBuffLen = tokenBuffLen;
		this.itemBuffLen = itemBuffLen;
		this.sheetBuffLen = sheetBuffLen;
	}

	public static BuffSetting create(boolean isBigEndian, int tokenBuffLen, int itemBuffLen, int sheetBuffLen) {
		BuffSetting rs = new BuffSetting();
		rs.setData(isBigEndian, tokenBuffLen, itemBuffLen, sheetBuffLen);
		return rs;
	}
}
