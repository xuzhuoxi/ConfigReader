package cfg.serialize.generater;

import java.util.List;

import org.junit.Test;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDefineLangType;
import cfg.serialize.cfgdefine.DefineGenerater;
import cfg.serialize.exceptions.SheetDefineException;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;
import code.path.BasePathUtils;

public class DefineGeneraterTest {

	@Test
	public void testSerialize() throws SheetDefineException {
		String filePath = BasePathUtils.getBasePath(this.getClass()) + "/source/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		DefineGenerater dg = new DefineGenerater();
		dg.setInfo(OutputDefineLangType.TypeScript, FieldRangeType.Client);
		String value = dg.serialize(sheetDefine);

		System.out.println(value);
	}

}
