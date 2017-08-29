package cfg.serialize.generater;

import java.util.List;

import org.junit.Test;

import cfg.serialize.OutputDefineLangType;
import cfg.serialize.cfgdefine.IContentGenerater;
import cfg.serialize.cfgdefine.LangFileGenerater;
import cfg.serialize.FieldRangeType;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetInfo;
import code.path.BasePathUtils;

public class ClassGeneraterTest {

	@Test
	public void testSerialize() {
		String filePath = BasePathUtils.getBasePath(this.getClass()) + "/configs/cfg_building.xls";
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();

		List<SheetInfo> sheets = info.getSheetInfos();
		SheetInfo sheetInfo = sheets.get(0);
		SheetDefine sheetDefine = sheetInfo.getDefine();

		IContentGenerater cg = LangFileGenerater.getClassGenerater(OutputDefineLangType.TypeScript, FieldRangeType.Client);
		String value = cg.serialize(sheetDefine);
		System.out.println(value);
	}

}
