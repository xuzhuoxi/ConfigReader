package cfg.serialize.cfgdefine;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDefineLangType;
import cfg.source.data.SheetDefine;

public interface IDefineGenerater {
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
	 * 把一个Sheet序列化成实体类定义
	 * 
	 * @param sheetDefine
	 *            Excel中的一个sheet定义数据
	 * @return 生成模块定义文本
	 */
	public String serialize(SheetDefine sheetDefine);
}
