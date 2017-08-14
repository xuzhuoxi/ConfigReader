package cfg.serialize.cfgdata;

import java.nio.ByteBuffer;
import java.util.List;

import cfg.serialize.FieldDataFormat;
import cfg.serialize.FieldKey;
import cfg.serialize.FieldRangType;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;

public class BinarySheetHandler implements ISheetHandler {

	private IItemHandler binaryItemHandler = new BinaryItemHandler();
	private ByteBuffer bb = ByteBuffer.allocate(2096000);

	private SheetInfo sheetInfo;
	private SheetDefine sheetDefine;
	private FieldDataFormat[] attrDataTypes;
	private List<String[]> dataList;

	@Override
	public void config(SheetInfo sheetInfo) {
		this.sheetInfo = sheetInfo;
		this.sheetDefine = this.sheetInfo.getDefine();
		this.attrDataTypes = this.sheetDefine.getDataTypeInstances();
		this.dataList = this.sheetInfo.getDataList();
	}

	@Override
	public Object serialize(FieldRangType exportType, FieldKey attrKeyType) {
		this.start();
		Integer[] indexs = sheetDefine.getExportInfo(exportType).getValidIndexs();
		String[] attrKeys = sheetDefine.getFieldNameArray(attrKeyType.getValue());

		this.start();
		for (String[] strings : this.dataList) {
			binaryItemHandler.start();
			binaryItemHandler.append(indexs, attrDataTypes, attrKeys, strings);
			binaryItemHandler.finish();
			byte[] data = (byte[]) binaryItemHandler.getSerializedData();
			this.append(data);
		}
		this.finish();
		return this.getSerializedData();
	}

	@Override
	public void start() {
		this.bb.clear();
		return;
	}

	@Override
	public void append(Object item) {
		if (item instanceof byte[]) {
			this.bb.put((byte[]) item);
		} else if (item instanceof Byte) {
			this.bb.put((Byte) item);
		}
	}

	@Override
	public void append(Object[] items) {
		for (Object b : items) {
			this.append(b);
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
