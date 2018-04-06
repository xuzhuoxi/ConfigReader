package cfg;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;

import org.junit.Test;

import cfg.serialize.FieldKey;
import cfg.serialize.FieldRangeType;
import cfg.serialize.cfgdata.JsonSheetHandler;
import cfg.settings.Settings;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;
import code.lang.NumberUtil;
import code.path.BasePathUtils;

public class ConfigReaderTest {

	@Test
	public void testIntegerObject() {
		// BigInteger bi0 = new BigInteger("1");
		// System.out.println(bi0);
		//
		// System.out.println(((int) Byte.MAX_VALUE + 1) * 2);
		// System.out.println(((int) Short.MAX_VALUE + 1) * 2);
		// System.out.println((Long.MAX_VALUE + 1) * 2);
		// System.out.println();
		//
		// System.out.println(Float.MAX_VALUE);
		// System.out.println(new DecimalFormat("#").format(Float.MAX_VALUE));
		// System.out.println(Float.MIN_VALUE);
		// System.out.println(new DecimalFormat("#.#").format(Float.MIN_VALUE));
		// System.out.println();
		//
		// float l = 2147483647;
		// System.out.println(l);
		// System.out.println(l + "");
		// System.out.println(String.valueOf(l));
		// System.out.println();

//		String str = "2.147483647E9";
////		long l = Long.parseLong(str);
//		long l = (long)Double.parseDouble(str);
//		System.out.println(str + "," + l);
//		System.out.println((Long)l);
//		System.out.println(new BigInteger("3.14"));
		
		String str = "-214740000";
		float f = Float.parseFloat(str);
		System.out.println(f + "," + new DecimalFormat("#").format(f));
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
