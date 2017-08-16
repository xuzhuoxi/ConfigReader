package cfg.serialize.cfgdata;

import cfg.serialize.FieldRangeType;
import cfg.serialize.FieldKey;
import cfg.source.data.SheetInfo;

public interface ISheetHandler {
	/**
	 * 配置序列化信息
	 * 
	 * @param sheetInfo
	 */
	void config(SheetInfo sheetInfo);

	/**
	 * 默认序列化过程，包括了start,append,finish过程，并返回了结果数据
	 * 
	 * @param exportType
	 *            导出类型
	 * @param attrKeyType
	 *            导出语言
	 * @return
	 */
	Object serialize(FieldRangeType exportType, FieldKey attrKeyType);

	/**
	 * 开始
	 */
	void start();

	/**
	 * 追加一条数据
	 * 
	 * @param item
	 */
	void append(Object item);

	/**
	 * 追加多条数据
	 * 
	 * @param items
	 */
	void append(Object[] items);

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
