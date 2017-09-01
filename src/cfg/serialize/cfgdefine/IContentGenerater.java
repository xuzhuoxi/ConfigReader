package cfg.serialize.cfgdefine;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDefineLangType;
import cfg.source.data.SheetDefine;

public interface IContentGenerater {

	/**
	 * 取模块键
	 * 
	 * @return 模块键
	 */
	public String getTempKey();

	/**
	 * 设置
	 * 
	 * @param lang
	 *            设置编程语言
	 * @param fieldRange
	 *            设置选择的字段范围
	 */
	public void setInfo(OutputDefineLangType lang, FieldRangeType fieldRange);

	/**
	 * 设置子生成器
	 * 
	 * @param contentKey
	 *            子生成器的模块键值
	 * @param subGenerater
	 *            子生成器对象实例
	 */
	public void setSubGenerater(String contentKey, IContentGenerater subGenerater);

	/**
	 * 把一个Sheet序列化成实体类定义
	 * 
	 * @param sheetDefine
	 *            Excel中的一个sheet定义数据
	 * @return 生成模块定义文本
	 */
	public String serialize(SheetDefine sheetDefine);
}
