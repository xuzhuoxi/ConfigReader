package cfg.serialize;

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
	 * @param fieldRange
	 * @param lang
	 * @param outputFolder
	 */
	public static void serializeDefine(SheetInfo sheetInfo, FieldRangeType fieldRange, OutputDefineLangType lang,
			String outputFolder, String extNamed) {
		IContentGenerater cg = LangFileGenerater.getModuleGenerater(lang, fieldRange);
		String value = cg.serialize(sheetInfo.getDefine());
		String outputFilePath = outputFolder + "/" + sheetInfo.getDefine().getExportInfo(fieldRange).getClassName()
				+ "." + extNamed;
		FileUtils.writeTextFile(outputFilePath, value, Settings.getInstance().getSysSettings().getTargetEncoding());
	}
}
