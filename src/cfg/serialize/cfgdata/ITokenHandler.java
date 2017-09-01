package cfg.serialize.cfgdata;

import cfg.serialize.FieldDataFormat;

public interface ITokenHandler {

	/**
	 * 把文本序列化为数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据格式
	 * @param fieldNamed
	 *            字段名称
	 * @param valueContent
	 *            字段文本数据
	 * @return 序列化结果数据
	 */
	Object serializeContentToken(FieldDataFormat fieldDataFormat, String fieldNamed, String valueContent);

	/**
	 * 
	 * 把文本数组序列化为数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据格式
	 * @param fieldNamed
	 *            字段名称
	 * @param valueContents
	 *            字段文本数据数组
	 * @return 序列化结果数据
	 */
	Object serializeContentToken(FieldDataFormat fieldDataFormat, String fieldNamed, String[] valueContents);

	/**
	 * 把Java对象序列化为数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据格式
	 * @param fieldNamed
	 *            字段名称
	 * @param valueObject
	 *            字段对象数据
	 * @return 序列化结果数据
	 */
	Object serializeObjectToken(FieldDataFormat fieldDataFormat, String fieldNamed, Object valueObject);

	/**
	 * 把Java对象数组序列化为数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据格式
	 * @param fieldNamed
	 *            字段名称
	 * @param valueObjects
	 *            字段对象数据数组
	 * @return 序列化结果数据
	 */
	Object serializeObjectToken(FieldDataFormat fieldDataFormat, String fieldNamed, Object[] valueObjects);

}
