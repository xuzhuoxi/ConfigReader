package cfg.serialize;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import cfg.AppDefine;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;
import xu.BinaryReaderProxy;
import xu.CfgBuilding;

public class SerializeTest {

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
	public void testSerializeDefine() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (SheetInfo sheetInfo : sheets) {
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, ClassLangType.Java,
					basePath + "/../testres/dist/define/server", "java");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, ClassLangType.TypeScript,
					basePath + "/../testres/dist/define/server", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, ClassLangType.CSharp,
					basePath + "/../testres/dist/define/server", "cs");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, ClassLangType.Java,
					basePath + "/../testres/dist/define/client", "java");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, ClassLangType.TypeScript,
					basePath + "/../testres/dist/define/client", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, ClassLangType.CSharp,
					basePath + "/../testres/dist/define/client", "cs");
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
