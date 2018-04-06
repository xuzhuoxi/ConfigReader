package cfg.serialize;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import cfg.AppDefine;
import cfg.serialize.cfgdata.BinarySheetHandler;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;
import code.lang.NumberUtil;
import xu.BinaryReaderProxy;
import xu.CfgBuilding;

public class SerializeTest {

	@Test
	public void testNumber2Binary() {
		int i = 99;
		byte[] ba = NumberUtil.toByteArray(i, 2);
		int i2 = NumberUtil.toShort(ba);
		System.out.println(i2);
		String str = "";
		for (byte b : ba) {
			str = str + " " + b;
		}
		System.out.println(str);
	}

	@Test
	public void testDataFile() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/source/cfg_building.xls";
		System.out.println(filePath);
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (SheetInfo sheetInfo : sheets) {
			SerializeDataUtil.serializeData(sheetInfo, OutputDataFormat.Json, FieldRangeType.Server,
					basePath + "/../testres/dist/data/server", FieldKey.Json);
			SerializeDataUtil.serializeData(sheetInfo, OutputDataFormat.Binary, FieldRangeType.Server,
					basePath + "/../testres/dist/data/server", null);
			SerializeDataUtil.serializeData(sheetInfo, OutputDataFormat.Json, FieldRangeType.Client,
					basePath + "/../testres/dist/data/client", FieldKey.Json);
			SerializeDataUtil.serializeData(sheetInfo, OutputDataFormat.Binary, FieldRangeType.Client,
					basePath + "/../testres/dist/data/client", null);
		}
	}

	@Test
	public void testWorkbook2Binary() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/source/cfg_building.xls";
		System.out.println("FilePaht:" + filePath);
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		BinarySheetHandler handler = new BinarySheetHandler();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (SheetInfo sheetInfo : sheets) {
			handler.config(sheetInfo);
			byte[] out = (byte[]) handler.serialize(FieldRangeType.Client, FieldKey.Json);
			System.out.println(sheetInfo);
			System.out.println(out.length);
		}
	}

	@Test
	public void testSerializeDefine() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/source/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (SheetInfo sheetInfo : sheets) {
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, OutputDefineLangType.Java,
					basePath + "/../testres/dist/define/client", "java");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, OutputDefineLangType.TypeScript,
					basePath + "/../testres/dist/define/client", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, OutputDefineLangType.CSharp,
					basePath + "/../testres/dist/define/client", "cs");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, OutputDefineLangType.Java,
					basePath + "/../testres/dist/define/server", "java");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, OutputDefineLangType.TypeScript,
					basePath + "/../testres/dist/define/server", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, OutputDefineLangType.CSharp,
					basePath + "/../testres/dist/define/server", "cs");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.DB, OutputDefineLangType.Java,
					basePath + "/../testres/dist/define/db", "java");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.DB, OutputDefineLangType.TypeScript,
					basePath + "/../testres/dist/define/db", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.DB, OutputDefineLangType.CSharp,
					basePath + "/../testres/dist/define/db", "cs");
		}
	}

	@Test
	public void testBinary2Objects() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/../testres/dist/data/client/cfg_building_c.binary";
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
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJson2Objects() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/../testres/dist/data/client/cfg_building_c.json";
		System.out.println("FilePath:" + filePath);
		String jsonContent = FileUtils.readFileContent(filePath);
		System.out.println("JsonContent:" + jsonContent);
		JSONArray ary = new JSONArray(jsonContent);
		int len = ary.length();
		System.out.println("Length:" + len);
		for (int i = 0; i < len; i++) {
			JSONObject jObj = ary.getJSONObject(i);
			CfgBuilding obj = new CfgBuilding();
			obj.parseJson(jObj);
			System.out.println(obj);
		}
	}

	@Test
	public void test() {
		byte b = -11;
		short s = (short) (b & 0x00ff);
		System.out.println(s);
	}

}
