package cfg.source.data;

import cfg.serialize.FieldDataFormat;

public class SheetProperty {
	private String property;
	private String upProperty;
	private String jsonProperty;
	private String propertyName;
	private String propertyRemark;
	private String propertyDataType;
	private FieldDataFormat dataFormat;

	/**
	 * 变量名的字符串表示
	 * 
	 * @return 变量名
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * 变量名的字符串表示
	 * 
	 * @return 变量名(第一字母大写)
	 */
	public String getUpProperty() {
		return upProperty;
	}

	/**
	 * 变量名的字符串表示
	 * 
	 * @return 变量名(Json变量)
	 */
	public String getJsonProperty() {
		return jsonProperty;
	}

	/**
	 * 字段名称
	 * 
	 * @return 字段名称
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 字段描述或备注
	 * 
	 * @return 字段描述或备注
	 */
	public String getPropertyRemark() {
		return propertyRemark;
	}

	/**
	 * 字段对应的语言数据格式
	 * 
	 * @return 字段对应的语言数据格式
	 */
	public String getPropertyDataType() {
		return propertyDataType;
	}

	/**
	 * 字段对应数据格式描述
	 * 
	 * @return 字段对应数据格式描述
	 */
	public FieldDataFormat getDataFormat() {
		return dataFormat;
	}

	@Override
	public String toString() {
		return "SheetProperty [property=" + property + ", upProperty=" + upProperty + ", jsonProperty=" + jsonProperty
				+ ", propertyName=" + propertyName + ", propertyRemark=" + propertyRemark + ", propertyDataType="
				+ propertyDataType + ", dataFormat=" + dataFormat + "]";
	}

	public SheetProperty(String property, String jsonProperty, String propertyName, String propertyRemark,
			String propertyDataType, FieldDataFormat dataFormat) {
		this.property = property;
		this.upProperty = property.substring(0, 1).toUpperCase() + property.substring(1);
		this.jsonProperty = jsonProperty;
		this.propertyName = propertyName;
		this.propertyRemark = propertyRemark;
		this.propertyDataType = propertyDataType;
		this.dataFormat = dataFormat;
	}

}
