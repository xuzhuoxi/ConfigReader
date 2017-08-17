package cfg.serialize.cfgdata;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import cfg.AppDefine;
import cfg.serialize.ClassLangType;
import cfg.serialize.FieldKey;
import cfg.serialize.FieldRangeType;
import cfg.serialize.generater.IContentGenerater;
import cfg.serialize.generater.LangFileGenerater;
import cfg.settings.Settings;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;
import code.path.BasePathUtils;

public class DataFileTest {

	@Test
	public void testJsonDataFile() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		FieldRangeType fieldRange = FieldRangeType.Server;
		FieldKey fieldKey = FieldKey.Json;
		String outputFolder = basePath + "/../testres/dist/data";

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
	public void testBinaryDataFile() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		FieldRangeType fieldRange = FieldRangeType.Server;
		FieldKey fieldKey = FieldKey.Json;
		String outputFolder = basePath + "/../testres/dist/data";

		BinarySheetHandler handler = new BinarySheetHandler();
		List<SheetInfo> sheets = info.getSheetInfos();

		for (SheetInfo sheetInfo : sheets) {
			handler.config(sheetInfo);
			byte[] out = (byte[]) handler.serialize(fieldRange, fieldKey);
			System.out.println(sheetInfo);
			String outputFilePath = outputFolder + "/server/"
					+ sheetInfo.getDefine().getExportInfo(fieldRange).getFileName();
			System.out.println(outputFilePath);
			FileUtils.writeBinaryFile(outputFilePath, out);
		}
	}

	@Test
	public void testSerializeJava() {
		String basePath = BasePathUtils.getBasePath(this.getClass());
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		FieldRangeType fieldRange = FieldRangeType.Server;
		String outputFolder = basePath + "/../testres/xu";

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		IContentGenerater cg = LangFileGenerater.getModuleGenerater(ClassLangType.Java, fieldRange);
		String value = cg.serialize(sheetDefine);
		String outputFilePath = outputFolder + "/" + sheetInfo.getDefine().getExportInfo(fieldRange).getClassName()
				+ ".java";
		FileUtils.writeTextFile(outputFilePath, value, Settings.getInstance().getSysSettings().getTargetEncoding());
	}

	@Test
	public void testBinaryReader() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/dist/client/cfg_building.cb";
		File file = new File(filePath);
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new FileInputStream(file));
			while (0 != dis.available()) {
				// CfgBuilding cfgb = new CfgBuilding();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
