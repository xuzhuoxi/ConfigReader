package cfg.serialize;

/**
 * 导出的字段范围类型
 * 
 * @author xuzhuoxi
 */
public enum FieldRangeType {
	Client("client"), Server("server"), DB("db");

	private String value;

	public String getValue() {
		return value;
	}

	private FieldRangeType(String value) {
		this.value = value;
	}

	public static FieldRangeType from(String value) {
		for (FieldRangeType type : FieldRangeType.values()) {
			if (type.value.equals(value)) {
				return type;
			}
		}
		return null;
	}
}
