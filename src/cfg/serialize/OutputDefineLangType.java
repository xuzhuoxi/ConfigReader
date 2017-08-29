package cfg.serialize;

/**
 * 编程语言支持
 * 
 * @author xuzhuoxi
 */
public enum OutputDefineLangType {
	Java("java"), TypeScript("ts"), CAddAdd("c++"), CSharp("c#");

	private String value;

	public String getValue() {
		return value;
	}

	private OutputDefineLangType(String value) {
		this.value = value;
	}

	public static OutputDefineLangType from(String value) {
		for (OutputDefineLangType lang : OutputDefineLangType.values()) {
			if (lang.value.equals(value)) {
				return lang;
			}
		}
		return null;
	}
}
