package cfg.serialize.generater;

public class LangFileGenerater {

	public static IContentGenerater getModuleGenerater(String modulePath, String classPath, String propertyPath,
			String propertyJsonPath, String lang) {
		IContentGenerater mg = new ModuleGenerater();
		mg.setTempPath(modulePath);
		mg.setLang(lang);
		IContentGenerater cg = getClassGenerater(classPath, propertyPath, propertyJsonPath, lang);
		mg.setSubGenerater(TempKey.KEY_CONTENT_CLASS, cg);
		return mg;
	}

	public static IContentGenerater getClassGenerater(String classPath, String propertyPath, String propertyJsonPath,
			String lang) {
		IContentGenerater cg = new ClassGenerater();
		cg.setTempPath(classPath);
		cg.setLang(lang);
		IContentGenerater pg = getPropertyGenerater(propertyPath, lang);
		IContentGenerater pjpg = getPropertyParseJsonGenerater(propertyJsonPath, lang);
		cg.setSubGenerater(TempKey.KEY_CONTENT_PROPERTY, pg);
		cg.setSubGenerater(TempKey.KEY_CONTENT_PARSE_JSON, pjpg);
		return cg;
	}

	public static IContentGenerater getPropertyGenerater(String propertyPath, String lang) {
		IContentGenerater pg = new PropertyGenerater();
		pg.setTempPath(propertyPath);
		pg.setLang(lang);
		return pg;
	}

	public static IContentGenerater getPropertyParseJsonGenerater(String propertyJsonPath, String lang) {
		IContentGenerater pjpg = new PropertyJsonParserGenerater();
		pjpg.setTempPath(propertyJsonPath);
		pjpg.setLang(lang);
		return pjpg;
	}
}
