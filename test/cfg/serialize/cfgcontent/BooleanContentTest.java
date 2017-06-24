package cfg.serialize.cfgcontent;

import org.junit.Test;

import cfg.serialize.cfgcontent.BooleanContentHandler;

public class BooleanContentTest {

	@Test
	public void testFromString() {
		BooleanContentHandler handler = new BooleanContentHandler();
		Boolean b0 = (Boolean) handler.fromString("True", null);
		Boolean b1 = (Boolean) handler.fromString("true", null);
		Boolean b2 = (Boolean) handler.fromString("False", null);
		Boolean b3 = (Boolean) handler.fromString("false", null);
		Boolean b4 = (Boolean) handler.fromString("0", null);
		Boolean b5 = (Boolean) handler.fromString("1", null);
		System.out.println(b0);
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println(b5);
	}

	@Test
	public void testToJson() {
		BooleanContentHandler handler = new BooleanContentHandler();
		System.out.println(handler.toJson(true, null));
		System.out.println(handler.toJson(false, null));
	}
}
