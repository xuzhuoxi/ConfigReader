package cfg.serialize.cfgdata;

import java.util.List;

import cfg.serialize.FieldDataFormat;
import cfg.serialize.FieldKey;
import cfg.serialize.FieldRangeType;
import cfg.serialize.exceptions.SheetDataException;
import cfg.settings.Settings;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;

public class JsonSheetHandler implements ISheetHandler {

	private IItemHandler jsonItemHandler = new JsonItemHandler();

	private SheetInfo sheetInfo;
	private StringBuilder sb = new StringBuilder();

	private SheetDefine sheetDefine;
	private FieldDataFormat[] attrDataTypes;
	private List<String[]> dataList;

	@Override
	public void config(SheetInfo sheetInfo) {
		this.sheetInfo = sheetInfo;
		this.sheetDefine = this.sheetInfo.getDefine();
		this.attrDataTypes = this.sheetDefine.getFieldDataFormats();
		this.dataList = this.sheetInfo.getDataList();
	}

	@Override
	public Object serialize(FieldRangeType exportType, FieldKey attrKeyType) throws SheetDataException {
		this.start();
		Integer[] indexs = sheetDefine.getExportInfo(exportType).getValidIndexs();
		String[] attrKeys = sheetDefine.getPropertyArray(attrKeyType.getValue());

		this.start();
		int i = 0;
		try {
			for (i = 0; i < this.dataList.size(); i++) {
				String[] strings = this.dataList.get(i);
				jsonItemHandler.start();
				jsonItemHandler.appends(indexs, attrDataTypes, attrKeys, strings);
				jsonItemHandler.finish();
				this.append(jsonItemHandler.getSerializedData());
			}
		} catch (SheetDataException e) {
			e.setRow(i + Settings.getInstance().getProjectSettings().getStartRowIndex());
			throw e;
		}
		this.finish();
		return this.getSerializedData();
	}

	@Override
	public void start() {
		this.sb.setLength(0);
		this.sb.append("[");
	}

	@Override
	public void append(Object item) {
		sb.append(item + ",");
	}

	@Override
	public void append(Object[] items) {
		for (Object object : items) {
			this.append(object);
		}
	}

	@Override
	public void finish() {
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
	}

	@Override
	public Object getSerializedData() {
		return sb.toString();
	}

}
