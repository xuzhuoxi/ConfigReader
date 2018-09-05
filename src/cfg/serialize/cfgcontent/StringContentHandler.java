package cfg.serialize.cfgcontent;

import java.nio.charset.Charset;
import java.util.Arrays;

import cfg.serialize.FieldDataFormat;
import cfg.settings.Settings;
import code.array.ArrayUtils;

public class StringContentHandler implements IContentSerializeHandler {

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		int dataLen = attrDataType.getDataLen();
		int currentLen = valueContent.length();
		if (-1 == dataLen || dataLen == currentLen) {
			return valueContent;
		} else {
			String content = valueContent;
			if (currentLen < dataLen) {
				int add = dataLen - currentLen;
				for (int i = 0; i < add; i++) {
					content = content + " ";
				}
			} else {
				content = valueContent.substring(0, dataLen);
			}
			return content;
		}
	}

	@Override
	public String toJson(String valueContent) {
		return this.toJson(valueContent, null);
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof String) {
			String str = obj.toString();
			int index = str.indexOf("\"");
			if (-1 == index) {
				return "\"" + str + "\"";
			} else {
				return "\"" + str.replaceAll("\"", "\\\\\"") + "\"";// 替换双引号
			}
		} else {
			throw new Error("StringDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		String value = (String) obj;
		Charset targetCharset = Settings.getInstance().getSysSettings().getTargetCharset();
		byte[] stringBytes = value.getBytes(targetCharset);
		int byteCount = attrDataType.getDataLen();
		short realByteCount = (-1 == byteCount) ? (short) stringBytes.length : (short) byteCount;
		byte[] lens = BinaryContentUtil.getNumberConverter().toByteArray(realByteCount);// 前面两个字节代表余下字符串字节长度
		byte[] datas = ArrayUtils.mergeArray(lens, stringBytes);
		return Arrays.copyOf(datas, realByteCount + 2);
	}
}
