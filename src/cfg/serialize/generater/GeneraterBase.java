package cfg.serialize.generater;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.ClassLangType;
import cfg.serialize.FieldRangeType;
import cfg.settings.Settings;
import cfg.settings.lang.LangInfo;
import cfg.source.data.SheetDefine;

public class GeneraterBase {
	protected ClassLangType lang = null;
	protected LangInfo langInfo = null;
	protected FieldRangeType fieldRange = null;;
	protected String tempKey = null;
	protected String tempContent = null;

	protected Map<String, IContentGenerater> subMap = new HashMap<String, IContentGenerater>();

	protected StringBuilder sb = new StringBuilder();

	public void setInfo(ClassLangType lang, FieldRangeType fieldRange, String tempKey) {
		this.lang = lang;
		this.langInfo = Settings.getInstance().getLangSettings().getLangInfo(lang);
		this.fieldRange = fieldRange;
		this.tempKey = tempKey;
		this.tempContent = this.langInfo.getTempContent(this.tempKey);
	}

	public void setSubGenerater(String contentKey, IContentGenerater subGenerater) {
		subMap.put(contentKey, subGenerater);
	}

	@Override
	public String toString() {
		return "GeneraterBase [lang=" + lang + ", langInfo=" + langInfo + ", fieldRange=" + fieldRange + ", tempKey="
				+ tempKey + ", tempContent=" + tempContent + "]";
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
