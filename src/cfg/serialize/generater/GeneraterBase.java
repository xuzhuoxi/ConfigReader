package cfg.serialize.generater;

import cfg.serialize.lang.ILangConversion;
import cfg.serialize.lang.LangConversionFactory;
import code.file.FileUtils;

public class GeneraterBase {
	protected String tempUrl = null;
	protected String tempContent = null;

	protected String lang;
	protected ILangConversion langConversion;

	protected IContentGenerater subGenerater;

	protected StringBuilder sb = new StringBuilder();

	public void setTempPath(String tempUrl) {
		this.tempUrl = tempUrl;
		this.tempContent = FileUtils.readFileContent(tempUrl, "UTF-8");
	}

	public void setLang(String lang) {
		this.lang = lang;
		this.langConversion = LangConversionFactory.getShared().getConversion(lang);
	}

	public void setSubGenerater(IContentGenerater subGenerater) {
		this.subGenerater = subGenerater;
	}
}
