package cfg.settings;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cfg.settings.lang.LangInfo;
import code.file.FileUtils;

public class LangSettings {

	private Map<String, LangInfo> langMap = new HashMap<String, LangInfo>();

	public String getDataFormat(String lang, String excelDataFormat) {
		return this.langMap.get(lang).getDataFormat(excelDataFormat);
	}

	public String getFunctionGetDesc(String lang, String param) {
		return this.langMap.get(lang).getFunctionGetDesc(param);
	}

	public String getFunctionGetDesc(String lang, String fileFormat, String dataFormat) {
		return this.langMap.get(lang).getFunctionGetDesc(fileFormat, dataFormat);
	}

	public String getFunctionSetDesc(String lang, String param) {
		return this.langMap.get(lang).getFunctionSetDesc(param);
	}

	public String getFunctionSetDesc(String lang, String fileFormat, String dataFormat) {
		return this.langMap.get(lang).getFunctionSetDesc(fileFormat, dataFormat);
	}

	public static final LangSettings parseByPath(String filePath) {
		String jsonContent = FileUtils.readFileContent(filePath);
		return parseByJson(jsonContent);
	}

	public static final LangSettings parseByJson(String json) {
		LangSettings settings = new LangSettings();

		JSONObject jsonObj = new JSONObject(json);
		settings.langMap.put("java", LangInfo.parseByJsonObject("java", jsonObj.getJSONObject("java")));
		settings.langMap.put("ts", LangInfo.parseByJsonObject("ts", jsonObj.getJSONObject("ts")));
		return settings;
	}
}
