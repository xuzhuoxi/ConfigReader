package cfg.settings.lang;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import cfg.AppDefine;
import code.file.FileUtils;

public class LangInfo {
	private String langNamed;
	private Map<String, String> mapDataFormat = new HashMap<String, String>();
	private Map<String, String> mapGetFunc = new HashMap<String, String>();
	private Map<String, String> mapSetFunc = new HashMap<String, String>();
	private Map<String, String> mapTemp = new HashMap<String, String>();// 模板映射，value为对应文件的内容

	public String getLangNamed() {
		return langNamed;
	}

	public String getDataFormat(String excelDataFormat) {
		if (this.mapDataFormat.containsKey(excelDataFormat)) {
			return this.mapDataFormat.get(excelDataFormat);
		}
		return "";
	}

	/**
	 * @param param
	 *            格式如：json_boolean
	 * @return
	 */
	public String getFunctionGetDesc(String param) {
		// System.out.println(
		// "name=" + this.langNamed + ",key=" + param + ", containsKey=" +
		// this.mapGetFunc.containsKey(param));
		if (this.mapGetFunc.containsKey(param)) {
			return this.mapGetFunc.get(param);
		}
		return "";
	}

	public String getFunctionGetDesc(String fileFormat, String dataFormat) {
		return this.getFunctionGetDesc(fileFormat + "_" + dataFormat);
	}

	/**
	 * @param param
	 *            格式如：json_boolean
	 * @return
	 */
	public String getFunctionSetDesc(String param) {
		if (this.mapSetFunc.containsKey(param)) {
			return this.mapSetFunc.get(param);
		}
		return "";
	}

	public String getFunctionSetDesc(String fileFormat, String dataFormat) {
		return this.getFunctionSetDesc(fileFormat + "_" + dataFormat);
	}

	/**
	 * 取模板文件内容
	 * 
	 * @param tempKey
	 * @return
	 */
	public String getTempContent(String tempKey) {
		return this.mapTemp.get(tempKey);
	}

	@Override
	public String toString() {
		return "LangInfo [langNamed=" + langNamed + ", mapDataFormat=" + mapDataFormat + ", mapGetFunc=" + mapGetFunc
				+ ", mapSetFunc=" + mapSetFunc + "]";
	}

	public void parse(String named, JSONObject jObj) {
		this.langNamed = named;
		this.parseDataFormat(jObj.getJSONObject("DataFormat"));
		this.parseFunc(this.mapGetFunc, jObj.getJSONObject("Get"));
		this.parseTemp(this.mapTemp, jObj.getJSONObject("Temp"));
		if (jObj.has("Set")) {
			this.parseFunc(this.mapSetFunc, jObj.getJSONObject("Set"));
		}
	}

	protected void parseDataFormat(JSONObject jObj) {
		String[] keys = JSONObject.getNames(jObj);
		for (String key : keys) {
			if ("desc".equals(key)) {
				continue;
			}
			JSONArray values = jObj.getJSONArray(key);
			for (int i = 0; i < values.length(); i++) {
				String value = values.getString(i);
				mapDataFormat.put(value, key);
				// System.out.println("DataFormat: " + "key=" + value +
				// ",value=" + key);
			}
		}
	}

	protected void parseFunc(Map<String, String> map, JSONObject jObj) {
		String[] fileFormats = JSONObject.getNames(jObj);
		for (String fileFormat : fileFormats) {// 如：json
			if ("desc".equals(fileFormat)) {
				continue;
			}
			JSONObject fileFormatObj = jObj.getJSONObject(fileFormat);
			String[] excelDataFormatKeys = JSONObject.getNames(fileFormatObj);
			for (String excelDataFormatKey : excelDataFormatKeys) {// 如：getBoolean
				if ("desc".equals(excelDataFormatKey)) {
					continue;
				}
				JSONArray values = fileFormatObj.getJSONArray(excelDataFormatKey);
				for (int i = 0; i < values.length(); i++) {
					String value = values.getString(i);
					String key = fileFormat + "_" + value;
					map.put(key, excelDataFormatKey);
					// System.out.println(
					// "FuncMap[" + this.langNamed + "]: " + "key=" + key +
					// ",value=" + excelDataFormatKey);
				}
			}
		}
	}

	protected void parseTemp(Map<String, String> map, JSONObject jObj) {
		String[] keys = JSONObject.getNames(jObj);
		String basePath = AppDefine.instance.getBasePath();
		for (String key : keys) {
			if ("desc".equals(key)) {
				continue;
			}
			String filePath = basePath + "/" + jObj.getString(key);
			String content = FileUtils.readFileContent(filePath);
			map.put(key, content);
		}
	}

	public static LangInfo parseByJsonObject(String named, JSONObject jObj) {
		LangInfo rs = new LangInfo();
		rs.parse(named, jObj);
		return rs;
	}
}
