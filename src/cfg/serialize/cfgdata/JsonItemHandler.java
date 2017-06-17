package cfg.serialize.cfgdata;

import cfg.serialize.AttributeDataType;
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
	public void append(AttributeDataType attrDataType, String attrkey, String valueContent) {
		String data = (String) tokenHandler.serializeContentToken(attrDataType, attrkey, valueContent);
		sb.append(data + ",");
	}

	@Override
	public void append(AttributeDataType attrDataType, String attrkey, Object valueObject) {
		String data = (String) tokenHandler.serializeObjectToken(attrDataType, attrkey, valueObject);
		sb.append(data + ",");
	}

	@Override
	public void append(AttributeDataType attrDataType, String attrkey, String[] valueContents) {
		String data = (String) tokenHandler.serializeContentToken(attrDataType, attrkey, valueContents);
		sb.append(data + ",");
	}

	@Override
	public void append(AttributeDataType attrDataType, String attrkey, Object[] valueObjects) {
		String data = (String) tokenHandler.serializeObjectToken(attrDataType, attrkey, valueObjects);
		sb.append(data + ",");
	}

	@Override
	public void append(Integer[] indexs, AttributeDataType[] attrDataTypes, String[] allAttrKeys, String[] allContents) {
		AttributeDataType dataType;
		IContentSerializeHandler dataHandler;
		Object obj;
		for (Integer index : indexs) {
			dataType = attrDataTypes[index];
			dataHandler = ContentSerializeHandlerMap.getShared().getHandler(dataType);
			obj = dataHandler.fromString(allContents[index], dataType);
			sb.append(tokenHandler.serializeObjectToken(dataType, allAttrKeys[index], obj) + ",");
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
