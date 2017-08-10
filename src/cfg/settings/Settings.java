package cfg.settings;

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

	public static final Settings parseByPath(String sysFilePath, String projectFilePath, String langsFilePath) {
		String sysJsonContent = FileUtils.readFileContent(sysFilePath);
		String proJsonContent = FileUtils.readFileContent(projectFilePath);
		String langJsonContent = FileUtils.readFileContent(langsFilePath);
		return parseByJson(sysJsonContent, proJsonContent, langJsonContent);
	}

	public static final Settings parseByJson(String sysJson, String proJson, String langJson) {
		Settings settings = new Settings();
		settings.sysSettings = SysSettings.parseByJson(sysJson);
		settings.projectSettings = ProjectSettings.parseByJson(proJson);
		settings.langSettings = LangSettings.parseByJson(langJson);
		return settings;
	}
}
