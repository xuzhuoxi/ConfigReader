package cfg.settings;

import org.json.JSONArray;
import org.json.JSONObject;

import code.file.FileUtils;

public class LangSettings {

	private JSONObject jsonData = null;

	public String[] getFunctionDesc(String param) {
		String[] params = param.split(".");
		return this.getFunctionDesc(params[0], params[1], params[2]);
	}

	public String[] getFunctionDesc(String fileFormat, String lang, String dataFormat) {
		try {
			JSONArray ary = this.jsonData.getJSONObject(fileFormat).getJSONObject(lang).getJSONArray(dataFormat);
			return new String[] { ary.getString(0), ary.getString(1) };
		} catch (Exception e) {
			return null;
		}
	}

	public String getFunctionGetDesc(String param) {
		String[] params = param.split(".");
		return this.getFunctionGetDesc(params[0], params[1], params[2]);
	}

	public String getFunctionGetDesc(String fileFormat, String lang, String dataFormat) {
		String[] funs = this.getFunctionDesc(fileFormat, lang, dataFormat);
		if (null == funs) {
			return null;
		} else {
			return funs[0];
		}
	}

	public String getFunctionSetDesc(String param) {
		String[] params = param.split(".");
		return this.getFunctionSetDesc(params[0], params[1], params[2]);
	}

	public String getFunctionSetDesc(String fileFormat, String lang, String dataFormat) {
		String[] funs = this.getFunctionDesc(fileFormat, lang, dataFormat);
		if (null == funs) {
			return null;
		} else {
			return funs[1];
		}
	}

	public static final LangSettings parseByPath(String filePath) {
		String jsonContent = FileUtils.readFileContent(filePath);
		return parseByJson(jsonContent);
	}

	public static final LangSettings parseByJson(String json) {
		LangSettings settings = new LangSettings();

		JSONObject jsonObj = new JSONObject(json);
		settings.jsonData = jsonObj;
		return settings;
	}
}
