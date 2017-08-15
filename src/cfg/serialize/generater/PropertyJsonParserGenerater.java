package cfg.serialize.generater;

import cfg.settings.Settings;
import cfg.source.data.SheetDefine;

/**
 * 
 * @author Administrator<br>
 *         支持的Key:<br>
 *         TempKey.KEY_PROPETY、 TempKey.KEY_PROPETY_JSON、
 *         TempKey.KEY_LANG_FUNCTION_PARSE
 */
public class PropertyJsonParserGenerater extends GeneraterBase implements IContentGenerater {

	@Override
	public String serialize(SheetDefine sheetDefine) {
		this.sb.setLength(0);
		// System.out.println("PropertyJsonParserGenerater: " +
		// this.tempContent);
		Integer[] indexs = sheetDefine.getExportInfo(this.fieldRange).getValidIndexs();
		Settings settings = Settings.getInstance();
		String langStr = this.lang.getValue();
		for (Integer index : indexs) {
			String property = sheetDefine.getFieldName(this.lang.getValue(), index);
			String jsonProperty = sheetDefine.getFieldName("json", index);
			String dataFormat = sheetDefine.getDataTypeInstance(index).getTypeName();
			String funcParse = settings.getLangSettings().getFunctionGetDesc(langStr, "json", dataFormat);
			this.sb.append(this.tempContent.replace(TempKey.KEY_PROPETY, property)
					.replace(TempKey.KEY_PROPETY_JSON, jsonProperty)
					.replace(TempKey.KEY_LANG_FUNCTION_PARSE, funcParse));
		}
		return this.sb.toString();
	}
}
