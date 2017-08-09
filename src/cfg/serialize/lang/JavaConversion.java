package cfg.serialize.lang;

import cfg.serialize.AttributeDataType;

public class JavaConversion implements ILangConversion {

	@Override
	public String conversionDataType(AttributeDataType dataType) {
		if (AttributeDataType.Boolean == dataType) {
			return "boolean";
		}
		if (AttributeDataType.String.getTypeName() == dataType.getTypeName()) {
			return "String";
		}
		if (AttributeDataType.UInt8 == dataType || AttributeDataType.UInt16 == dataType
				|| AttributeDataType.UInt32 == dataType || AttributeDataType.Int8 == dataType
				|| AttributeDataType.Int16 == dataType || AttributeDataType.Int32 == dataType) {
			return "int";
		}
		return "long";
	}

}
