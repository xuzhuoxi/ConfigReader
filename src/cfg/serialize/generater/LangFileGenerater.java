package cfg.serialize.generater;

import cfg.serialize.ClassLangType;
import cfg.serialize.FieldRangeType;

public class LangFileGenerater {

	public static IContentGenerater getModuleGenerater(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater mg = new ModuleGenerater();
		mg.setInfo(lang, fieldRange);
		// System.out.println(mg);

		IContentGenerater cg = getClassGenerater(lang, fieldRange);
		mg.setSubGenerater(cg.getTempKey(), cg);
		return mg;
	}

	public static IContentGenerater getClassGenerater(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater cg = new ClassGenerater();
		cg.setInfo(lang, fieldRange);
		// System.out.println(cg);

		IContentGenerater pcg = getPropertyGenerater(lang, fieldRange);
		cg.setSubGenerater(pcg.getTempKey(), pcg);
		IContentGenerater pcgjp = getPropertyGeneraterJsonParser(lang, fieldRange);
		cg.setSubGenerater(pcgjp.getTempKey(), pcgjp);
		IContentGenerater pcgbp = getPropertyGeneraterBinaryParser(lang, fieldRange);
		cg.setSubGenerater(pcgbp.getTempKey(), pcgbp);
		return cg;
	}

	public static IContentGenerater getPropertyGenerater(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater pg = new PropertyGenerater();
		pg.setInfo(lang, fieldRange);
		// System.out.println(pg);
		return pg;
	}

	public static IContentGenerater getPropertyGeneraterJsonParser(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater pjpg = new PropertyGeneraterJsonParser();
		pjpg.setInfo(lang, fieldRange);
		// System.out.println(pjpg);
		return pjpg;
	}

	public static IContentGenerater getPropertyGeneraterBinaryParser(ClassLangType lang, FieldRangeType fieldRange) {
		IContentGenerater pjpg = new PropertyGeneraterBinaryParser();
		pjpg.setInfo(lang, fieldRange);
		// System.out.println(pjpg);
		return pjpg;
	}
}
