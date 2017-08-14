package cfg.serialize;

/**
 * 字段的键类型
 * @author Administrator
 */
public enum FieldKey {
	Sql("sql"), Java("java"), TypeScript("ts"), CAddAdd("c++"), Json("json"), CSharp("c#");

	private String value;

	public String getValue() {
		return value;
	}

	private FieldKey(String value) {
		this.value = value;
	}

	public static FieldKey from(String value) {
		for (FieldKey lang : FieldKey.values()) {
			if (lang.value.equals(value)) {
				return lang;
			}
		}
		return null;
	}
}
