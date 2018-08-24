package cfg.settings;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cfg.serialize.OutputDefineLangType;
import cfg.settings.lang.LangInfo;
import code.file.FileUtils;

public class LangSettings {

	private Map<String, LangInfo> langMap = new HashMap<String, LangInfo>();

	public LangInfo getLangInfo(OutputDefineLangType lang) {
		return this.langMap.get(lang.getValue());
	}

	public LangInfo getLangInfo(String lang) {
		return this.langMap.get(lang);
	}

	public String getLangDataType(String lang, String dataFormatName) {
		return this.langMap.get(lang).getLangDataType(dataFormatName);
	}

	public String getFunctionGetDesc(String lang, String param) {
		return this.langMap.get(lang).getFunctionGetDesc(param);
	}

	public String getFunctionGetDesc(String lang, String fileFormat, String dataFormatName) {
		return this.langMap.get(lang).getFunctionGetDesc(fileFormat, dataFormatName);
	}

	public String getFunctionSetDesc(String lang, String param) {
		return this.langMap.get(lang).getFunctionSetDesc(param);
	}

	public String getFunctionSetDesc(String lang, String fileFormat, String dataFormatName) {
		return this.langMap.get(lang).getFunctionSetDesc(fileFormat, dataFormatName);
	}

	public final void appendLangByPath(String lang, String langSettingFilePath) {
		String jsonContent = FileUtils.readFileContent(langSettingFilePath);
		appendLangByJson(lang, jsonContent);
	}

	public final void appendLangByJson(String lang, String langSettingJosn) {
		JSONObject jsonObj = new JSONObject(langSettingJosn);
		LangInfo li = LangInfo.parseByJsonObject(lang, jsonObj);
		this.langMap.put(lang, li);
	}

	@Override
	public String toString() {
		return "LangSettings [langMap=" + langMap + "]";
	}
}
