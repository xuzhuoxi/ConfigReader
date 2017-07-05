package cfg.serialize.generater;

import cfg.serialize.ExportProjectType;
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
	public void setLang(String lang);

	/**
	 * 设置子生成器
	 * 
	 * @param contentKey
	 * @param subGenerater
	 */
	public void setSubGenerater(String contentKey, IContentGenerater subGenerater);

	/**
	 * 序列化类
	 * 
	 * @param sheetInfo
	 * @return
	 */
	public String serialize(SheetDefine sheetDefine, ExportProjectType projectType);
}
