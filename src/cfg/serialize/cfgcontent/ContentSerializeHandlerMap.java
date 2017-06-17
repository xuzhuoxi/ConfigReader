package cfg.serialize.cfgcontent;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.AttributeDataType;

public class ContentSerializeHandlerMap {
	private static ContentSerializeHandlerMap shared = null;

	public static ContentSerializeHandlerMap getShared() {
		if (null == shared) {
			shared = new ContentSerializeHandlerMap();
		}
		return shared;
	}

	private Map<String, IContentSerializeHandler> handlerMap = null;

	private ContentSerializeHandlerMap() {
		this.handlerMap = new HashMap<String, IContentSerializeHandler>();
		this.handlerMap.put(AttributeDataType.Boolean.getTypeName(), new BooleanContentHandler());
		this.handlerMap.put(AttributeDataType.UInt8.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(AttributeDataType.UInt16.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(AttributeDataType.UInt32.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(AttributeDataType.Int8.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(AttributeDataType.Int16.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(AttributeDataType.Int32.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(AttributeDataType.String.getTypeName(), new StringContentHandler());
	}

	public IContentSerializeHandler getHandler(AttributeDataType dataType) {
		return this.handlerMap.get(dataType.getTypeName());
	}

	public IContentSerializeHandler getHander(String dataTypeNamed) {
		return this.handlerMap.get(dataTypeNamed);
	}
}
