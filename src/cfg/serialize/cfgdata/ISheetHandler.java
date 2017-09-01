package cfg.serialize.cfgdata;

import cfg.serialize.FieldKey;
import cfg.serialize.FieldRangeType;
import cfg.source.data.SheetInfo;

public interface ISheetHandler {
	/**
	 * 配置序列化信息
	 * 
	 * @param sheetInfo
	 *            Excel中的一个sheet数据对象
	 */
	void config(SheetInfo sheetInfo);

	/**
	 * 默认序列化过程，包括了start,append,finish过程，并返回了结果数据
	 * 
	 * @param fieldRange
	 *            导出的字段范围类型
	 * @param fieldKey
	 *            字段使用的键值类型
	 * @return 序列化结果数据
	 */
	Object serialize(FieldRangeType fieldRange, FieldKey fieldKey);

	/**
	 * 开始
	 */
	void start();

	/**
	 * 追加一条数据
	 * 
	 * @param item
	 *            数据对象
	 */
	void append(Object item);

	/**
	 * 追加多条数据
	 * 
	 * @param items
	 *            数据对象数组
	 */
	void append(Object[] items);

	/**
	 * 结束
	 */
	void finish();

	/**
	 * 取序列化后的数据
	 * 
	 * @return 序列化后的数据
	 */
	Object getSerializedData();
}
