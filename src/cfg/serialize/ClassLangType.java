package cfg.serialize;

/**
 * 编程语言支持
 * @author Administrator
 *
 */
public enum ClassLangType {
	Java("java"), TypeScript("ts"), CAddAdd("c++"), CSharp("c#");

	private String value;

	public String getValue() {
		return value;
	}

	private ClassLangType(String value) {
		this.value = value;
	}

	public static ClassLangType from(String value) {
		for (ClassLangType lang : ClassLangType.values()) {
			if (lang.value.equals(value)) {
				return lang;
			}
		}
		return null;
	}
}
