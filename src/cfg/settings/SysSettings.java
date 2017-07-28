package cfg.settings;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import cfg.AppDefine;
import cfg.serialize.cfgcontent.StringContentHandler;
import cfg.settings.sys.BuffSetting;
import code.file.FileUtils;

public class SysSettings {
	protected String source;
	protected String sourceEncoding;
	protected Charset sourceCharset;
	
	protected String target;
	protected String targetEncoding;
	protected Charset targetCharset;
	
	protected String[] dataFormat;
	protected BuffSetting buffSettings;
	protected String[] langs;
	protected String sheetPrefix;

	public SysSettings() {
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

	public BuffSetting getBuffSettings() {
		return buffSettings;
	}

	public String[] getLangs() {
		return langs.clone();
	}

	public String getSheetPrefix() {
		return sheetPrefix;
	}

	@Override
	public String toString() {
		return "SysSettings [source=" + source + ", sourceEncoding=" + sourceEncoding + ", sourceCharset="
				+ sourceCharset + ", target=" + target + ", targetEncoding=" + targetEncoding + ", targetCharset="
				+ targetCharset + ", dataFormat=" + Arrays.toString(dataFormat) + ", buffSettings=" + buffSettings
				+ ", langs=" + Arrays.toString(langs) + ", sheetPrefix=" + sheetPrefix + "]";
	}

	public static final SysSettings parseByPath(String filePath) {
		String jsonContent = FileUtils.readFileContent(filePath);
		return parseByJson(jsonContent);
	}

	public static final SysSettings parseByJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		SysSettings settings = new SysSettings();

		JSONObject sourceObj = jsonObj.getJSONObject("source");
		String sourcePath = sourceObj.getString("value").trim();
		settings.source = handPath(sourcePath);
		settings.sourceEncoding = sourceObj.getString("encoding");
		settings.sourceCharset = Charset.forName(settings.sourceEncoding);
		StringContentHandler.CHARSET_SOURCE = settings.sourceCharset;

		JSONObject targetObj = jsonObj.getJSONObject("target");
		String targetPath = targetObj.getString("value").trim();
		settings.target = handPath(targetPath);
		settings.targetEncoding = targetObj.getString("encoding");
		settings.targetCharset = Charset.forName(settings.targetEncoding);
		StringContentHandler.CHARSET_TARGET = settings.targetCharset;

		settings.dataFormat = getStringArray(jsonObj, "dataFormat", "value");
		JSONObject buffObject = jsonObj.getJSONObject("buff");
		settings.buffSettings = BuffSetting.create(buffObject.getInt("token"), buffObject.getInt("item"),
				buffObject.getInt("sheet"));
		settings.langs = getStringArray(jsonObj, "langs", "value");
		settings.sheetPrefix = jsonObj.getJSONObject("sheetPrefix").getString("value").toLowerCase();
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

	private static String handPath(String path) {
		if (path.indexOf("./") == 0) {
			return AppDefine.instance.getBasePath() + "/" + path;
		}
		if (path.indexOf("/") == 0) {
			return AppDefine.instance.getBasePath() + path;
		}
		return path;
	}
}
