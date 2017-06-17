package cfg.serialize.cfgcontent;

import cfg.serialize.AttributeDataType;
import code.lang.BooleanUtil;

public class BooleanContentHandler implements IContentSerializeHandler {

	@Override
	public Object fromString(String valueContent, AttributeDataType attrDataType) {
		return BooleanUtil.fromString(valueContent);
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, AttributeDataType attrDataType) {
		if (obj instanceof Boolean) {
			return obj.toString();
		} else {
			throw new Error("BooleanDataHandler.toJson");
		}
	}
}
