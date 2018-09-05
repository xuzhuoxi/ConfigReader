package cfg.serialize.cfgdata;

import java.nio.ByteBuffer;

import cfg.serialize.FieldDataFormat;
import cfg.serialize.cfgcontent.ContentSerializeHandlerMap;
import cfg.serialize.cfgcontent.IContentSerializeHandler;
import cfg.serialize.exceptions.SheetDataException;
import cfg.settings.Settings;
import cfg.settings.sys.BuffSetting;

public class BinaryItemHandler implements IItemHandler {

	private ByteBuffer bb = null;
	private ITokenHandler tokenHandler = new BinaryTokenHandler();

	public BinaryItemHandler() {
		super();
		BuffSetting bs = Settings.getInstance().getSysSettings().getBuffSettings();
		this.bb = ByteBuffer.allocate(bs.getItemBuffLen());
		this.bb.order(bs.byteOrder());
	}

	@Override
	public void start() {
		this.bb.clear();
	}

	@Override
	public void append(FieldDataFormat attrDataType, String attrKey, String valueContent) {
		byte[] data = (byte[]) this.tokenHandler.serializeContentToken(attrDataType, attrKey, valueContent);
		this.bb.put(data);
	}

	@Override
	public void append(FieldDataFormat attrDataType, String attrKey, Object valueObject) {
		byte[] data = (byte[]) this.tokenHandler.serializeObjectToken(attrDataType, attrKey, valueObject);
		this.bb.put(data);
	}

	@Override
	public void appends(FieldDataFormat attrDataType, String attrKey, String[] valueContents) {
		byte[] data = (byte[]) this.tokenHandler.serializeContentToken(attrDataType, attrKey, valueContents);
		this.bb.put(data);
	}

	@Override
	public void appends(FieldDataFormat attrDataType, String attrKey, Object[] valueObjects) {
		byte[] data = (byte[]) this.tokenHandler.serializeObjectToken(attrDataType, attrKey, valueObjects);
		this.bb.put(data);
	}

	@Override
	public void appends(Integer[] indexs, FieldDataFormat[] attrDataTypes, String[] attrKeys, String[] allContents)
			throws SheetDataException {
		FieldDataFormat dataType;
		IContentSerializeHandler dataHandler;
		Object obj;
		for (Integer index : indexs) {
			dataType = attrDataTypes[index];
			dataHandler = ContentSerializeHandlerMap.getShared().getHandler(dataType);
			try {
				obj = dataHandler.fromString(allContents[index], dataType);
			} catch (Exception e) {
				SheetDataException sde = new SheetDataException("FromString Error!");
				sde.setCol(index);
				throw sde;
			}
			byte[] tokenData = (byte[]) tokenHandler.serializeObjectToken(dataType, null, obj);
			this.bb.put(tokenData);
		}
	}

	@Override
	public void finish() {
		this.bb.flip();
	}

	@Override
	public Object getSerializedData() {
		byte[] rs = new byte[this.bb.limit()];
		this.bb.get(rs);
		return rs;
	}
}
