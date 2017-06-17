package cfg.serialize.cfgdata;

import cfg.serialize.AttributeDataType;
import cfg.serialize.cfgcontent.ContentSerializeHandlerMap;
import cfg.serialize.cfgcontent.IContentSerializeHandler;

public class JsonTokenHandler implements ITokenHandler {
	private StringBuilder sb = new StringBuilder();

	@Override
	public Object serializeContentToken(AttributeDataType attrDataType, String attrkey, String valueContent) {
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		return "\"" + attrkey + "\":" + handler.toJson(valueContent);
	}

	@Override
	public Object serializeObjectToken(AttributeDataType attrDataType, String attrkey, Object valueObject) {
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		return "\"" + attrkey + "\":" + handler.toJson(valueObject, attrDataType);
	}

	@Override
	public Object serializeContentToken(AttributeDataType attrDataType, String attrkey, String[] valueContents) {
		sb.setLength(0);
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		for (String string : valueContents) {
			sb.append(handler.toJson(string) + ",");
		}
		return "\"" + attrkey + "\":[" + sb.substring(0, sb.length() - 1) + "]";
	}

	@Override
	public Object serializeObjectToken(AttributeDataType attrDataType, String attrkey, Object[] valueObjects) {
		sb.setLength(0);
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		for (Object obj : valueObjects) {
			sb.append(handler.toJson(obj, attrDataType) + ",");
		}
		return "\"" + attrkey + "\":[" + sb.substring(0, sb.length() - 1) + "]";
	}

}
