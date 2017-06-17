package cfg.serialize.data;

import org.junit.Test;

import cfg.serialize.AttributeDataType;
import cfg.serialize.cfgcontent.StringContentHandler;

public class TestString {

	@Test
	public void testFromString() {
		StringContentHandler handler = new StringContentHandler();
		AttributeDataType dt0 = AttributeDataType.String;
		AttributeDataType dt1 = AttributeDataType.from("string(5)");
		AttributeDataType dt2 = AttributeDataType.from("string(10)");
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
