package cfg.serialize.cfgcontent;

import cfg.serialize.FieldDataFormat;

public class JsonContentHandler extends StringContentHandler implements IContentSerializeHandler {

	@Override
	public String toJson(Object obj, FieldDataFormat attrDataType) {
		if (obj instanceof String) {
			String str = obj.toString().trim();
			return str;
		} else {
			throw new Error("StringDataHandler.toJson");
		}
	}
}
