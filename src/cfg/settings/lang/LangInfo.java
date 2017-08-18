package cfg.settings.lang;

import java.util.HashMap;
import java.util.Map;

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
		this.parseDataFormat(this.mapDataFormat, jObj.getJSONObject("DataFormat"));
		this.parseFunc(this.mapGetFunc, jObj.getJSONObject("Get"));
		if (jObj.has("Set")) {
			this.parseFunc(this.mapSetFunc, jObj.getJSONObject("Set"));
		}
		this.parseTemp(mapTemp, jObj.getJSONObject("Temp"));
	}

	protected void parseDataFormat(Map<String, String> map, JSONObject jObj) {
		String[] dataFormats = JSONObject.getNames(jObj);
		for (String dataFormat : dataFormats) {
			if ("desc".equals(dataFormat)) {
				continue;
			}
			map.put(dataFormat, jObj.getString(dataFormat));
		}
	}

	protected void parseFunc(Map<String, String> map, JSONObject jObj) {
		String[] fileFormats = JSONObject.getNames(jObj);
		for (String fileFormat : fileFormats) {// 如：json
			if ("desc".equals(fileFormat)) {
				continue;
			}
			JSONObject fileFormatObj = jObj.getJSONObject(fileFormat);
			String[] dataFormats = JSONObject.getNames(fileFormatObj);
			for (String dataFormat : dataFormats) {
				if ("desc".equals(dataFormat)) {
					continue;
				}
				String key = fileFormat + "_" + dataFormat;
				String value = fileFormatObj.getString(dataFormat);
				map.put(key, value);
			}
		}
	}

	protected void parseTemp(Map<String, String> map, JSONObject jObj) {
		String basePath = AppDefine.instance.getBasePath();
		String[] keys = JSONObject.getNames(jObj);
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
