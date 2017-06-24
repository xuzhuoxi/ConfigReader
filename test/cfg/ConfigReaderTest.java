package cfg;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import cfg.serialize.AttributeKeyType;
import cfg.serialize.ExportProjectType;
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
	public void testLoadSettings() {
		// String settingPath =
		// "E:/sourcestore/tools/Eclipse_ConfigReader/res/settings.json";
		String settingPath = "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/settings.json";
		Settings settings = Settings.parseByPath(settingPath);
		System.out.println(settings);
	}

	@Test
	public void testJsonWorkbook() {
		String settingPath = "E:/sourcestore/tools/Eclipse_ConfigReader/res/settings.json";
		// String settingPath =
		// "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/settings.json";
		Settings settings = Settings.parseByPath(settingPath);
		String filePath = "E:/sourcestore/tools/Eclipse_ConfigReader/res/configs/cfg_building.xls";
		// String filePath =
		// "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos(settings);

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
		String settingPath = "E:/sourcestore/tools/Eclipse_ConfigReader/res/settings.json";
		// String settingPath =
		// "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/settings.json";
		Settings settings = Settings.parseByPath(settingPath);
		String filePath = "E:/sourcestore/tools/Eclipse_ConfigReader/res/configs/cfg_building.xls";
		// String filePath =
		// "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos(settings);

		// List<SheetInfo> sheets = info.getSheetInfos();
		// BinarySheetHandler handler = new BinarySheetHandler();
		// for (SheetInfo sheetInfo : sheets) {
		// handler.config(sheetInfo);
		// Object out = handler.serialize(ExportProjectType.Client,
		// AttributeKeyType.Json);
		// System.out.println(out);
		// }
	}

	@Test
	public void testSettings() {
		String filePath = "E:/sourcestore/tools/Eclipse_ConfigReader/res/settings.json";
		Settings settings = Settings.parseByPath(filePath);
		System.out.println(settings);
	}

	@Test
	public void testBasePath() {
		System.out.println(BasePathUtils.getBasePath());
		System.out.println(BasePathUtils.getAppPath(Settings.class));
	}
}
