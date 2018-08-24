package cfg.settings.lang;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cfg.AppDefine;
import code.file.FileUtils;

public class LangInfo {
	private String langNamed;
	private Map<String, String> dataFormatToLangDataType = new HashMap<String, String>();
	private Map<String, String> dataFormatToGetFunc = new HashMap<String, String>();
	private Map<String, String> dataFormatToSetFunc = new HashMap<String, String>();

	private LangTempInfo tempInfo = new LangTempInfo();

	/**
	 * 编程语言
	 * 
	 * @return 编程语言的字符串值
	 */
	public String getLangNamed() {
		return langNamed;
	}

	public String getLangDataType(String dataFormatName) {
		if (this.dataFormatToLangDataType.containsKey(dataFormatName)) {
			return this.dataFormatToLangDataType.get(dataFormatName);
		}
		return "";
	}

	/**
	 * 取字段属性的读取(解释)方法名
	 * 
	 * @param param
	 *            格式如：json_boolean
	 * @return 字段属性的读取(解释)方法名
	 */
	public String getFunctionGetDesc(String param) {
		if (this.dataFormatToGetFunc.containsKey(param)) {
			return this.dataFormatToGetFunc.get(param);
		}
		return "";
	}

	/**
	 * 取字段属性的读取(解释)方法名
	 * 
	 * @param fileFormat
	 *            数据文件格式
	 * @param dataFormatName
	 *            字段数据类型
	 * @return 字段属性的读取(解释)方法名
	 */
	public String getFunctionGetDesc(String fileFormat, String dataFormatName) {
		return this.getFunctionGetDesc(fileFormat + "_" + dataFormatName);
	}

	/**
	 * 取字段属性的写入(设置)方法名
	 * 
	 * @param param
	 *            格式如：json_boolean
	 * @return 字段属性的写入(设置)方法名
	 */
	public String getFunctionSetDesc(String param) {
		if (this.dataFormatToSetFunc.containsKey(param)) {
			return this.dataFormatToSetFunc.get(param);
		}
		return "";
	}

	/**
	 * 取字段属性的写入(设置)方法名
	 * 
	 * @param fileFormat
	 *            数据文件格式
	 * @param dataFormatName
	 *            字段数据类型
	 * @return 字段属性的写入(设置)方法名
	 */
	public String getFunctionSetDesc(String fileFormat, String dataFormatName) {
		return this.getFunctionSetDesc(fileFormat + "_" + dataFormatName);
	}

	/**
	 * 取模板文件内容
	 * 
	 * @return 模板的文本内容
	 */
	public String getTempMainContent() {
		return this.tempInfo.getMainContent();
	}

	/**
	 * 取子模板文件内容
	 * 
	 * @param subKey
	 *            子模板键
	 * @return 模板的文本内容
	 */
	public String getTempSubContent(String subKey) {
		return this.tempInfo.getSubContent(subKey);
	}

	@Override
	public String toString() {
		return "LangInfo [langNamed=" + langNamed + ", mapDataFormat=" + dataFormatToLangDataType + ", mapGetFunc=" + dataFormatToGetFunc
				+ ", mapSetFunc=" + dataFormatToSetFunc + ", tempInfo=" + tempInfo + "]";
	}

	public void parse(String named, JSONObject jo) {
		this.langNamed = named;
		this.parseDataFormat(this.dataFormatToLangDataType, jo.getJSONObject("DataFormat"));
		this.parseFunc(this.dataFormatToGetFunc, jo.getJSONObject("Get"));
		if (jo.has("Set")) {
			this.parseFunc(this.dataFormatToSetFunc, jo.getJSONObject("Set"));
		}
		this.tempInfo.parse(jo.getJSONObject("Temp"));
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
			if (FileUtils.isExists(filePath)) {
				String content = FileUtils.readFileContent(filePath);
				map.put(key, content);
			} else {
				map.put(key, "");
			}
		}
	}

	public static LangInfo parseByJsonObject(String named, JSONObject jObj) {
		LangInfo rs = new LangInfo();
		rs.parse(named, jObj);
		return rs;
	}
}
