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
		this.handlerMap.put(FieldDataFormat.Boolean.getDataFormatName(), new BooleanContentHandler());
		this.handlerMap.put(FieldDataFormat.UInt8.getDataFormatName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.UInt16.getDataFormatName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.UInt32.getDataFormatName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Int8.getDataFormatName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Int16.getDataFormatName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Int32.getDataFormatName(), new IntegerContentHandler());
		this.handlerMap.put(FieldDataFormat.Float32.getDataFormatName(), new DecimalContentHandler());
		this.handlerMap.put(FieldDataFormat.Json.getDataFormatName(), new JsonContentHandler());
		this.handlerMap.put(FieldDataFormat.String.getDataFormatName(), new StringContentHandler());

		this.handlerMap.put(FieldDataFormat.A_Boolean.getDataFormatName(),
				new ArrayContentHandler(new BooleanContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_UInt8.getDataFormatName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_UInt16.getDataFormatName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_UInt32.getDataFormatName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Int8.getDataFormatName(), new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Int16.getDataFormatName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Int32.getDataFormatName(),
				new ArrayContentHandler(new IntegerContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Float32.getDataFormatName(),
				new ArrayContentHandler(new DecimalContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_Json.getDataFormatName(), new ArrayContentHandler(new JsonContentHandler()));
		this.handlerMap.put(FieldDataFormat.A_String.getDataFormatName(),
				new ArrayContentHandler(new StringContentHandler()));
	}

	public IContentSerializeHandler getHandler(FieldDataFormat dataType) {
		return this.handlerMap.get(dataType.getDataFormatName());
	}

	public IContentSerializeHandler getHander(String dataTypeNamed) {
		return this.handlerMap.get(dataTypeNamed);
	}
}
