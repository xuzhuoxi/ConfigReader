package cfg.serialize;

public class DataToken {
	private AttributeDataType dataType;
	private String key;
	private String content;

	public AttributeDataType getDataType() {
		return dataType;
	}

	public String getKey() {
		return key;
	}

	public String getContent() {
		return content;
	}

	public void update(AttributeDataType dataType, String key, String content) {
		this.dataType = dataType;
		this.key = key;
		this.content = content;
	}

	public void update(AttributeDataType dataType, String content) {
		this.dataType = dataType;
		this.key = null;
		this.content = content;
	}
}
