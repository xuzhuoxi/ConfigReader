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

public class ModuleGeneraterTest {

	@Test
	public void testSerializeTS() {
		String basePath = BasePathUtils.getBasePath(this.getClass());
		String sysPath = basePath + "/system.json";
		String proPath = basePath + "/project.json";
		String langsPath = basePath + "/langs.json";
		Settings settings = Settings.parseByPath(sysPath, proPath, langsPath);
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos(settings);

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		String mPath = basePath + "/template/ts/Module.ts.temp";
		String cPath = basePath + "/template/ts/Class.ts.temp";
		String pPath = basePath + "/template/ts/Property.ts.temp";
		String pjpPath = basePath + "/template/ts/PropertyParseJson.ts.temp";
		IContentGenerater cg = LangFileGenerater.getModuleGenerater(mPath, cPath, pPath, pjpPath,
				ExportLangClassType.TypeScript.getValue());
		String value = cg.serialize(sheetDefine, ExportProjectType.Server);
		System.out.println(value);
	}

	@Test
	public void testSerializeJava() {
		String basePath = BasePathUtils.getBasePath(this.getClass());
		String sysPath = basePath + "/system.json";
		String proPath = basePath + "/project.json";
		String langsPath = basePath + "/langs.json";
		Settings settings = Settings.parseByPath(sysPath, proPath, langsPath);
		String filePath = basePath + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos(settings);

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		String mPath = basePath + "/template/java/Package.java.temp";
		String cPath = basePath + "/template/java/Class.java.temp";
		String pPath = basePath + "/template/java/Property.java.temp";
		String pjpPath = basePath + "/template/java/PropertyParseJson.java.temp";
		IContentGenerater cg = LangFileGenerater.getModuleGenerater(mPath, cPath, pPath, pjpPath,
				ExportLangClassType.Java.getValue());
		String value = cg.serialize(sheetDefine, ExportProjectType.Server);
		System.out.println(value);
	}

}
