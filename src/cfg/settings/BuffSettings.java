package cfg.settings;

public class BuffSettings {
	private int tokenBuffLen;
	private int itemBuffLen;
	private int sheetBuffLen;

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
		return "BuffSettings [tokenBuffLen=" + tokenBuffLen + ", itemBuffLen=" + itemBuffLen + ", sheetBuffLen="
				+ sheetBuffLen + "]";
	}

	protected BuffSettings() {
		super();
	}

	private void setData(int tokenBuffLen, int itemBuffLen, int sheetBuffLen) {
		this.tokenBuffLen = tokenBuffLen;
		this.itemBuffLen = itemBuffLen;
		this.sheetBuffLen = sheetBuffLen;
	}

	public static BuffSettings create(int tokenBuffLen, int itemBuffLen, int sheetBuffLen) {
		BuffSettings rs = new BuffSettings();
		rs.setData(tokenBuffLen, itemBuffLen, sheetBuffLen);
		return rs;
	}
}
