package cfg.serialize.cfgcontent;

import java.nio.charset.Charset;
import java.util.Arrays;

import cfg.serialize.FieldDataFormat;
import cfg.settings.Settings;
import code.array.ArrayUtils;
import code.lang.IntegerUtil;

public class StringContentHandler implements IContentSerializeHandler {
	private StringBuilder sb = new StringBuilder();

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		Charset sourceCharset = Settings.getInstance().getSysSettings().getSourceCharset();
		byte[] valueBytes = valueContent.getBytes(sourceCharset);
		int cByteCount = valueBytes.length;
		if (attrDataType.getByteCount() == -1 || cByteCount == attrDataType.getByteCount()) {
			return valueContent;
		} else {
			byte[] newStringBytes = Arrays.copyOf(valueBytes, attrDataType.getByteCount());
			sb.setLength(0);
			sb.append(new String(newStringBytes, sourceCharset));
			return sb.toString();
		}
	}

	@Override
	public String toJson(String valueContent) {
		return "\"" + valueContent + "\"";
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof String) {
			return "\"" + obj.toString() + "\"";
		} else {
			throw new Error("StringDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		String value = (String) obj;
		Charset targetCharset = Settings.getInstance().getSysSettings().getTargetCharset();
		byte[] stringBytes = value.getBytes(targetCharset);
		int byteCount = attrDataType.getByteCount();
		int realByteCount = (-1 == byteCount) ? stringBytes.length : byteCount;
		byte[] lens = IntegerUtil.toByteArray(realByteCount, 2);// 前面两个字节代表余下字符串字节长度
		byte[] datas = ArrayUtils.mergeArray(lens, stringBytes);
		return Arrays.copyOf(datas, realByteCount + 2);
	}
}
