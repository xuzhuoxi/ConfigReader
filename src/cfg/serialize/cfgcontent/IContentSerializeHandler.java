package cfg.serialize.cfgcontent;

import cfg.serialize.FieldDataFormat;

public interface IContentSerializeHandler {
	/**
	 * 把字符串内容转为对应的Java对象
	 * 
	 * @param valueContent
	 *            字符串数据
	 * @param attrDataType
	 *            数据类型,内部包含长度信息
	 * @return 各个语言对应的数据结构
	 */
	Object fromString(String valueContent, FieldDataFormat attrDataType);

	/**
	 * 把字符串数据转为Json数据
	 * 
	 * @param valueContent
	 *            字符串数据
	 * @return Json字符串
	 */
	String toJson(String valueContent);

	/**
	 * 把Java对象转为Json数据
	 * 
	 * @param obj
	 *            Java数据对象
	 * @param attrDataType
	 *            数据类型,内部包含长度信息
	 * @return Json字符串
	 */
	String toJson(Object obj, FieldDataFormat attrDataType);

	/**
	 * 把Java对象转为二进制数据
	 * 
	 * @param obj
	 *            Java数据对象
	 * @param attrDataType
	 *            数据类型,内部包含长度信息
	 * @return 字节数组
	 */
	byte[] toBinary(Object obj, FieldDataFormat attrDataType);
}
