package cfg.serialize.generater;

import cfg.serialize.ExportProjectType;
import cfg.source.data.SheetDefine;

public class ModuleGenerater extends GeneraterBase implements IContentGenerater {

	@Override
	public String serialize(SheetDefine sheetDefine, ExportProjectType projectType) {
		String rs = this.tempContent.replace(TempKey.KEY_MODULE, "xu");
		rs = this.handleContents(rs, sheetDefine, projectType);
		return rs;
	}

}
