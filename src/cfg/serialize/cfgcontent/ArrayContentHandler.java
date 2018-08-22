package cfg.serialize.cfgcontent;

import cfg.serialize.FieldDataFormat;
import code.array.ArrayUtils;
import code.lang.NumberUtil;

public class ArrayContentHandler implements IContentSerializeHandler {
	private IContentSerializeHandler eleContentHandler;

	public ArrayContentHandler(IContentSerializeHandler eleContentHandler) {
		super();
		this.eleContentHandler = eleContentHandler;
	}

	@Override
	public Object fromString(String valueContent, FieldDataFormat attrDataType) {
		return ArrayContentUtil.fromString(valueContent, attrDataType, eleContentHandler);
	}

	@Override
	public String toJson(String valueContent) {
		return valueContent.trim();
	}

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		return ArrayContentUtil.toJson((Object[]) obj, attrDataType, eleContentHandler);
	}

	@Override
	public byte[] toBinary(Object obj, FieldDataFormat attrDataType) {
		return ArrayContentUtil.toBinary((Object[]) obj, attrDataType, eleContentHandler);
	}
}

class ArrayContentUtil {
	private static StringBuilder sb = new StringBuilder();

	/**
	 * 解释文本为数组
	 * 
	 * @param valueContent
	 *            文本内容
	 * @param attrDataType
	 *            文本格式定义
	 * @param contentHandler
	 *            单个元素解释器
	 * @return 数组
	 */
	public static Object[] fromString(String valueContent, FieldDataFormat attrDataType,
			IContentSerializeHandler contentHandler) {
		if (!attrDataType.getIsArray()) {
			throw new Error("FieldDataFormat is not Array");
		}
		String trimContent = valueContent.trim();
		trimContent = trimContent.substring(1, trimContent.length() - 1).trim();
		if (trimContent.length() == 0) {
			return new Object[0];
		}
		String[] values = trimContent.split(",");
		// System.out.println("数组内：" + trimContent + " len:" + values.length);
		Object[] rs = new Object[values.length];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = contentHandler.fromString(values[i].trim(), attrDataType);
		}
		return rs;
	}

	/**
	 * 序列化为Json字符串
	 * 
	 * @param ao
	 *            数组
	 * @param attrDataType
	 *            元素文本格式定义
	 * @param contentHandler
	 *            单个元素解释器
	 * @return Json文本
	 */
	public static String toJson(Object[] ao, FieldDataFormat attrDataType, IContentSerializeHandler contentHandler) {
		try {
			sb.setLength(0);
			sb.append("[");
			for (Object object : ao) {
				sb.append(contentHandler.toJson(object, attrDataType));
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			return sb.toString();
		} catch (Exception e) {
			throw new Error("ArrayContentUtil.toJson");
		}
	}

	/**
	 * 序列化为二进制数据
	 * 
	 * @param ao
	 *            数组
	 * @param attrDataType
	 *            元素文本格式定义
	 * @param contentHandler
	 *            单个元素解释器
	 * @return 二进制数据
	 */
	public static byte[] toBinary(Object[] ao, FieldDataFormat attrDataType, IContentSerializeHandler contentHandler) {
		short len = (short) ao.length;
		// System.out.println("数组长度：" + len);
		byte[] rs = NumberUtil.toByteArray(len);// 前面两个字节代表数组个数
		for (Object o : ao) {
			rs = ArrayUtils.mergeArray(rs, contentHandler.toBinary(o, attrDataType));
		}
		return rs;
	}
}
