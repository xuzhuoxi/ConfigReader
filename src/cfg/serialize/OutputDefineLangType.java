package cfg.serialize;

/**
 * 编程语言支持
 * 
 * @author xuzhuoxi
 */
public enum OutputDefineLangType {
	Java("java"), TypeScript("ts"), CAddAdd("c++"), CSharp("c#"), AS3("as3"), Go("go");

	private String value;

	public String getValue() {
		return value;
	}

	public String getExtensionName() {
		switch (this) {
		case Java:
			return "java";
		case TypeScript:
			return "ts";
		case CAddAdd:
			return "cpp";
		case CSharp:
			return "cs";
		case AS3:
			return "as";
		case Go:
			return "go";
		default:
			return "";
		}
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
