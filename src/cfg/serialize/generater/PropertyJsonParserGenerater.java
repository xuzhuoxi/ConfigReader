package cfg.serialize.generater;

import cfg.serialize.ExportProjectType;
import cfg.source.data.SheetDefine;

public class PropertyJsonParserGenerater extends GeneraterBase implements IContentGenerater {

	@Override
	public String serialize(SheetDefine sheetDefine, ExportProjectType projectType) {
		this.sb.setLength(0);
//		System.out.println("PropertyJsonParserGenerater: " + this.tempContent);
		Integer[] indexs = sheetDefine.getExportInfo(projectType).getValidIndexs();
		for (Integer index : indexs) {
			String property = sheetDefine.getFieldName(this.lang, index);
			String jsonProperty = sheetDefine.getFieldName("json", index);

			this.sb.append(this.tempContent.replace(TempKey.KEY_PROPETY, property).replace(TempKey.KEY_PROPETY_JSON,
					jsonProperty));
		}
		return this.sb.toString();
	}
}
