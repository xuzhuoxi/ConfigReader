package cfg.settings;

import code.file.FileUtils;

public class Settings {
	protected SysSettings sysSettings;
	protected ProjectSettings projectSettings;

	private Settings() {
		super();
	}

	public SysSettings getSysSettings() {
		return sysSettings;
	}

	public ProjectSettings getProjectSettings() {
		return projectSettings;
	}

	@Override
	public String toString() {
		return "Settings：\nsysSettings=" + sysSettings + "\nprojectSettings=" + projectSettings + "]";
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
		return settings;
	}
}
