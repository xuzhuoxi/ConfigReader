package cfg.serialize.generater;

import cfg.serialize.ClassLangType;
import cfg.serialize.FieldRangeType;

public class LangFileGenerater {

	public static IContentGenerater getModuleGenerater(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater mg = new ModuleGenerater();
		mg.setInfo(lang, fieldRange, TempKey.KEY_CONTENT_PACKAGE);
//		System.out.println(mg);

		IContentGenerater cg = getClassGenerater(lang, fieldRange);
		mg.setSubGenerater(TempKey.KEY_CONTENT_CLASS, cg);
		return mg;
	}

	public static IContentGenerater getClassGenerater(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater cg = new ClassGenerater();
		cg.setInfo(lang, fieldRange, TempKey.KEY_CONTENT_CLASS);
//		System.out.println(cg);

		cg.setSubGenerater(TempKey.KEY_CONTENT_PROPERTY, getPropertyGenerater(lang, fieldRange));
		cg.setSubGenerater(TempKey.KEY_CONTENT_PARSE_JSON,
				getPropertyParseGenerater(lang, fieldRange, TempKey.KEY_CONTENT_PARSE_JSON));
		cg.setSubGenerater(TempKey.KEY_CONTENT_PARSE_BINARY,
				getPropertyParseGenerater(lang, fieldRange, TempKey.KEY_CONTENT_PARSE_BINARY));
		return cg;
	}

	public static IContentGenerater getPropertyGenerater(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater pg = new PropertyGenerater();
		pg.setInfo(lang, fieldRange, TempKey.KEY_CONTENT_PROPERTY);
//		System.out.println(pg);
		return pg;
	}

	public static IContentGenerater getPropertyParseGenerater(ClassLangType lang, FieldRangeType fieldRange,
			String tempKey) {
		IContentGenerater pjpg = new PropertyJsonParserGenerater();
		pjpg.setInfo(lang, fieldRange, tempKey);
//		System.out.println(pjpg);
		return pjpg;
	}
}
