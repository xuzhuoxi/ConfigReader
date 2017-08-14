package cfg.serialize.generater;

import cfg.serialize.ClassLangType;
import cfg.serialize.FieldRangType;
import cfg.source.data.SheetDefine;

public interface IContentGenerater {

	/**
	 * 设置模块文件路径
	 * 
	 * @param tempUrl
	 */
	public void setTempPath(String tempUrl);

	/**
	 * 设置要生成的编程语言
	 * 
	 * @param lang
	 */
	public void setLang(ClassLangType lang);

	/**
	 * 设置选择的字段范围
	 * 
	 * @param fieldRange
	 */
	public void setFieldRang(FieldRangType fieldRange);

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
