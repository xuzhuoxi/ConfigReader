package cfg.serialize.cfgcontent;

import java.math.BigDecimal;

import cfg.serialize.AttributeDataType;

public class DecimalContentHandler implements IContentSerializeHandler {

	@Override
	public Object fromString(String valueContent, AttributeDataType attrDataType) {
		return new BigDecimal(valueContent);
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, AttributeDataType attrDataType) {
		if (obj instanceof Double || obj instanceof BigDecimal || obj instanceof Float) {
			return obj.toString().trim();
		} else {
			throw new Error("DecimalDataHandler.toJson");
		}
	}

}
