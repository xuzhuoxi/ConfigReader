package cfg.serialize;

public enum ExportDataType {

	Json("json"), Binary("binary"), Sql("sql");

	private String value;

	public String getValue() {
		return value;
	}

	private ExportDataType(String value) {
		this.value = value;
	}

	public static ExportDataType from(String value) {
		for (ExportDataType type : ExportDataType.values()) {
			if (type.value.equals(value)) {
				return type;
			}
		}
		return null;
	}
}
