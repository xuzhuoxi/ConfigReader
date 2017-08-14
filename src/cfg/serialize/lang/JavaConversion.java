package cfg.serialize.lang;

import cfg.serialize.FieldDataFormat;

public class JavaConversion implements ILangConversion {

	@Override
	public String conversionDataType(FieldDataFormat dataType) {
		if (FieldDataFormat.Boolean == dataType) {
			return "boolean";
		}
		if (FieldDataFormat.String.getTypeName() == dataType.getTypeName()) {
			return "String";
		}
		if (FieldDataFormat.UInt8 == dataType || FieldDataFormat.UInt16 == dataType
				|| FieldDataFormat.UInt32 == dataType || FieldDataFormat.Int8 == dataType
				|| FieldDataFormat.Int16 == dataType || FieldDataFormat.Int32 == dataType) {
			return "int";
		}
		return "long";
	}

}
