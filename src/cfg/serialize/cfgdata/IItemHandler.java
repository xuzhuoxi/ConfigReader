package cfg.serialize.cfgdata;

import cfg.serialize.AttributeDataType;

public interface IItemHandler {
	/**
	 * 开始
	 */
	void start();

	/**
	 * 追加单个Cell数据
	 * 
	 * @param attrDataType
	 * @param attrKey
	 * @param valueContent
	 */
	void append(AttributeDataType attrDataType, String attrKey, String valueContent);

	/**
	 * 追加单个Cell数据
	 * 
	 * @param attrDataType
	 * @param attrKey
	 * @param valueObject
	 */
	void append(AttributeDataType attrDataType, String attrKey, Object valueObject);

	/**
	 * 追加单个Cell数据
	 * 
	 * @param attrDataType
	 * @param attrKey
	 * @param valueContents
	 */
	void append(AttributeDataType attrDataType, String attrKey, String[] valueContents);

	/**
	 * 追加单个Cell数据
	 * 
	 * @param attrDataType
	 * @param attrKey
	 * @param valueObjects
	 */
	void append(AttributeDataType attrDataType, String attrKey, Object[] valueObjects);

	/**
	 * 追加整条数据
	 * 
	 * @param indexs
	 * @param attrDataTypes
	 * @param attrKeys
	 * @param allContents
	 */
	void append(Integer[] indexs, AttributeDataType[] attrDataTypes, String[] attrKeys, String[] allContents);

	/**
	 * 结束
	 */
	void finish();

	/**
	 * 取序列化后的数据
	 * 
	 * @return
	 */
	Object getSerializedData();
}
