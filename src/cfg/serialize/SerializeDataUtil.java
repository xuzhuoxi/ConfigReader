package cfg.serialize;

import java.nio.charset.Charset;
import java.util.List;

import cfg.serialize.cfgdata.ISheetHandler;
import cfg.serialize.cfgdata.SheetHandlerFactory;
import cfg.settings.Settings;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;
import code.lang.StringUtil;

public class SerializeDataUtil {
	/**
	 * 导出数据
	 * 
	 * @param sheetInfo
	 *            Excel中的一个sheet数据对象
	 * @param fileFormat
	 *            输出文件格式
	 * @param fieldRange
	 *            导出的字段范围类型
	 * @param outputFolder
	 *            文件输出目录
	 * @param fieldKey
	 *            字段使用的键值类型
	 */
	public static void serializeData(SheetInfo sheetInfo, OutputDataFormat fileFormat, FieldRangeType fieldRange,
			String outputFolder, FieldKey fieldKey) {
		ISheetHandler handler = SheetHandlerFactory.getSheetHandler(fileFormat);
		handler.config(sheetInfo);
		Object out = handler.serialize(fieldRange, fieldKey);
		String outputFilePath = outputFolder + "/" + sheetInfo.getDefine().getExportInfo(fieldRange).getFileName() + "."
				+ fileFormat.getValue();
		if (fileFormat.isTextFile()) {
			System.out.println("输出数据文件(字符)(" + Settings.getInstance().getSysSettings().getTargetEncoding() + ")："
					+ outputFilePath);
			Charset outputCharset = Settings.getInstance().getSysSettings().getTargetCharset();
			String outJsonStr = (String) out;
			if (!Settings.getInstance().getSysSettings().isEncodingConsistent()) {
				outJsonStr = StringUtil.changeCharset(outJsonStr,
						Settings.getInstance().getSysSettings().getSourceCharset(), outputCharset);
			}
			FileUtils.writeTextFile(outputFilePath, outJsonStr, outputCharset);
			// FileUtils.writeTextFile(outputFilePath, (String) out,
			// outputCharset);
			// System.out.println(out);
		} else {
			System.out.println("输出数据文件(字节):" + outputFilePath);
			FileUtils.writeBinaryFile(outputFilePath, (byte[]) out);
		}
	}

	/**
	 * 批量导出数据
	 * 
	 * @param sheetInfos
	 *            Excel中的一个sheet数据对象组成的数组
	 * @param fileFormat
	 *            输出文件格式
	 * @param fieldRange
	 *            导出的字段范围类型
	 * @param outputFolder
	 *            文件输出目录
	 * @param fieldKey
	 *            字段使用的键值类型
	 */
	public static void serializeData(List<SheetInfo> sheetInfos, OutputDataFormat fileFormat, FieldRangeType fieldRange,
			String outputFolder, FieldKey fieldKey) {
		for (SheetInfo sheetInfo : sheetInfos) {
			serializeData(sheetInfo, fileFormat, fieldRange, outputFolder, fieldKey);
		}
	}
}
