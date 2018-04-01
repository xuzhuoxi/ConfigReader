package cfg;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import cfg.serialize.FieldKey;
import cfg.serialize.FieldRangeType;
import cfg.serialize.cfgdata.BinarySheetHandler;
import cfg.serialize.cfgdata.JsonSheetHandler;
import cfg.settings.Settings;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;
import code.lang.NumberUtil;
import code.path.BasePathUtils;
import xu.BinaryReaderProxy;
import xu.CfgBuilding;

public class ConfigReaderTest {

	@Test
	public void testIntegerObject() {
		BigInteger bi0 = new BigInteger("1");
		System.out.println(bi0);
	}

	@Test
	public void testStringObject() {
		String str = "asjdflslaj${name}alfjsdlfjlsajf_${name}_asljflsjdlafjlsdf";
		String str2 = str.replace("${name}", "顶你个肺");
		System.out.println(str);
		System.out.println(str2);
	}

	@Test
	public void testWorkbook2Json() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/source/cfg_building.xls";
		System.out.println("FilePaht:" + filePath);
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		List<SheetInfo> sheets = info.getSheetInfos();
		JsonSheetHandler handler = new JsonSheetHandler();
		for (SheetInfo sheetInfo : sheets) {
			handler.config(sheetInfo);
			Object out = handler.serialize(FieldRangeType.Client, FieldKey.Json);
			System.out.println(out);
		}
	}

	@Test
	public void testJson2Objects() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/target/data/client/cfg_building_c.json";
		System.out.println("FilePaht:" + filePath);
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
	public void testBinary2Objects() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/target/data/client/cfg_building_c.binary";
		System.out.println("FilePaht:" + filePath);

		InputStream is;
		DataInputStream dis;
		try {
			is = new FileInputStream(filePath);
			dis = new DataInputStream(is);
			BinaryReaderProxy proxy = new BinaryReaderProxy(dis, "utf-8");
			while (true) {
				CfgBuilding obj = new CfgBuilding();
				obj.parseBinary(proxy);
				System.out.println(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testOverflow() {
		System.out.println(Integer.MAX_VALUE);
		String uintMax = "4294967295";
		int i = NumberUtil.intFromString(uintMax);
		System.out.println(i + "----------");

		long i2 = Long.parseLong(uintMax);
		System.out.println(i2);
		int i3 = (int) (i2);
		System.out.println(i3 + " " + (long) i3);
		long i4 = i3 + ((long) (Integer.MAX_VALUE) + 1) * 2;
		System.out.println(i4);
	}

	@Test
	public void testSettings() {
		System.out.println(Settings.getInstance());
	}

	@Test
	public void testBasePath() {
		System.out.println(BasePathUtils.getBasePath());
		System.out.println(BasePathUtils.getBasePath(Settings.class));
		System.out.println(BasePathUtils.getAppPath(Settings.class));
	}
}
