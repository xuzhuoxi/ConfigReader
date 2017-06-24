package cfg.serialize.lang;

import cfg.serialize.AttributeDataType;

public interface ILangConversion {
	/**
	 * 转换AttributeDataType对象到对应编程语言的数据结构
	 * 
	 * @param dataType
	 * @return
	 */
	String conversionDataType(AttributeDataType dataType);
}
