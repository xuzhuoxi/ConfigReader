package cfg.serialize;

public class DataToken {
	private FieldDataFormat dataType;
	private String key;
	private String content;

	public FieldDataFormat getDataType() {
		return dataType;
	}

	public String getKey() {
		return key;
	}

	public String getContent() {
		return content;
	}

	public void update(FieldDataFormat dataType, String key, String content) {
		this.dataType = dataType;
		this.key = key;
		this.content = content;
	}

	public void update(FieldDataFormat dataType, String content) {
		this.dataType = dataType;
		this.key = null;
		this.content = content;
	}
}
