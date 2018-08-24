package cfg.serialize;

/**
 * 字段的键类型
 * 
 * @author xuzhuoxi
 */
public enum FieldKey {
	Sql("sql"), Json("json"), Java("java"), TypeScript("ts"), CAddAdd("c++"), CSharp("c#"), AS3("as3"), Go("go");

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
