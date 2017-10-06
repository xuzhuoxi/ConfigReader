package cfg.serialize;

/**
 * 数据文件的输出格式
 * 
 * @author xuzhuoxi
 */
public enum OutputDataFormat {
	Json("json"), Binary("binary"), Sql("sql");

	private String value;

	/**
	 * 枚举的字符串值
	 * 
	 * @return 枚举的字符表示
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 输出是否为字符文件
	 * 
	 * @return true:是字符文件，false:二进制文件
	 */
	public boolean isTextFile() {
		return this != OutputDataFormat.Binary;
	}

	/**
	 * 数据文件使用的字段的键类型
	 * 
	 * @return 字段的键类型
	 * @see FieldKey
	 */
	public FieldKey getFieldKey() {
		switch (this) {
		case Json:
			return FieldKey.Json;
		case Sql:
			return FieldKey.Sql;
		default:
			return null;
		}
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
