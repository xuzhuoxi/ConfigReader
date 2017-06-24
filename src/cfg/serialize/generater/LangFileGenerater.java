package cfg.serialize.generater;

public class LangFileGenerater {

	public static IContentGenerater getModuleGenerater(String modulePath, String classPath, String propertyPath,
			String lang) {
		IContentGenerater mg = new ModuleGenerater();
		mg.setTempPath(modulePath);
		mg.setLang(lang);
		IContentGenerater cg = getClassGenerater(classPath, propertyPath, lang);
		mg.setSubGenerater(cg);
		return mg;
	}

	public static IContentGenerater getClassGenerater(String classPath, String propertyPath, String lang) {
		IContentGenerater cg = new ClassGenerater();
		cg.setTempPath(classPath);
		cg.setLang(lang);
		IContentGenerater pg = getPropertyGenerater(propertyPath, lang);
		cg.setSubGenerater(pg);
		return cg;
	}

	public static IContentGenerater getPropertyGenerater(String propertyPath, String lang) {
		IContentGenerater pg = new PropertyGenerater();
		pg.setTempPath(propertyPath);
		pg.setLang(lang);
		return pg;
	}
}
