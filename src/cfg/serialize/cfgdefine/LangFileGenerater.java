package cfg.serialize.cfgdefine;

import cfg.serialize.OutputDefineLangType;
import cfg.serialize.FieldRangeType;

public class LangFileGenerater {

	public static IContentGenerater getModuleGenerater(OutputDefineLangType lang, FieldRangeType fieldRange) {
		IContentGenerater mg = new ModuleGenerater();
		mg.setInfo(lang, fieldRange);
		// System.out.println(mg);

		IContentGenerater cg = getClassGenerater(lang, fieldRange);
		mg.setSubGenerater(cg.getTempKey(), cg);
		return mg;
	}

	public static IContentGenerater getClassGenerater(OutputDefineLangType lang, FieldRangeType fieldRange) {
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

	public static IContentGenerater getPropertyGenerater(OutputDefineLangType lang, FieldRangeType fieldRange) {
		IContentGenerater pg = new PropertyGenerater();
		pg.setInfo(lang, fieldRange);
		// System.out.println(pg);
		return pg;
	}

	public static IContentGenerater getPropertyGeneraterJsonParser(OutputDefineLangType lang, FieldRangeType fieldRange) {
		IContentGenerater pjpg = new PropertyGeneraterJsonParser();
		pjpg.setInfo(lang, fieldRange);
		// System.out.println(pjpg);
		return pjpg;
	}

	public static IContentGenerater getPropertyGeneraterBinaryParser(OutputDefineLangType lang, FieldRangeType fieldRange) {
		IContentGenerater pjpg = new PropertyGeneraterBinaryParser();
		pjpg.setInfo(lang, fieldRange);
		// System.out.println(pjpg);
		return pjpg;
	}
}
