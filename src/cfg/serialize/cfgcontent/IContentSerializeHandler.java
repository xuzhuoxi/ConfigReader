package cfg.serialize.cfgcontent;

import cfg.serialize.AttributeDataType;

public interface IContentSerializeHandler {
	/**
	 * 把字符串内容转为对应的Java对象
	 * 
	 * @param attrDataType
	 * @param valueContent
	 * @return
	 */
	Object fromString(String valueContent, AttributeDataType attrDataType);

	/**
	 * 把字符串数据转为Json数据
	 * 
	 * @param valueContent
	 * @param dataType
	 * @return
	 */
	String toJson(String valueContent);

	/**
	 * 把Java对象转为Json数据
	 * 
	 * @param obj
	 * @param attrDataType
	 *            内部包含长度信息
	 * @return
	 */
	String toJson(Object obj, AttributeDataType attrDataType);

	/**
	 * 把Java对象转为二进制数据
	 * 
	 * @param obj
	 * @param attrDataType
	 *            内部包含长度信息
	 * @return
	 */
	byte[] toBinary(Object obj, AttributeDataType attrDataType);
}
