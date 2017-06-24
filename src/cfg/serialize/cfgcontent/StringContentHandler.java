package cfg.serialize.cfgcontent;

import java.nio.charset.Charset;
import java.util.Arrays;

import cfg.serialize.AttributeDataType;
import code.array.ArrayUtils;
import code.lang.IntegerUtil;

public class StringContentHandler implements IContentSerializeHandler {

	public static Charset CHARSET_SOURCE;
	public static Charset CHARSET_TARGET;

	private StringBuilder sb = new StringBuilder();

	@Override
	public Object fromString(String valueContent, AttributeDataType attrDataType) {
		byte[] valueBytes = valueContent.getBytes(StringContentHandler.CHARSET_SOURCE);
		int cByteCount = valueBytes.length;
		if (attrDataType.getByteCount() == -1 || cByteCount == attrDataType.getByteCount()) {
			return valueContent;
		} else {
			byte[] newStringBytes = Arrays.copyOf(valueBytes, attrDataType.getByteCount());
			sb.setLength(0);
			sb.append(new String(newStringBytes));
			return sb.toString();
		}
	}

	@Override
	public String toJson(String valueContent) {
		return "\"" + valueContent + "\"";
	}

	@Override
	public String toJson(Object obj, AttributeDataType attrDataType) {
		if (obj instanceof String) {
			return "\"" + obj.toString() + "\"";
		} else {
			throw new Error("StringDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, AttributeDataType attrDataType) {
		String value = (String) obj;
		byte[] stringBytes = value.getBytes(StringContentHandler.CHARSET_TARGET);
		int byteCount = attrDataType.getByteCount();
		int realByteCount = (-1 == byteCount) ? stringBytes.length : byteCount;
		byte[] lens = IntegerUtil.toByteArray(realByteCount, 2);
		byte[] datas = ArrayUtils.mergeArray(lens, stringBytes);
		return Arrays.copyOf(datas, realByteCount + 2);
	}
}
