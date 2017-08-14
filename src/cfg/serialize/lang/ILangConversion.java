package cfg.serialize.lang;

import cfg.serialize.FieldDataFormat;

public interface ILangConversion {
	/**
	 * 转换AttributeDataType对象到对应编程语言的数据结构
	 * 
	 * @param dataType
	 * @return
	 */
	String conversionDataType(FieldDataFormat dataType);
}
