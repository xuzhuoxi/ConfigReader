package cfg.serialize.cfgcontent;

import cfg.settings.Settings;
import code.lang.NumberConverter;

public class BinaryContentUtil {
	private static NumberConverter converter = null;

	public static NumberConverter getNumberConverter() {
		if (null == converter) {
			converter = NumberConverter
					.newInstance(Settings.getInstance().getSysSettings().getBuffSettings().byteOrder());
		}
		return converter;
	}
}
