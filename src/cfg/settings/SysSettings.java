package cfg.settings;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import cfg.settings.sys.BuffSetting;
import cfg.util.PathUtil;
import code.file.FileUtils;

public class SysSettings {
	protected String source;
	protected String sourceEncoding;
	protected Charset sourceCharset;

	protected String target;
	protected String targetEncoding;
	protected Charset targetCharset;

	protected BuffSetting buffSettings;

	protected String[] fileTypes;
	protected String[] langs;
	protected String[] dataFormats;
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

	public boolean isEncodingConsistent() {
		return sourceCharset == targetCharset;
	}

	public BuffSetting getBuffSettings() {
		return buffSettings;
	}

	public String[] getFileTypes() {
		return fileTypes.clone();
	}

	public String[] getLangs() {
		return langs.clone();
	}

	public String[] getDataFormats() {
		return dataFormats;
	}

	public String getSheetPrefix() {
		return sheetPrefix;
	}

	@Override
	public String toString() {
		return "SysSettings [source=" + source + ", sourceEncoding=" + sourceEncoding + ", sourceCharset="
				+ sourceCharset + ", target=" + target + ", targetEncoding=" + targetEncoding + ", targetCharset="
				+ targetCharset + ", buffSettings=" + buffSettings + ", fileTypes=" + Arrays.toString(fileTypes)
				+ ", langs=" + Arrays.toString(langs) + ", dataFormats=" + Arrays.toString(dataFormats)
				+ ", sheetPrefix=" + sheetPrefix + "]";
	}

	public static final SysSettings parseByPath(String filePath) {
		String jsonContent = FileUtils.readFileContent(filePath);
		return parseByJson(jsonContent);
	}

	public static final SysSettings parseByJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		SysSettings settings = new SysSettings();

		JSONObject sourceObj = jsonObj.getJSONObject("Source");
		String sourcePath = sourceObj.getString("value").trim();
		settings.source = handPath(sourcePath);
		settings.sourceEncoding = sourceObj.getString("encoding");
		settings.sourceCharset = Charset.forName(settings.sourceEncoding);

		JSONObject targetObj = jsonObj.getJSONObject("Target");
		String targetPath = targetObj.getString("value").trim();
		settings.target = handPath(targetPath);
		settings.targetEncoding = targetObj.getString("encoding");
		settings.targetCharset = Charset.forName(settings.targetEncoding);

		JSONObject buffObject = jsonObj.getJSONObject("Buff");
		settings.buffSettings = BuffSetting.create(buffObject.getBoolean("BigEndian"), buffObject.getInt("token"),
				buffObject.getInt("item"), buffObject.getInt("sheet"));

		settings.fileTypes = getStringArray(jsonObj, "FileType", "value");
		settings.langs = getStringArray(jsonObj, "Langs", "value");
		settings.dataFormats = getStringArray(jsonObj, "DataFormat", "value");

		settings.sheetPrefix = jsonObj.getJSONObject("SheetPrefix").getString("value").toLowerCase();
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
		return PathUtil.handPath(path);
	}
}
