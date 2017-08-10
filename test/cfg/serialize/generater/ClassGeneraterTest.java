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

public class ClassGeneraterTest {

	@Test
	public void testSerialize() {
		String sysPath = BasePathUtils.getBasePath(this.getClass()) + "/system.json";
		String proPath = BasePathUtils.getBasePath(this.getClass()) + "/project.json";
		String langsPath = BasePathUtils.getBasePath(this.getClass()) + "/langs.json";
		Settings settings = Settings.parseByPath(sysPath, proPath, langsPath);
		String filePath = BasePathUtils.getBasePath(this.getClass()) + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos(settings);

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		String cPath = "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/template/ts/Class.ts.temp";
		String pPath = "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/template/ts/Property.ts.temp";
		String pjpPath = "E:/eclipseWorkspaces/Eclipse_ConfigReader/res/template/ts/PropertyParseJson.ts.temp";
		IContentGenerater cg = LangFileGenerater.getClassGenerater(cPath, pPath, pjpPath,
				ExportLangClassType.TypeScript.getValue());
		String value = cg.serialize(sheetDefine, ExportProjectType.Client);
		System.out.println(value);
	}

}
