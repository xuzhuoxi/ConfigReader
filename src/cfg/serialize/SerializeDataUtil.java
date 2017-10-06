package cfg.serialize;

import java.util.List;

import cfg.serialize.cfgdata.ISheetHandler;
import cfg.serialize.cfgdata.SheetHandlerFactory;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;

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
			FileUtils.writeTextFile(outputFilePath, (String) out);
		} else {
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
