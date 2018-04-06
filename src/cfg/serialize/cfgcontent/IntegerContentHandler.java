package cfg.serialize.cfgcontent;

import java.math.BigInteger;
import java.text.DecimalFormat;

import cfg.serialize.FieldDataFormat;
import code.lang.BigIntegerUtil;
import code.lang.NumberUtil;

public class IntegerContentHandler implements IContentSerializeHandler {

	private DecimalFormat format = new DecimalFormat("#");

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		if (null == attrDataType) {
			return new BigInteger(valueContent);
		}
		int dotIndex = valueContent.indexOf(".");
		int bc = attrDataType.getDataLen();
		if (-1 == dotIndex) {// 纯数字
			if (bc < 4) {
				return Integer.parseInt(valueContent);
			} else if (bc < 8) {
				return Long.parseLong(valueContent);
			} else {
				return new BigInteger(valueContent);
			}
		} else {
			if (0 == dotIndex) {// 小数点开头
				return 0;
			}
			int eIndex = Math.max(valueContent.indexOf("E"), valueContent.indexOf("e"));
			if (eIndex >= 1) {// 科学记数法
				double d = Double.parseDouble(valueContent);
				if (bc < 4) {
					return (int) d;
				} else if (bc < 8) {
					return (long) d;
				} else {
					return new BigInteger(valueContent);
				}
			} else {
				String subStr = valueContent.substring(0, dotIndex);// 截掉小数点以后
				if (bc < 4) {
					return Integer.parseInt(subStr);
				} else if (bc < 8) {
					return Long.parseLong(subStr);
				} else {
					return new BigInteger(subStr);
				}
			}
		}
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof BigInteger) {
			return obj.toString().trim();
		} else if (obj instanceof Integer || obj instanceof Long) {
			return format.format(obj);
		} else {
			throw new Error("IntegerDataHandler.toJson");
		}
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		boolean typeTrue = (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof BigInteger);
		if (!typeTrue) {
			throw new Error("IntegerDataHandler.toBinary");
		}
		int count = attrDataType.getDataLen();

		if (obj instanceof Integer) {
			int val = (Integer) obj;
			return NumberUtil.toByteArray(val, count);
		} else if (obj instanceof Long) {
			long val = (Long) obj;
			return NumberUtil.toByteArray(val, count);
		} else {
			return BigIntegerUtil.toByteArray((BigInteger) obj, count);
		}
	}

}
