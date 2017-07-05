package cfg.serialize.generater;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.ExportProjectType;
import cfg.serialize.lang.ILangConversion;
import cfg.serialize.lang.LangConversionFactory;
import cfg.source.data.SheetDefine;
import code.file.FileUtils;

public class GeneraterBase {
	protected String tempUrl = null;
	protected String tempContent = null;

	protected String lang;
	protected ILangConversion langConversion;

	protected Map<String, IContentGenerater> subMap = new HashMap<String, IContentGenerater>();

	protected StringBuilder sb = new StringBuilder();

	public void setTempPath(String tempUrl) {
		this.tempUrl = tempUrl;
		this.tempContent = FileUtils.readFileContent(tempUrl, "UTF-8");
	}

	public void setLang(String lang) {
		this.lang = lang;
		this.langConversion = LangConversionFactory.getShared().getConversion(lang);
	}

	public void setSubGenerater(String contentKey, IContentGenerater subGenerater) {
		subMap.put(contentKey, subGenerater);
	}

	protected String handleContents(String content, SheetDefine sheetDefine, ExportProjectType projectType) {
		for (String key : this.subMap.keySet()) {
			content = this.handleContent(key, content, sheetDefine, projectType);
		}
		return content;
	}

	protected String handleContent(String contentKey, String content, SheetDefine sheetDefine,
			ExportProjectType projectType) {
		if (!this.subMap.containsKey(contentKey)) {
			return content;
		}
		if (!content.contains(contentKey)) {
			return content;
		}
		return content.replace(contentKey, this.subMap.get(contentKey).serialize(sheetDefine, projectType));
	}
}
