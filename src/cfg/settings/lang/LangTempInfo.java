package cfg.settings.lang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import cfg.AppDefine;
import code.file.FileUtils;

public class LangTempInfo {
	private String mainPath;
	private String[] subKeys;
	private Map<String, String> subPath = new HashMap<String, String>();

	private String mainContent;
	private Map<String, String> subContent = new HashMap<String, String>();

	/**
	 * 主模板配置路径
	 * 
	 * @return 路径的字符串表示
	 */
	public String getMainPath() {
		return mainPath + "";
	}

	/**
	 * 主模板文本内容
	 * 
	 * @return 主模板文本内容
	 */
	public String getMainContent() {
		return mainContent + "";
	}

	/**
	 * 取子模板的配置路径
	 * 
	 * @param subKey
	 *            配置键,应为LangTempKey中常量
	 * @return 子模板的配置路径
	 */
	public String getSubPath(String subKey) {
		return this.subPath.get(subKey) + "";
	}

	/**
	 * 取子模板的文本内容
	 * 
	 * @param subKey
	 *            配置键,应为LangTempKey中常量
	 * @return 子模板的文本内容
	 */
	public String getSubContent(String subKey) {
		return this.subContent.get(subKey) + "";
	}

	// -------------------------------------------

	@Override
	public String toString() {
		return "LangTempInfo [mainPath=" + mainPath + ", subKeys=" + Arrays.toString(subKeys) + ", subPath=" + subPath
				+ "]";
	}

	public void parse(JSONObject jo) {
		this.parseMain(jo);
		this.parseSub(jo);
	}

	protected void parseMain(JSONObject jo) {
		this.mainPath = jo.getString("Main");
		this.mainContent = this.getContent(this.mainPath);
	}

	protected void parseSub(JSONObject jo) {
		JSONObject sub = jo.getJSONObject("Sub");
		String[] subKeys = JSONObject.getNames(sub);
		this.subKeys = subKeys;
		for (String key : subKeys) {
			String path = sub.getString(key);
			subPath.put(key, path);
			subContent.put(key, this.getContent(path));
		}
	}

	private String getContent(String path) {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + "/" + path;
		System.out.println(filePath + ":" + FileUtils.isExists(filePath));
		if (FileUtils.isExists(filePath)) {
			return FileUtils.readFileContent(filePath);
		} else {
			return "";
		}
	}
}
