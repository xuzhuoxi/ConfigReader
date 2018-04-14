package cfg.serialize.generater;

import java.util.List;

import org.junit.Test;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDefineLangType;
import cfg.serialize.cfgdefine.IContentGenerater;
import cfg.serialize.cfgdefine.LangFileGenerater;
import cfg.serialize.exceptions.SheetDefineException;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;
import code.path.BasePathUtils;

public class PropertyGeneraterTest {

	@Test
	public void testSerialize() throws SheetDefineException {
		String basePath = BasePathUtils.getBasePath(this.getClass());
		String filePath = basePath + "/source/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		IContentGenerater pg = LangFileGenerater.getPropertyGenerater(OutputDefineLangType.TypeScript,
				FieldRangeType.Client);
		String value = pg.serialize(sheetDefine);
		System.out.println(value);
	}

}
