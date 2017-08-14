package cfg;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import cfg.serialize.AttributeKeyType;
import cfg.serialize.ExportProjectType;
import cfg.serialize.cfgdata.BinarySheetHandler;
import cfg.serialize.cfgdata.JsonSheetHandler;
import cfg.settings.Settings;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;
import code.path.BasePathUtils;

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
	public void testJsonWorkbook() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		List<SheetInfo> sheets = info.getSheetInfos();
		JsonSheetHandler handler = new JsonSheetHandler();
		for (SheetInfo sheetInfo : sheets) {
			handler.config(sheetInfo);
			Object out = handler.serialize(ExportProjectType.Client, AttributeKeyType.Json);
			System.out.println(out);
		}
	}

	@Test
	public void testBinaryWorkbook() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		BinarySheetHandler handler = new BinarySheetHandler();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (SheetInfo sheetInfo : sheets) {
			handler.config(sheetInfo);
			byte[] out = (byte[]) handler.serialize(ExportProjectType.Client, AttributeKeyType.Json);
			System.out.println(sheetInfo);
			System.out.println(out.length);
		}
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
