package cfg.serialize.cfgcontent;

import java.math.BigInteger;

import cfg.serialize.AttributeDataType;

public class IntegerContentHandler implements IContentSerializeHandler {

	@Override
	public Object fromString(String valueContent, AttributeDataType attrDataType) {
		int dotIndex = valueContent.indexOf(".");
		if (-1 == dotIndex) {
			return new BigInteger(valueContent);
		} else {
			if (0 == dotIndex)
				return new BigInteger("0");
			else
				return new BigInteger(valueContent.substring(0, dotIndex));
		}
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, AttributeDataType attrDataType) {
		if (obj instanceof BigInteger || obj instanceof Integer) {
			return obj.toString().trim();
		} else {
			throw new Error("IntegerDataHandler.toJson");
		}
	}
}
