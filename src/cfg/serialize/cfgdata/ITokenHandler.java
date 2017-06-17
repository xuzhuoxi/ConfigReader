package cfg.serialize.cfgdata;

import cfg.serialize.AttributeDataType;

public interface ITokenHandler {

	/**
	 * 把文本序列化为数据
	 * @param attrDataType
	 * @param attrKey
	 * @param valueContent
	 * @return
	 */
	Object serializeContentToken(AttributeDataType attrDataType, String attrKey, String valueContent);
	
	/**
	 * 把文本数组序列化为数据
	 * @param attrDataType
	 * @param attrKey
	 * @param valueContent
	 * @return
	 */
	Object serializeContentToken(AttributeDataType attrDataType, String attrKey, String[] valueContents);
	
	/**
	 * 把Java对象序列化为数据
	 * @param attrDataType
	 * @param attrKey
	 * @param valueContent
	 * @return
	 */
	Object serializeObjectToken(AttributeDataType attrDataType, String attrKey, Object valueObject);
	
	/**
	 * 把Java对象数组序列化为数据
	 * @param attrDataType
	 * @param attrKey
	 * @param valueContent
	 * @return
	 */
	Object serializeObjectToken(AttributeDataType attrDataType, String attrKey, Object[] valueObjects);
	
}