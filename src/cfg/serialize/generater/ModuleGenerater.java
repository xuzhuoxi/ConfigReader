package cfg.serialize.generater;

import cfg.source.data.SheetDefine;

public class ModuleGenerater extends GeneraterBase implements IContentGenerater {

	public ModuleGenerater() {
		super(TempKey.KEY_CONTENT_PACKAGE);
	}

	@Override
	public String serialize(SheetDefine sheetDefine) {
		String rs = this.tempContent.replace(TempKey.KEY_MODULE, "xu");
		rs = this.handleContents(rs, sheetDefine);
		return rs;
	}
}
