package cfg.serialize.generater;

import cfg.serialize.ClassLangType;
import cfg.serialize.FieldRangType;

public class LangFileGenerater {

	public static IContentGenerater getModuleGenerater(String modulePath, String classPath, String propertyPath,
			String propertyJsonPath, ClassLangType lang, FieldRangType fieldRange) {
		IContentGenerater mg = new ModuleGenerater();
		mg.setTempPath(modulePath);
		mg.setLang(lang);
		mg.setFieldRang(fieldRange);
		IContentGenerater cg = getClassGenerater(classPath, propertyPath, propertyJsonPath, lang, fieldRange);
		mg.setSubGenerater(TempKey.KEY_CONTENT_CLASS, cg);
		return mg;
	}

	public static IContentGenerater getClassGenerater(String classPath, String propertyPath, String propertyJsonPath,
			ClassLangType lang, FieldRangType fieldRange) {
		IContentGenerater cg = new ClassGenerater();
		cg.setTempPath(classPath);
		cg.setLang(lang);
		cg.setFieldRang(fieldRange);
		IContentGenerater pg = getPropertyGenerater(propertyPath, lang, fieldRange);
		IContentGenerater pjpg = getPropertyParseJsonGenerater(propertyJsonPath, lang, fieldRange);
		cg.setSubGenerater(TempKey.KEY_CONTENT_PROPERTY, pg);
		cg.setSubGenerater(TempKey.KEY_CONTENT_PARSE_JSON, pjpg);
		return cg;
	}

	public static IContentGenerater getPropertyGenerater(String propertyPath, ClassLangType lang,
			FieldRangType fieldRange) {
		IContentGenerater pg = new PropertyGenerater();
		pg.setTempPath(propertyPath);
		pg.setLang(lang);
		pg.setFieldRang(fieldRange);
		return pg;
	}

	public static IContentGenerater getPropertyParseJsonGenerater(String propertyJsonPath, ClassLangType lang,
			FieldRangType fieldRange) {
		IContentGenerater pjpg = new PropertyJsonParserGenerater();
		pjpg.setTempPath(propertyJsonPath);
		pjpg.setLang(lang);
		pjpg.setFieldRang(fieldRange);
		return pjpg;
	}
}
