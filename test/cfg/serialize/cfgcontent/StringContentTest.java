package cfg.serialize.cfgcontent;

import org.junit.Test;

import cfg.serialize.FieldDataFormat;
import code.array.ArrayUtils;
import code.lang.NumberUtil;

public class StringContentTest {

	@Test
	public void testBinary() {
		String[] strs = new String[] { "顶你个肺", "123456", "abcdef" };
		StringContentHandler handler = new StringContentHandler();
		FieldDataFormat attType = FieldDataFormat.from("string(25)");
		// AttributeDataType attType = AttributeDataType.String;
		for (String str : strs) {
			byte[] bs = handler.toBinary(str, attType);
			int len = NumberUtil.toShort(new byte[] { bs[0], bs[1] });
			String newStr = new String(ArrayUtils.subArray(bs, 2));
			System.out.println(bs.length);
			System.out.println(len);
			System.out.println("|" + newStr + "|");
			System.out.println("|" + newStr.trim() + "|");
			System.out.println();
		}
	}

	@Test
	public void testFromString() {
		StringContentHandler handler = new StringContentHandler();
		FieldDataFormat dt0 = FieldDataFormat.String;
		FieldDataFormat dt1 = FieldDataFormat.from("string(5)");
		FieldDataFormat dt2 = FieldDataFormat.from("string(10)");
		System.out.println(dt0.toOriginalString());
		System.out.println(dt0.toString());
		System.out.println(dt1.toOriginalString());
		System.out.println(dt1.toString());
		System.out.println(dt2.toOriginalString());
		System.out.println(dt2.toString());
		String value = "1234567";
		String v1 = (String) handler.fromString(value, dt0);
		String v2 = (String) handler.fromString(value, dt1);
		String v3 = (String) handler.fromString(value, dt2);
		System.out.println(v1.length() + ":|" + v1 + "|");
		System.out.println(v1.length() + ":|" + v2 + "|");
		System.out.println(v1.length() + ":|" + v3 + "|");
	}

	@Test
	public void testStringBuilder() {
		StringBuilder sb = new StringBuilder("1234567");
		sb.setLength(5);
		System.out.println(sb.toString());
		sb.setLength(10);
		System.out.println(sb.toString());
	}
}
