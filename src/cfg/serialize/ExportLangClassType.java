package cfg.serialize;

public enum ExportLangClassType {
	Java("java"), TypeScript("ts"), CAddAdd("c++"), CSharp("c#");

	private String value;

	public String getValue() {
		return value;
	}

	private ExportLangClassType(String value) {
		this.value = value;
	}

	public static ExportLangClassType from(String value) {
		for (ExportLangClassType lang : ExportLangClassType.values()) {
			if (lang.value.equals(value)) {
				return lang;
			}
		}
		return null;
	}
}
