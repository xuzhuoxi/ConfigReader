package cfg.settings;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import cfg.serialize.cfgcontent.StringContentHandler;
import code.file.FileUtils;

public class Settings {
	protected String source;
	protected String sourceEncoding;
	protected Charset sourceCharset;
	protected String target;
	protected String targetEncoding;
	protected Charset targetCharset;
	protected String[] dataFormat;
	protected String[] langs;
	protected String sheetPrefix;

	protected int serverOutRowIndex;
	protected int clientOutRowIndex;
	protected int nameRowIndex;
	protected int remarkRowIndex;
	protected int validRowIndex;

	protected int dataTypeRowIndex;
	protected int startRowIndex;

	protected Map<String, Integer> fieldNameRowIndexMap;

	private Settings() {
		super();
	}

	public String getSource() {
		return source;
	}

	public String getSourceEncoding() {
		return sourceEncoding;
	}

	public Charset getSourceCharset() {
		return sourceCharset;
	}

	public String getTarget() {
		return target;
	}

	public String getTargetEncoding() {
		return targetEncoding;
	}

	public Charset getTargetCharset() {
		return targetCharset;
	}

	public String[] getDataFormat() {
		return dataFormat.clone();
	}

	public String[] getLangs() {
		return langs.clone();
	}

	public String getSheetPrefix() {
		return sheetPrefix;
	}

	public int getServerOutRowIndex() {
		return serverOutRowIndex;
	}

	public int getClientOutRowIndex() {
		return clientOutRowIndex;
	}

	public int getNameRowIndex() {
		return nameRowIndex;
	}

	public int getRemarkRowIndex() {
		return remarkRowIndex;
	}

	public int getValidRowIndex() {
		return validRowIndex;
	}

	public int getDataTypeRowIndex() {
		return dataTypeRowIndex;
	}

	public int getStartRowIndex() {
		return startRowIndex;
	}

	public Map<String, Integer> getFieldNameRowNumMap() {
		return fieldNameRowIndexMap;
	}

	public int getFieldNameRowIndex(String langNamed) {
		return fieldNameRowIndexMap.get(langNamed);
	}

	@Override
	public String toString() {
		return "Settings [source=" + source + ", target=" + target + ", dataFormat=" + Arrays.toString(dataFormat)
				+ ", langs=" + Arrays.toString(langs) + ", sheetPrefix=" + sheetPrefix + ", serverOutRowIndex="
				+ serverOutRowIndex + ", clientOutRowIndex=" + clientOutRowIndex + ", nameRowIndex=" + nameRowIndex
				+ ", remarkRowIndex=" + remarkRowIndex + ", validRowIndex=" + validRowIndex + ", dataTypeRowIndex="
				+ dataTypeRowIndex + ", startRowIndex=" + startRowIndex + ", fieldNameRowIndexMap="
				+ fieldNameRowIndexMap + "]";
	}

	public static final Settings parseByPath(String filePath) {
		String jsonContent = FileUtils.readFileContent(filePath);
		return parseByJson(jsonContent);
	}

	public static final Settings parseByJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		Settings settings = new Settings();
		settings.source = jsonObj.getJSONObject("source").getString("value");
		settings.sourceEncoding = jsonObj.getJSONObject("source").getString("encoding");
		settings.sourceCharset = Charset.forName(settings.sourceEncoding);
		StringContentHandler.CHARSET_SOURCE = settings.sourceCharset;

		settings.target = jsonObj.getJSONObject("target").getString("value");
		settings.targetEncoding = jsonObj.getJSONObject("target").getString("encoding");
		settings.targetCharset = Charset.forName(settings.targetEncoding);
		StringContentHandler.CHARSET_TARGET = settings.targetCharset;

		settings.dataFormat = getStringArray(jsonObj, "dataFormat", "value");
		settings.langs = getStringArray(jsonObj, "langs", "value");
		settings.sheetPrefix = jsonObj.getJSONObject("sheetPrefix").getString("value").toLowerCase();

		settings.serverOutRowIndex = jsonObj.getJSONObject("serverOut").getInt("value") - 1;
		settings.clientOutRowIndex = jsonObj.getJSONObject("clientOut").getInt("value") - 1;
		settings.nameRowIndex = jsonObj.getJSONObject("name").getInt("value") - 1;
		settings.remarkRowIndex = jsonObj.getJSONObject("remark").getInt("value") - 1;
		settings.validRowIndex = jsonObj.getJSONObject("valid").getInt("value") - 1;
		settings.dataTypeRowIndex = jsonObj.getJSONObject("dataType").getInt("value") - 1;
		settings.startRowIndex = jsonObj.getJSONObject("startRow").getInt("value") - 1;

		JSONObject langMap = jsonObj.getJSONObject("fieldName").getJSONObject("value");
		Map<String, Integer> map = new HashMap<String, Integer>();
		String key = null;
		@SuppressWarnings("rawtypes")
		Iterator iterator = langMap.keys();
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			map.put(key, langMap.getInt(key) - 1);
		}
		settings.fieldNameRowIndexMap = map;
		return settings;
	}

	private static final String[] getStringArray(JSONObject jsonObj, String first, String second) {
		JSONArray df = jsonObj.getJSONObject(first).getJSONArray(second);
		int len = df.length();
		String[] data = new String[len];
		for (int index = 0; index < len; index++) {
			data[index] = df.getString(index);
		}
		return data;
	}
}
