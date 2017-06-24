package cfg.serialize.lang;

import cfg.serialize.AttributeDataType;

public class TSConversion implements ILangConversion {

	private static final String TYPE_NUMBER = "number";
	private static final String TYPE_NUMBER64 = "dcodeIO.Long";
	private static final String TYPE_BOOLEAN = "boolean";
	private static final String TYPE_STRING = "string";

	@Override
	public String conversionDataType(AttributeDataType dataType) {
		if (AttributeDataType.Boolean == dataType) {
			return TYPE_BOOLEAN;
		}
		if (AttributeDataType.String.getTypeName() == dataType.getTypeName()) {
			return TYPE_STRING;
		}
		if (AttributeDataType.UInt8 == dataType || AttributeDataType.UInt16 == dataType
				|| AttributeDataType.UInt32 == dataType || AttributeDataType.Int8 == dataType
				|| AttributeDataType.Int16 == dataType || AttributeDataType.Int32 == dataType) {
			return TYPE_NUMBER;
		}
		return TYPE_NUMBER64;
	}
}
