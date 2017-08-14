package cfg.serialize.cfgdata;

import java.nio.ByteBuffer;

import cfg.serialize.FieldDataFormat;
import cfg.serialize.cfgcontent.ContentSerializeHandlerMap;
import cfg.serialize.cfgcontent.IContentSerializeHandler;

public class BinaryTokenHandler implements ITokenHandler {

	private ByteBuffer bb = ByteBuffer.allocate(512);

	@Override
	public Object serializeContentToken(FieldDataFormat attrDataType, String attrKey, String valueContent) {
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		return handler.toBinary(valueContent, attrDataType);
	}

	@Override
	public Object serializeObjectToken(FieldDataFormat attrDataType, String attrKey, Object valueObject) {
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		return handler.toBinary(valueObject, attrDataType);
	}

	@Override
	public Object serializeContentToken(FieldDataFormat attrDataType, String attrKey, String[] valueContents) {
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		bb.clear();
		for (String string : valueContents) {
			byte[] bs = handler.toBinary(string, attrDataType);
			bb.put(bs);
		}
		bb.flip();
		byte[] rs = new byte[bb.limit()];
		bb.get(rs);
		return rs;
	}

	@Override
	public Object serializeObjectToken(FieldDataFormat attrDataType, String attrKey, Object[] valueObjects) {
		IContentSerializeHandler handler = ContentSerializeHandlerMap.getShared().getHandler(attrDataType);
		bb.clear();
		for (Object obj : valueObjects) {
			byte[] bs = handler.toBinary(obj, attrDataType);
			bb.put(bs);
		}
		bb.flip();
		byte[] rs = new byte[bb.limit()];
		bb.get(rs);
		return rs;
	}
}
