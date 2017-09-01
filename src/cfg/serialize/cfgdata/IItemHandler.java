package cfg.serialize.cfgdata;

import cfg.serialize.FieldDataFormat;

public interface IItemHandler {
	/**
	 * 开始
	 */
	void start();

	/**
	 * 追加单个Cell数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据类型
	 * @param filedKey
	 *            字段的键类型
	 * @param valueContent
	 *            字段内容
	 */
	void append(FieldDataFormat fieldDataFormat, String filedKey, String valueContent);

	/**
	 * 追加单个Cell数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据类型
	 * @param filedKey
	 *            字段的键类型
	 * @param valueObject
	 *            字段对象
	 */
	void append(FieldDataFormat fieldDataFormat, String filedKey, Object valueObject);

	/**
	 * 追加单个Cell数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据类型
	 * @param filedKey
	 *            字段的键类型
	 * @param valueContents
	 *            字段内容组成的数组
	 */
	void append(FieldDataFormat fieldDataFormat, String filedKey, String[] valueContents);

	/**
	 * 追加单个Cell数据
	 * 
	 * @param fieldDataFormat
	 *            字段的数据类型
	 * @param filedKey
	 *            字段的键类型
	 * @param valueObjects
	 *            字段对象组成的数组
	 */
	void append(FieldDataFormat fieldDataFormat, String filedKey, Object[] valueObjects);

	/**
	 * 追加整条数据
	 * 
	 * @param indexs
	 *            字段的索引数组，由0开始
	 * @param fieldDataFormats
	 *            字段的数据类型数组
	 * @param filedKeys
	 *            字段的键类型数组
	 * @param valueContents
	 *            字段内容组成的数组
	 */
	void append(Integer[] indexs, FieldDataFormat[] fieldDataFormats, String[] filedKeys, String[] valueContents);

	/**
	 * 结束
	 */
	void finish();

	/**
	 * 取序列化后的数据
	 * 
	 * @return 数据化后的数据
	 */
	Object getSerializedData();
}
