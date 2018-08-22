package cfg.serialize.cfgcontent;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.FieldDataFormat;

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
		this.handlerMap.put(FieldDataFormat.Boolean.getTypeName(), new BooleanContentHandler());
		this.handlerMap.put(FieldDataFormat.UInt8.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.UInt16.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.UInt32.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Int8.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Int16.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Int32.getTypeName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Float32.getTypeName(), new DecimalContentHandler());
		this.handlerMap.put(FieldDataFormat.Json.getTypeName(), new JsonContentHandler());
		this.handlerMap.put(FieldDataFormat.String.getTypeName(), new StringContentHandler());

		this.handlerMap.put(FieldDataFormat.A_Boolean.getTypeName(),
				new ArrayContentHandler(new BooleanContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_UInt8.getTypeName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_UInt16.getTypeName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_UInt32.getTypeName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Int8.getTypeName(), new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Int16.getTypeName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Int32.getTypeName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Float32.getTypeName(),
				new ArrayContentHandler(new DecimalContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Json.getTypeName(), new ArrayContentHandler(new JsonContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_String.getTypeName(),
				new ArrayContentHandler(new StringContentHandler()));
	}

	public IContentSerializeHandler getHandler(FieldDataFormat dataType) {
		return this.handlerMap.get(dataType.getTypeName());
	}

	public IContentSerializeHandler getHander(String dataTypeNamed) {
		return this.handlerMap.get(dataTypeNamed);
	}
}
