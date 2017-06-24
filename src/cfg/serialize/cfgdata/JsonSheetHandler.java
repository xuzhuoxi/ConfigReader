package cfg.serialize.cfgdata;

import java.util.List;

import cfg.serialize.AttributeDataType;
import cfg.serialize.ExportProjectType;
import cfg.serialize.AttributeKeyType;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;

public class JsonSheetHandler implements ISheetHandler {

	private IItemHandler jsonItemHandler = new JsonItemHandler();

	private SheetInfo sheetInfo;
	private StringBuilder sb = new StringBuilder();

	private SheetDefine sheetDefine;
	private AttributeDataType[] attrDataTypes;
	private List<String[]> dataList;

	@Override
	public void config(SheetInfo sheetInfo) {
		this.sheetInfo = sheetInfo;
		this.sheetDefine = this.sheetInfo.getDefine();
		this.attrDataTypes = this.sheetDefine.getDataTypeInstances();
		this.dataList = this.sheetInfo.getDataList();
	}

	@Override
	public Object serialize(ExportProjectType exprotType, AttributeKeyType attrKeyType) {
		this.start();
		Integer[] indexs = sheetDefine.getExportInfo(exprotType).getValidIndexs();
		String[] attrKeys = sheetDefine.getFieldNameArray(attrKeyType.getValue());

		this.start();
		for (String[] strings : this.dataList) {
			jsonItemHandler.start();
			jsonItemHandler.append(indexs, attrDataTypes, attrKeys, strings);
			jsonItemHandler.finish();
			this.append(jsonItemHandler.getSerializedData());
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
