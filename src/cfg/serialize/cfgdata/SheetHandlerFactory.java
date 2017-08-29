package cfg.serialize.cfgdata;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.OutputDataFormat;

public class SheetHandlerFactory {
	private static Map<OutputDataFormat, ISheetHandler> handlerMap = new HashMap<OutputDataFormat, ISheetHandler>();

	static {
		handlerMap.put(OutputDataFormat.Json, new JsonSheetHandler());
		handlerMap.put(OutputDataFormat.Binary, new BinarySheetHandler());
		handlerMap.put(OutputDataFormat.Sql, null);
	}

	public static ISheetHandler getSheetHandler(OutputDataFormat fileFormat) {
		if (handlerMap.containsKey(fileFormat)) {
			return handlerMap.get(fileFormat);
		}
		return null;
	}
}
