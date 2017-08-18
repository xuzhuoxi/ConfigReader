package cfg.serialize.generater;

import cfg.serialize.ClassLangType;
import cfg.serialize.FieldRangeType;
import cfg.source.data.SheetDefine;

public interface IContentGenerater {

	/**
	 * 取模块键
	 * 
	 * @return
	 */
	public String getTempKey();

	/**
	 * 设置
	 * 
	 * @param lang
	 *            设置编程语言
	 * @param fieldRange
	 *            设置选择的字段范围
	 * @param tempKey
	 *            设置模块键
	 */
	public void setInfo(ClassLangType lang, FieldRangeType fieldRange);

	/**
	 * 设置子生成器
	 * 
	 * @param contentKey
	 * @param subGenerater
	 */
	public void setSubGenerater(String contentKey, IContentGenerater subGenerater);

	/**
	 * 把一个Sheet序列化成实体类定义
	 * 
	 * @param sheetInfo
	 * @return
	 */
	public String serialize(SheetDefine sheetDefine);
}
