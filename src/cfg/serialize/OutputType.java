package cfg.serialize;

/**
 * 输出类型
 * 
 * @author xuzhuoxi
 */
public enum OutputType {
	Data("data"), Define("define");

	private String value;

	public String getValue() {
		return value;
	}

	private OutputType(String value) {
		this.value = value;
	}

	public static OutputType from(String value) {
		for (OutputType lang : OutputType.values()) {
			if (lang.value.equals(value)) {
				return lang;
			}
		}
		return null;
	}
}
