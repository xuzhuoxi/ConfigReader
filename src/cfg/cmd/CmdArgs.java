package cfg.cmd;

import java.util.HashMap;
import java.util.Map;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDataFormat;
import cfg.serialize.OutputDefineLangType;
import cfg.serialize.OutputType;
import cfg.settings.Settings;

/**
 * 命令行参数定义
 * 
 * @author xuzhuoxi<br>
 *         create on 2017年9月4日.<br>
 */
public class CmdArgs {
	/**
	 * 数据来源目录路径或文件路径
	 */
	public static String KEY_SOURCE = "-Source";
	/**
	 * 输出目录
	 */
	public static String KEY_TARGET = "-Target";
	/**
	 * 数据输出类型，支持范围请看OutputType<br>
	 * 
	 * @see OutputType
	 */
	public static String KEY_OUTTYPE = "-OutType";
	/**
	 * 字段范围类型，支持范围请看FieldRangeType<br>
	 * 
	 * @see FieldRangeType
	 */
	public static String KEY_FIELD = "-Field";
	/**
	 * 输出数据文件格式，支持范围请看OutputDataFormat<br>
	 * 
	 * @see OutputDataFormat
	 */
	public static String KEY_DATAOUT = "-DataOut";
	/**
	 * 输出的定义文件的编程语言类型，支持范围请看OutputDefineLangType<br>
	 * 
	 * @see OutputDefineLangType
	 */
	public static String KEY_DEFINEOUT = "-DefineOut";

	/**
	 * 生成参数映射表
	 * 
	 * @param args
	 *            参数列表长度必须为偶数，并且前一位为Key，后一位为Value
	 * @return 参数映射表
	 */
	public static Map<String, String> createArgsMap(String[] args) {
		int groupLen = args.length / 2;
		Map<String, String> argsMap = new HashMap<String, String>();
		for (int index = 0; index < groupLen; index += 2) {
			argsMap.put(args[index], args[index + 1]);
		}
		Settings settings = Settings.getInstance();
		if (!argsMap.containsKey(KEY_SOURCE)) {
			argsMap.put(KEY_SOURCE, settings.getSysSettings().getSource());
		}
		if (!argsMap.containsKey(KEY_TARGET)) {
			argsMap.put(KEY_TARGET, settings.getSysSettings().getTarget());
		}
		return argsMap;
	}
}
