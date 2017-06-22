package cfg.serialize.cfgcontent;

import cfg.serialize.AttributeDataType;
import code.array.ArrayUtils;
import code.lang.IntegerUtil;

public class StringContentHandler implements IContentSerializeHandler {

	private StringBuilder sb = new StringBuilder();

	@Override
	public Object fromString(String valueContent, AttributeDataType attrDataType) {
		int cByteCount = valueContent.length() << 1;
		if (attrDataType.getByteCount() == -1 || cByteCount == attrDataType.getByteCount()) {
			return valueContent;
		} else {
			if (cByteCount > attrDataType.getByteCount()) {
				return valueContent.substring(0, (attrDataType.getByteCount() >> 1));
			} else {
				sb.setLength(0);
				sb.append(valueContent);
				sb.setLength(attrDataType.getByteCount() >> 1);
				return sb.toString();
			}
		}
	}

	@Override
	public String toJson(String valueContent) {
		return "\"" + valueContent.trim() + "\"";
	}

	@Override
	public String toJson(Object obj, AttributeDataType attrDataType) {
		if (obj instanceof String) {
			return "\"" + obj.toString().trim() + "\"";
		} else {
			throw new Error("StringDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, AttributeDataType attrDataType) {
		String value = (String) obj;
		byte[] stringBytes = value.getBytes();
		int byteCount = attrDataType.getByteCount();
		int len = (-1 == byteCount) ? stringBytes.length : byteCount;
		byte[] lens = IntegerUtil.toByteArray(len);
		return ArrayUtils.mergeArray(lens, stringBytes);
	}
}
