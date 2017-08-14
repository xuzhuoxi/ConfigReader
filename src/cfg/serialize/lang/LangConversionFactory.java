package cfg.serialize.lang;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.ClassLangType;

public class LangConversionFactory {

	// static 静态——————————

	private static LangConversionFactory shared = new LangConversionFactory();

	public static LangConversionFactory getShared() {
		return shared;
	}

	// class 类——————————

	private Map<ClassLangType, ILangConversion> langMap = null;

	private LangConversionFactory() {
		this.langMap = new HashMap<ClassLangType, ILangConversion>();
		this.langMap.put(ClassLangType.TypeScript, new TSConversion());
		this.langMap.put(ClassLangType.Java, new JavaConversion());
	}

	public final ILangConversion getConversion(ClassLangType lang) {
		return langMap.get(lang);
	}

	public final ILangConversion getConversion(String lang) {
		ClassLangType langType = ClassLangType.from(lang);
		return getConversion(langType);
	}
}
