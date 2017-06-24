package cfg.serialize.generater;

import java.util.Date;

import cfg.serialize.ExportProjectType;
import cfg.source.data.SheetDefine;
import code.date.DateFormats;
import code.date.DateUtil;

public class ClassGenerater extends GeneraterBase implements IContentGenerater {

	@Override
	public String serialize(SheetDefine sheetDefine, ExportProjectType projectType) {
		String subContent = null == subGenerater ? "" : subGenerater.serialize(sheetDefine, projectType);
		String author = "xuzhuoxi";
		String date = DateUtil.dateToString(new Date(), DateFormats.yyyyMMdd);
		String rs = this.tempContent.replace(TempKey.KEY_CLASS_NAME,
				sheetDefine.getExportInfo(projectType).getClassName());
		rs = rs.replace(TempKey.KEY_DATE, date);
		rs = rs.replace(TempKey.KEY_AUTHOR, author);
		rs = rs.replace(TempKey.KEY_PROPERTY_CONTENT, subContent);
		return rs;
	}

}
