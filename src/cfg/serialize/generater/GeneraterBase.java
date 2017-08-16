package cfg.serialize.generater;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.ClassLangType;
import cfg.serialize.FieldRangType;
import cfg.settings.Settings;
import cfg.settings.lang.LangInfo;
import cfg.source.data.SheetDefine;
import code.file.FileUtils;

public class GeneraterBase {
	protected String tempUrl = null;
	protected String tempContent = null;

	protected ClassLangType lang;
	protected LangInfo langInfo;
	protected FieldRangType fieldRange;

	protected Map<String, IContentGenerater> subMap = new HashMap<String, IContentGenerater>();

	protected StringBuilder sb = new StringBuilder();

	public void setTempPath(String tempUrl) {
		this.tempUrl = tempUrl;
		this.tempContent = FileUtils.readFileContent(tempUrl, "UTF-8");
	}

	public void setLang(ClassLangType lang) {
		this.lang = lang;
		this.langInfo = Settings.getInstance().getLangSettings().getLangInfo(lang);
	}

	public void setFieldRang(FieldRangType fieldRange) {
		this.fieldRange = fieldRange;
	}

	public void setSubGenerater(String contentKey, IContentGenerater subGenerater) {
		subMap.put(contentKey, subGenerater);
	}

	protected String handleContents(String content, SheetDefine sheetDefine) {
		for (String key : this.subMap.keySet()) {
			content = this.handleContent(key, content, sheetDefine);
		}
		return content;
	}

	protected String handleContent(String contentKey, String content, SheetDefine sheetDefine) {
		if (!this.subMap.containsKey(contentKey)) {
			return content;
		}
		if (!content.contains(contentKey)) {
			return content;
		}
		return content.replace(contentKey, this.subMap.get(contentKey).serialize(sheetDefine));
	}
}
