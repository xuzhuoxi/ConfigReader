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
	 * @param fileFormat
	 * @param fieldRange
	 * @param outputFolder
	 * @param fieldKey
	 */
	public static void serializeData(SheetInfo sheetInfo, OutputDataFormat fileFormat, FieldRangeType fieldRange,
			String outputFolder, FieldKey fieldKey) {
		ISheetHandler handler = SheetHandlerFactory.getSheetHandler(fileFormat);
		handler.config(sheetInfo);
		Object out = handler.serialize(fieldRange, fieldKey);
		String outputFilePath = outputFolder + "/" + sheetInfo.getDefine().getExportInfo(fieldRange).getFileName() + "."
				+ fileFormat.getValue();
		if (fileFormat.outputTextFile()) {
			FileUtils.writeTextFile(outputFilePath, (String) out);
		} else {
			FileUtils.writeBinaryFile(outputFilePath, (byte[]) out);
		}
	}

	/**
	 * 批量导出数据
	 * 
	 * @param sheetInfos
	 * @param fileFormat
	 * @param fieldRange
	 * @param outputFolder
	 * @param fieldKey
	 */
	public static void serializeData(List<SheetInfo> sheetInfos, OutputDataFormat fileFormat, FieldRangeType fieldRange,
			String outputFolder, FieldKey fieldKey) {
		for (SheetInfo sheetInfo : sheetInfos) {
			serializeData(sheetInfo, fileFormat, fieldRange, outputFolder, fieldKey);
		}
	}
}
