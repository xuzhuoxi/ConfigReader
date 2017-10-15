package cfg.cmd;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDataFormat;
import cfg.serialize.OutputDefineLangType;
import cfg.serialize.OutputType;

/**
 * 参数键表
 * 
 * @author xuzhuoxi
 */
public class CmdArgKeys {
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
}
