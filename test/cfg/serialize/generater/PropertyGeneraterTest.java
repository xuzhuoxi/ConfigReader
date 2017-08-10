package cfg.serialize.generater;

import java.util.List;

import org.junit.Test;

import cfg.serialize.ExportLangClassType;
import cfg.serialize.ExportProjectType;
import cfg.settings.Settings;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;
import code.path.BasePathUtils;

public class PropertyGeneraterTest {

	@Test
	public void testSerialize() {
		String basePath = BasePathUtils.getBasePath(this.getClass());
		String sysPath = basePath + "/system.json";
		String proPath = basePath + "/project.json";
		String langsPath = basePath + "/langs.json";
		Settings settings = Settings.parseByPath(sysPath, proPath, langsPath);
		String filePath = BasePathUtils.getBasePath(this.getClass()) + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos(settings);

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		PropertyGenerater pg = new PropertyGenerater();
		pg.setLang(ExportLangClassType.TypeScript.getValue());
		String tempPath = "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/template/ts/Property.ts.temp";
		pg.setTempPath(tempPath);

		String value = pg.serialize(sheetDefine, ExportProjectType.Client);
		System.out.println(value);
	}

}
