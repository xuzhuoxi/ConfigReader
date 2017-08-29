package cfg.serialize.cfgdefine;

import cfg.settings.Settings;
import cfg.source.data.SheetDefine;

/**
 * 
 * @author Administrator<br>
 *         支持的Key:<br>
 *         TempKey.KEY_PROPETY、 TempKey.KEY_PROPETY_JSON、
 *         TempKey.KEY_LANG_FUNCTION_PARSE
 */
public class PropertyGeneraterBinaryParser extends GeneraterBase implements IContentGenerater {

	public PropertyGeneraterBinaryParser() {
		super(TempKey.KEY_CONTENT_PARSE_BINARY);
	}

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
			String dataFormat = sheetDefine.getDataTypeInstance(index).getTypeName();
			String funcParse = settings.getLangSettings().getFunctionGetDesc(langStr, "binary", dataFormat);
//			System.out.println(TempKey.KEY_LANG_FUNCTION_PARSE + "，" + funcParse);
			this.sb.append(this.tempContent.replace(TempKey.KEY_PROPETY, property)
					.replace(TempKey.KEY_LANG_FUNCTION_PARSE, funcParse));
		}
		return this.sb.toString();
	}
}
