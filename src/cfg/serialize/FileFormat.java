package cfg.serialize;

public enum FileFormat {

	Json("json"), Binary("binary"), Sql("sql");

	private String value;

	public String getValue() {
		return value;
	}

	private FileFormat(String value) {
		this.value = value;
	}

	public static FileFormat from(String value) {
		for (FileFormat type : FileFormat.values()) {
			if (type.value.equals(value)) {
				return type;
			}
		}
		return null;
	}
}
