package cfg.serialize.lang;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.ExportLangClassType;

public class LangConversionFactory {

	// static 静态——————————

	private static LangConversionFactory shared = new LangConversionFactory();

	public static LangConversionFactory getShared() {
		return shared;
	}

	// class 类——————————

	private Map<ExportLangClassType, ILangConversion> langMap = null;

	private LangConversionFactory() {
		this.langMap = new HashMap<ExportLangClassType, ILangConversion>();
		this.langMap.put(ExportLangClassType.TypeScript, new TSConversion());
	}

	public final ILangConversion getConversion(ExportLangClassType lang) {
		return langMap.get(lang);
	}

	public final ILangConversion getConversion(String lang) {
		ExportLangClassType langType = ExportLangClassType.from(lang);
		return getConversion(langType);
	}
}
