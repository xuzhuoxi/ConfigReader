package cfg.serialize.cfgdata;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.FileFormat;

public class SheetHandlerFactory {
	private static Map<FileFormat, ISheetHandler> handlerMap = new HashMap<FileFormat, ISheetHandler>();

	static {
		handlerMap.put(FileFormat.Json, new JsonSheetHandler());
		handlerMap.put(FileFormat.Binary, new BinarySheetHandler());
	}

	public static ISheetHandler getSheetHandler(FileFormat fileFormat) {
		if (handlerMap.containsKey(fileFormat)) {
			return handlerMap.get(fileFormat);
		}
		return null;
	}
}
