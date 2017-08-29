package cfg.serialize;

/**
 * 输出文件格式
 * 
 * @author xuzhuoxi
 */
public enum OutputDataFormat {
	Json("json"), Binary("binary"), Sql("sql");

	private String value;

	public String getValue() {
		return value;
	}

	public boolean outputTextFile() {
		return this != OutputDataFormat.Binary;
	}

	private OutputDataFormat(String value) {
		this.value = value;
	}

	public static OutputDataFormat from(String value) {
		for (OutputDataFormat type : OutputDataFormat.values()) {
			if (type.value.equals(value)) {
				return type;
			}
		}
		return null;
	}
}
