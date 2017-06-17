package cfg.serialize;

public enum AttributeKeyType {
	Sql("sql"), Java("java"), TypeScript("ts"), CAddAdd("c++"), Json("json"), CSharp("c#");

	private String value;

	public String getValue() {
		return value;
	}

	private AttributeKeyType(String value) {
		this.value = value;
	}

	public static AttributeKeyType from(String value) {
		for (AttributeKeyType lang : AttributeKeyType.values()) {
			if (lang.value.equals(value)) {
				return lang;
			}
		}
		return null;
	}
}
