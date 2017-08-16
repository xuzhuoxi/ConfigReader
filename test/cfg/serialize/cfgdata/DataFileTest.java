package cfg.serialize.cfgdata;

import java.util.List;

import org.junit.Test;

import cfg.AppDefine;
import cfg.serialize.FieldKey;
import cfg.serialize.FieldRangeType;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;

public class DataFileTest {

	@Test
	public void testJsonFile() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		FieldRangeType fieldRange = FieldRangeType.Server;
		FieldKey fieldKey = FieldKey.Json;
		String outputFolder = basePath + "/../res/dist";

		List<SheetInfo> sheets = info.getSheetInfos();
		JsonSheetHandler handler = new JsonSheetHandler();
		for (SheetInfo sheetInfo : sheets) {
			handler.config(sheetInfo);
			Object out = handler.serialize(fieldRange, fieldKey);
			String outputFilePath = outputFolder + "/server/"
					+ sheetInfo.getDefine().getExportInfo(fieldRange).getFileName();
			System.out.println(outputFilePath);
			FileUtils.writeTextFile(outputFilePath, (String) out);
		}
	}

	@Test
	public void testBinaryFile() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		FieldRangeType fieldRange = FieldRangeType.Client;
		FieldKey fieldKey = FieldKey.Json;
		String outputFolder = basePath + "/../res/dist";

		BinarySheetHandler handler = new BinarySheetHandler();
		List<SheetInfo> sheets = info.getSheetInfos();

		for (SheetInfo sheetInfo : sheets) {
			handler.config(sheetInfo);
			byte[] out = (byte[]) handler.serialize(fieldRange, fieldKey);
			System.out.println(sheetInfo);
			String outputFilePath = outputFolder + "/client/"
					+ sheetInfo.getDefine().getExportInfo(fieldRange).getFileName();
			System.out.println(outputFilePath);
			FileUtils.writeBinaryFile(outputFilePath, out);
		}
	}

}
