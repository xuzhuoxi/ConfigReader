package cfg.serialize;

import java.util.List;

import cfg.serialize.cfgdefine.IContentGenerater;
import cfg.serialize.cfgdefine.LangFileGenerater;
import cfg.settings.Settings;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;

public class SerializeDefineUtil {
	/**
	 * 导出定义数据结构
	 * 
	 * @param sheetInfo
	 *            Excel中的一个sheet数据对象
	 * @param fieldRange
	 *            导出的字段范围类型
	 * @param lang
	 *            编码语言
	 * @param outputFolder
	 *            输出文件夹
	 * @param extNamed
	 *            输出文件的扩展名
	 */
	public static void serializeDefine(SheetInfo sheetInfo, FieldRangeType fieldRange, OutputDefineLangType lang,
			String outputFolder, String extNamed) {
		if (sheetInfo.getDefine().isFieldRangeEmpty(fieldRange)) {// 跳过无索引字段的表
			return;
		}
		IContentGenerater cg = LangFileGenerater.getModuleGenerater(lang, fieldRange);
		String value = cg.serialize(sheetInfo.getDefine());
		String outputFilePath = outputFolder + "/" + sheetInfo.getDefine().getExportInfo(fieldRange).getClassName()
				+ "." + extNamed;
		System.out.println(
				"\t输出定义文件(" + Settings.getInstance().getSysSettings().getTargetEncoding() + ")：" + outputFilePath);
		FileUtils.writeTextFile(outputFilePath, value, Settings.getInstance().getSysSettings().getTargetCharset());
	}

	/**
	 * 导出定义数据结构
	 * 
	 * @param sheetInfos
	 *            Excel中的一个sheet数据对象组成的数组
	 * @param fieldRange
	 *            导出的字段范围类型
	 * @param lang
	 *            编码语言
	 * @param outputFolder
	 *            输出文件夹
	 * @param extNamed
	 *            输出文件的扩展名
	 */
	public static void serializeDefine(List<SheetInfo> sheetInfos, FieldRangeType fieldRange, OutputDefineLangType lang,
			String outputFolder, String extNamed) {
		for (SheetInfo sheetInfo : sheetInfos) {
			System.out.println("Start Serialize Define: [" + sheetInfo.getSheetNamed() + "," + lang + "]");
			serializeDefine(sheetInfo, fieldRange, lang, outputFolder, extNamed);
		}
	}
}
