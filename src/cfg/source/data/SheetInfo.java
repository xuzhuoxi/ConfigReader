package cfg.source.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import cfg.settings.Settings;
import cfg.source.WorkbookUtil;

public class SheetInfo {
	private String sheetNamed;
	private Sheet sheet;
	private SheetDefine define;
	private List<String[]> dataList;

	public String getSheetNamed() {
		return sheetNamed;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public SheetDefine getDefine() {
		return define;
	}

	public List<String[]> getDataList() {
		return dataList;
	}

	@Override
	public String toString() {
		return "SheetInfo [sheetNamed=" + sheetNamed + ", sheet=" + sheet + ", define=" + define + ", dataList="
				+ dataList + "]";
	}

	public SheetInfo(String sheetNamed, Sheet sheet) {
		super();
		this.sheetNamed = sheetNamed;
		this.sheet = sheet;
	}

	public void handleInfo(Settings settings) {
		this.define = SheetDefine.parse(sheet, settings);
		int lastRowIndex = sheet.getLastRowNum();
		int len = define.getMaxColLength();
		String[] contents;
		this.dataList = new ArrayList<String[]>();
		for (int index = settings.getProjectSettings().getStartRowIndex(); index <= lastRowIndex; index++) {
			contents = WorkbookUtil.getContentArray(sheet, index, len);
			if (contents[0].length() == 0) {
				continue;
			}
			dataList.add(contents);
		}
	}
}
