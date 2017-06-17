package cfg;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import cfg.serialize.ExportProjectType;
import cfg.serialize.cfgdata.JsonSheetHandler;
import cfg.serialize.AttributeKeyType;
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
	public void testLoadSettings() {
		// String settingPath =
		// "E:/sourcestore/tools/Eclipse_ConfigReader/res/settings.json";
		String settingPath = "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/settings.json";
		Settings settings = Settings.parseByPath(settingPath);
		System.out.println(settings);
	}

	@Test
	public void testLoadWorkbook() {
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

		// System.out.println("Data Sheet Len: " + sheets.size());
		// for (SheetInfo sheetInfo : sheets) {
		// System.out.println(
		// "SheetNamed: " + sheetInfo.getSheetNamed() + ", DataLen=" +
		// sheetInfo.getDataList().size());
		// int index = 0;
		// for (String[] datas : sheetInfo.getDataList()) {
		// System.out.println("\t Index=" + index + "\t" +
		// this.stringArrayToString(datas));
		// index++;
		// }
		// }
		// SheetInfo si = sheets.get(0);
		// SheetDefine define = si.getDefine();
		// Integer[] indexs = define.getClientValidIndexs();
		//
		// DataType[] dateTypes = define.getDataTypeInstances();
		// List<String[]> datas = si.getDataList();
		// String[] keys = define.getFieldNameArray(LangType.Json.getValue());
		//
		// StringBuilder sb = new StringBuilder("[");
		// JsonItemHandler itemhandler = new JsonItemHandler();
		// for (String[] strings : datas) {
		// itemhandler.start();
		// itemhandler.append(indexs, dateTypes, keys, strings);
		// itemhandler.finish();
		// sb.append(itemhandler.getSerializedData() + ",");
		// }
		// sb.deleteCharAt(sb.length() - 1);
		// sb.append("]");
		// System.out.println(sb.toString());
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

	private StringBuilder sb = new StringBuilder();

	private String stringArrayToString(String[] stringAry) {
		sb.setLength(0);
		for (String string : stringAry) {
			sb.append("\t" + string);
		}
		return sb.toString();
	}
}
