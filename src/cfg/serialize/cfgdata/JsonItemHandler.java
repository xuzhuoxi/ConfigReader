package cfg.serialize.cfgdata;

import cfg.serialize.FieldDataFormat;
import cfg.serialize.cfgcontent.ContentSerializeHandlerMap;
import cfg.serialize.cfgcontent.IContentSerializeHandler;

public class JsonItemHandler implements IItemHandler {

	private StringBuilder sb = new StringBuilder();
	private ITokenHandler tokenHandler = new JsonTokenHandler();

	@Override
	public void start() {
		sb.setLength(0);
		sb.append("{");
	}

	@Override
	public void append(FieldDataFormat attrDataType, String attrKey, String valueContent) {
		String data = (String) tokenHandler.serializeContentToken(attrDataType, attrKey, valueContent);
		sb.append(data + ",");
	}

	@Override
	public void append(FieldDataFormat attrDataType, String attrKey, Object valueObject) {
		String data = (String) tokenHandler.serializeObjectToken(attrDataType, attrKey, valueObject);
		sb.append(data + ",");
	}

	@Override
	public void append(FieldDataFormat attrDataType, String attrKey, String[] valueContents) {
		String data = (String) tokenHandler.serializeContentToken(attrDataType, attrKey, valueContents);
		sb.append(data + ",");
	}

	@Override
	public void append(FieldDataFormat attrDataType, String attrKey, Object[] valueObjects) {
		String data = (String) tokenHandler.serializeObjectToken(attrDataType, attrKey, valueObjects);
		sb.append(data + ",");
	}

	@Override
	public void append(Integer[] indexs, FieldDataFormat[] attrDataTypes, String[] attrKeys, String[] allContents) {
		FieldDataFormat dataType;
		IContentSerializeHandler dataHandler;
		Object obj;
		for (Integer index : indexs) {
			dataType = attrDataTypes[index];
			dataHandler = ContentSerializeHandlerMap.getShared().getHandler(dataType);
			obj = dataHandler.fromString(allContents[index], dataType);
			sb.append(tokenHandler.serializeObjectToken(dataType, attrKeys[index], obj) + ",");
		}
	}

	@Override
	public void finish() {
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
	}

	@Override
	public Object getSerializedData() {
		return sb.toString();
	}
}
