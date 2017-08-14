package cfg.serialize.cfgcontent;

import java.math.BigDecimal;

import cfg.serialize.FieldDataFormat;

public class DecimalContentHandler implements IContentSerializeHandler {

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		return new BigDecimal(valueContent);
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof Double || obj instanceof BigDecimal || obj instanceof Float) {
			return obj.toString().trim();
		} else {
			throw new Error("DecimalDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		// TODO Auto-generated method stub
		return null;
	}
}
