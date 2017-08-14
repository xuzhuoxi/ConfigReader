package cfg.source;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cfg.settings.Settings;
import cfg.source.data.SheetInfo;
import code.file.FileUtils;

public class WorkbookInfo {
	private String filePath;

	private List<SheetInfo> sheetInfos;

	public String getFilePath() {
		return filePath;
	}

	public List<SheetInfo> getSheetInfos() {
		return sheetInfos;
	}

	public WorkbookInfo(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void loadSheetInfos() {
		this.sheetInfos = new ArrayList<SheetInfo>();
		if (FileUtils.isFolder(filePath)) {
			loadWorkbooks();
		} else {
			Workbook workbook = WorkbookUtil.getWorkbook(filePath);
			loadSheets(workbook);
		}
	}

	/**
	 * 加载一个目录下全部Excel文件中的全部符合规范的Sheet
	 * 
	 * @param settings
	 */
	private void loadWorkbooks() {
		File[] files = FileUtils.getFiles(filePath, new String[] { "xls", "xlsx" }, true);
		Workbook workbook;
		for (File file : files) {
			workbook = WorkbookUtil.getWorkbook(file.getAbsolutePath());
			loadSheets(workbook);
		}
	}

	/**
	 * 加载一个Workbook中的全部符合规范的Sheet
	 * 
	 * @param workbook
	 * @param settings
	 */
	private void loadSheets(Workbook workbook) {
		if (null != workbook) {
			int sheetNum = workbook.getNumberOfSheets();
			Settings settings = Settings.getInstance();
			String sheetPrefix = settings.getSysSettings().getSheetPrefix();
			String sheetName;
			for (int i = 0; i < sheetNum; i++) {
				sheetName = workbook.getSheetName(i).toLowerCase();
				if (sheetName.indexOf(sheetPrefix) != 0) {
					continue;
				}
				Sheet sheet = workbook.getSheetAt(i);
				SheetInfo sheetInfo = new SheetInfo(sheetName, sheet);
				sheetInfo.handleInfo();
				this.sheetInfos.add(sheetInfo);
			}
		}
	}
}
