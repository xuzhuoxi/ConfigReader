package cfg.serialize.cfgdefine;

import java.util.Date;

import cfg.source.data.SheetDefine;
import code.date.DateFormats;
import code.date.DateUtil;

public class ClassGenerater extends GeneraterBase implements IContentGenerater {

	public ClassGenerater() {
		super(TempKey.KEY_CONTENT_CLASS);
	}

	@Override
	public String serialize(SheetDefine sheetDefine) {
		String author = "xuzhuoxi";
		String date = DateUtil.dateToString(new Date(), DateFormats.yyyyMMdd);
		String rs = this.tempContent.replace(TempKey.KEY_CLASS_NAME,
				sheetDefine.getExportInfo(this.fieldRange).getClassName());
		rs = rs.replace(TempKey.KEY_DATE, date);
		rs = rs.replace(TempKey.KEY_AUTHOR, author);
		rs = this.handleContents(rs, sheetDefine);
		return rs;
	}

}
