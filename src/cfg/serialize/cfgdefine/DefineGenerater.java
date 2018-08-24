package cfg.serialize.cfgdefine;

import java.util.Date;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDefineLangType;
import cfg.settings.Settings;
import cfg.settings.lang.LangInfo;
import cfg.settings.lang.LangTempKey;
import cfg.source.data.SheetDefine;
import cfg.source.data.SheetProperty;
import code.date.DateFormats;
import code.date.DateUtil;

public class DefineGenerater implements IDefineGenerater {

	protected OutputDefineLangType lang = null;
	protected FieldRangeType fieldRange = null;;

	protected LangInfo langInfo = null;

	@Override
	public void setInfo(OutputDefineLangType lang, FieldRangeType fieldRange) {
		this.lang = lang;
		this.langInfo = Settings.getInstance().getLangSettings().getLangInfo(lang);
		this.fieldRange = fieldRange;
	}

	@Override
	public String serialize(SheetDefine sheetDefine) {
		Integer[] indexs = sheetDefine.getExportInfo(this.fieldRange).getValidIndexs();
		String mainContent = this.langInfo.getTempMainContent();
		for (Integer index : indexs) {
			SheetProperty info = sheetDefine.getPropertyDefine(langInfo.getLangNamed(), index);
			mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PROPERTY_FIELD,
					this.getPropertyFieldContent(info) + "\n" + LangTempKey.KEY_CONTENT_PROPERTY_FIELD);
			mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET,
					this.getPropertyFieldGetContent(info) + "\n" + LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET);
			mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET2,
					this.getPropertyFieldGet2Content(info) + "\n" + LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET2);
			mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PARSE_JSON,
					this.getJsonParseContent(info) + "\n" + LangTempKey.KEY_CONTENT_PARSE_JSON);
			mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PARSE_BINARY,
					this.getBinaryParseContent(info) + "\n" + LangTempKey.KEY_CONTENT_PARSE_BINARY);
		}
		mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PROPERTY_FIELD, "");
		mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET, "");
		mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET2, "");
		mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PARSE_JSON, "");
		mainContent = mainContent.replace(LangTempKey.KEY_CONTENT_PARSE_BINARY, "");
		mainContent = mainContent.replace(LangTempKey.KEY_CLASS_NAME,
				sheetDefine.getExportInfo(this.fieldRange).getClassName());
		return replaceGenInfo(mainContent);
	}

	// ------------------------------------------

	private String getPropertyFieldContent(SheetProperty info) {
		String content = this.langInfo.getTempSubContent(LangTempKey.KEY_CONTENT_PROPERTY_FIELD);
		return replacePropertyInfo(content, info);
	}

	private String getPropertyFieldGetContent(SheetProperty info) {
		String content = this.langInfo.getTempSubContent(LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET);
		return replacePropertyInfo(content, info);
	}

	private String getPropertyFieldGet2Content(SheetProperty info) {
		String content = this.langInfo.getTempSubContent(LangTempKey.KEY_CONTENT_PROPERTY_FIELD_GET2);
		return replacePropertyInfo(content, info);
	}

	private String getBinaryParseContent(SheetProperty info) {
		String content = this.langInfo.getTempSubContent(LangTempKey.KEY_CONTENT_PARSE_BINARY);
		content = this.replacePropertyInfo(content, info);
		content = content.replace(LangTempKey.KEY_LANG_FUNCTION_PARSE,
				this.langInfo.getFunctionGetDesc("binary", info.getDataFormat().getDataFormatName()));
		return content;
	}

	private String getJsonParseContent(SheetProperty info) {
		String content = this.langInfo.getTempSubContent(LangTempKey.KEY_CONTENT_PARSE_JSON);
		content = this.replacePropertyInfo(content, info);
		content = content.replace(LangTempKey.KEY_LANG_FUNCTION_PARSE,
				this.langInfo.getFunctionGetDesc("json", info.getDataFormat().getDataFormatName()));
		return content;
	}

	private String replacePropertyInfo(String content, SheetProperty info) {
		return content.replace(LangTempKey.KEY_PROPETY, info.getProperty())
				.replace(LangTempKey.KEY_PROPERTY_UP, info.getUpProperty())
				.replace(LangTempKey.KEY_PROPETY_JSON, info.getJsonProperty())
				.replace(LangTempKey.KEY_PROPERTY_NAME, info.getPropertyName())
				.replace(LangTempKey.KEY_PROPETY_REMARK, info.getPropertyRemark())
				.replace(LangTempKey.KEY_PROPETY_DATA_TYPE, info.getPropertyDataType());
	}

	private String replaceGenInfo(String content) {
		if (content == null || content.length() == 0) {
			return "";
		}
		String moduleName = "xu";
		String author = "xuzhuoxi";
		String date = DateUtil.dateToString(new Date(), DateFormats.yyyyMMdd);
		return content.replace(LangTempKey.KEY_MODULE, moduleName).replace(LangTempKey.KEY_AUTHOR, author)
				.replace(LangTempKey.KEY_DATE, date);
	}
}
