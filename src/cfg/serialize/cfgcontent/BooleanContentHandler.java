package cfg.serialize.cfgcontent;

import cfg.serialize.FieldDataFormat;
import code.lang.BooleanUtil;

public class BooleanContentHandler implements IContentSerializeHandler {

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		return BooleanUtil.fromString(valueContent);
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof Boolean) {
			return obj.toString();
		} else {
			throw new Error("BooleanDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof Boolean && attrDataType.equals(FieldDataFormat.Boolean)) {
			byte b = BooleanUtil.toByte((Boolean) obj);
			return new byte[] { b };
		} else {
			throw new Error("BooleanDataHandler.toBinary");
		}
	}
}
