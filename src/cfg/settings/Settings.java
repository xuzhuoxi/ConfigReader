package cfg.settings;

import cfg.AppDefine;
import code.file.FileUtils;

public class Settings {
	protected SysSettings sysSettings;
	protected ProjectSettings projectSettings;
	protected LangSettings langSettings;

	private Settings() {
		super();
	}

	public SysSettings getSysSettings() {
		return sysSettings;
	}

	public ProjectSettings getProjectSettings() {
		return projectSettings;
	}

	public LangSettings getLangSettings() {
		return langSettings;
	}

	@Override
	public String toString() {
		return "Settings [\nsysSettings=" + sysSettings + "\nprojectSettings=" + projectSettings + "\nlangSettings="
				+ langSettings + "]";
	}

	private static Settings instance = null;

	public static final Settings getInstance() {
		if (null == instance) {
			String basePath = AppDefine.instance.getBasePath();
			String sysPath = basePath + "/system.json";
			String proPath = basePath + "/project.json";
			instance = parseByPath(sysPath, proPath);
		}
		return instance;
	}

	public static final Settings parseByPath(String sysFilePath, String projectFilePath) {
		String sysJsonContent = FileUtils.readFileContent(sysFilePath);
		String proJsonContent = FileUtils.readFileContent(projectFilePath);
		return parseByJson(sysJsonContent, proJsonContent);
	}

	public static final Settings parseByJson(String sysJson, String proJson) {
		Settings settings = new Settings();
		settings.sysSettings = SysSettings.parseByJson(sysJson);
		settings.projectSettings = ProjectSettings.parseByJson(proJson);
		settings.langSettings = new LangSettings();
		String basePath = AppDefine.instance.getBasePath();
		for (String lang : settings.sysSettings.getLangs()) {
			// System.out.println(basePath + "/langs/" + lang + ".json");
			settings.langSettings.appendLangByPath(lang, basePath + "/langs/" + lang + ".json");
		}
		return settings;
	}
}
