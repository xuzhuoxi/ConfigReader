package cfg.serialize.cfgcontent;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

import cfg.serialize.FieldDataFormat;
import code.lang.BigNumberUtil;

public class DecimalContentHandler implements IContentSerializeHandler {

	private DecimalFormat format = new DecimalFormat("#.########");

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		int bCount = attrDataType.getDataLen();
		switch (bCount) {
		case 4:
			return (float) Double.parseDouble(valueContent);// 兼容科学计数法
		case 8:
			return Double.parseDouble(valueContent);
		default:
			return new BigDecimal(valueContent);
		}
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof BigDecimal) {
			return obj.toString().trim();
		} else if (obj instanceof Double || obj instanceof Float) {
			String rs = obj.toString().trim();
			if (rs.indexOf("E") != -1 || rs.indexOf("e") != -1) {// 科学记数格式
				return format.format(obj);
			} else {// 普通格式输出
				return rs;
			}
		} else {
			throw new Error("DecimalDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		boolean typeTrue = (obj instanceof Float) || (obj instanceof Double) || (obj instanceof BigDecimal);
		if (!typeTrue) {
			throw new Error("DecimalDataHandler.toBinary");
		}
		if (obj instanceof Float) {
			return BinaryContentUtil.getNumberConverter().toByteArray((Float) obj);
		} else if (obj instanceof Double) {
			return BinaryContentUtil.getNumberConverter().toByteArray((Double) obj);
		} else {
			return BigNumberUtil.toByteArray((BigInteger) obj, attrDataType.getDataLen());
		}
	}
}
