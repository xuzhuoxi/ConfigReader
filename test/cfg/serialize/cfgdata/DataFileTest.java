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
import cfg.serialize.FileFormat;
import cfg.serialize.SerializeDataUtil;
import cfg.serialize.generater.IContentGenerater;
import cfg.serialize.generater.LangFileGenerater;
import cfg.settings.Settings;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;
import code.path.BasePathUtils;
import xu.BinaryReaderProxy;
import xu.CfgBuilding;

public class DataFileTest {

	@Test
	public void testDataFile() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (SheetInfo sheetInfo : sheets) {
			SerializeDataUtil.serializeData(sheetInfo, FileFormat.Json, FieldRangeType.Server,
					basePath + "/../testres/dist/data/server", FieldKey.Json);
			SerializeDataUtil.serializeData(sheetInfo, FileFormat.Binary, FieldRangeType.Server,
					basePath + "/../testres/dist/data/server", null);
			SerializeDataUtil.serializeData(sheetInfo, FileFormat.Json, FieldRangeType.Client,
					basePath + "/../testres/dist/data/client", FieldKey.Json);
			SerializeDataUtil.serializeData(sheetInfo, FileFormat.Binary, FieldRangeType.Client,
					basePath + "/../testres/dist/data/client", null);
		}
	}

	@Test
	public void testSerializeJava() {
		String basePath = BasePathUtils.getBasePath(this.getClass());
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		FieldRangeType fieldRange = FieldRangeType.Server;
		String outputFolder = basePath + "/../test/xu";

		List<SheetInfo> sheets = info.getSheetInfos();

		this.serialize2DefineClass(sheets, fieldRange, outputFolder, ClassLangType.Java, "java");
		this.serialize2DefineClass(sheets, fieldRange, outputFolder, ClassLangType.TypeScript, "ts");
	}

	private void serialize2DefineClass(List<SheetInfo> sheets, FieldRangeType fieldRange, String outputFolder,
			ClassLangType lang, String extendName) {
		for (SheetInfo sheetInfo : sheets) {
			SheetDefine sheetDefine = sheetInfo.getDefine();
			IContentGenerater cg = LangFileGenerater.getModuleGenerater(lang, fieldRange);
			String value = cg.serialize(sheetDefine);
			String outputFilePath = outputFolder + "/" + sheetInfo.getDefine().getExportInfo(fieldRange).getClassName()
					+ "." + extendName;
			FileUtils.writeTextFile(outputFilePath, value, Settings.getInstance().getSysSettings().getTargetEncoding());
		}
	}

	@Test
	public void testBinaryReader() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/dist/data/server/cfg_building.sj";
		File file = new File(filePath);
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new FileInputStream(file));
			BinaryReaderProxy brp = new BinaryReaderProxy(dis, "UTF-8");
			while (0 != dis.available()) {
				CfgBuilding cfgb = new CfgBuilding();
				cfgb.parseBinary(brp);
				System.out.println(cfgb);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		byte b = -11;
		short s = (short) (b & 0x00ff);
		System.out.println(s);
	}

}
