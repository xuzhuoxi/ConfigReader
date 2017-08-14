package cfg.serialize.cfgcontent;

import java.math.BigInteger;

import cfg.serialize.FieldDataFormat;
import code.lang.BigIntegerUtil;
import code.lang.IntegerUtil;

public class IntegerContentHandler implements IContentSerializeHandler {

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		int dotIndex = valueContent.indexOf(".");
		String newContent;
		if (-1 == dotIndex) {
			newContent = valueContent;
		} else {
			if (0 == dotIndex)
				newContent = "0";
			else
				newContent = valueContent.substring(0, dotIndex);
		}
		if (null == attrDataType) {
			return new BigInteger(newContent);
		}
		if (attrDataType.getByteCount() <= 4) {
			return Integer.parseInt(newContent);
		} else {
			return new BigInteger(newContent);
		}
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof BigInteger || obj instanceof Integer) {
			return obj.toString().trim();
		} else {
			throw new Error("IntegerDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		boolean typeTrue = (obj instanceof Integer) || (obj instanceof BigInteger);
		if (!typeTrue) {
			throw new Error("IntegerDataHandler.toBinary");
		}
		if (obj instanceof Integer) {
			return IntegerUtil.toByteArray((Integer) obj, attrDataType.getByteCount());
		} else {
			return BigIntegerUtil.toByteArray((BigInteger) obj, attrDataType.getByteCount());
		}
	}

}
