package cfg.serialize.cfgcontent;

import java.math.BigInteger;

import org.junit.Test;

import cfg.serialize.cfgcontent.IntegerContentHandler;

public class IntegerContentTest {

	@Test
	public void testFromString() {
		IntegerContentHandler handler = new IntegerContentHandler();
		BigInteger v1 = (BigInteger) handler.fromString("1", null);
		BigInteger v2 = (BigInteger) handler.fromString("-1", null);
		BigInteger v3 = (BigInteger) handler.fromString("0", null);
		BigInteger v4 = (BigInteger) handler.fromString("+0", null);
		BigInteger v5 = (BigInteger) handler.fromString("-0", null);
		BigInteger v6 = (BigInteger) handler.fromString("1.0", null);
		BigInteger v7 = (BigInteger) handler.fromString("-1.0", null);
		BigInteger v8 = (BigInteger) handler.fromString("0.0", null);
		BigInteger v9 = (BigInteger) handler.fromString("+0.0", null);
		BigInteger v10 = (BigInteger) handler.fromString("-0.0", null);
		System.out.println(v1);
		System.out.println(v2);
		System.out.println(v3);
		System.out.println(v4);
		System.out.println(v5);
		System.out.println(v6);
		System.out.println(v7);
		System.out.println(v8);
		System.out.println(v9);
		System.out.println(v10);
	}
	
	public void testToJson(){
		
	}
}
