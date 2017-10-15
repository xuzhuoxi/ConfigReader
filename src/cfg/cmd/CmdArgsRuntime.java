package cfg.cmd;

import java.util.ArrayList;
import java.util.List;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDataFormat;
import cfg.serialize.OutputDefineLangType;
import cfg.serialize.OutputType;
import cfg.util.PathUtil;

public class CmdArgsRuntime {
	/**
	 * 原始参数
	 */
	private CmdArgsOrigin cmdArgsOrigin = null;

	/**
	 * 数据源目录路径或文件路径
	 */
	private String sourcePath = null;
	/**
	 * 输出目录路径
	 */
	private String targetPath = null;
	/**
	 * 输出类型
	 * 
	 * @see OutputType
	 */
	private OutputType outType = null;
	/**
	 * 字段范围
	 * 
	 * @see FieldRangeType
	 */
	private FieldRangeType fieldRangeType = null;

	/**
	 * 数据文件的输出格式
	 * 
	 * @see OutputDataFormat
	 */
	private List<OutputDataFormat> dataFormats = new ArrayList<OutputDataFormat>();

	/**
	 * 编程语言支持
	 * 
	 * @see OutputDefineLangType
	 */
	private List<OutputDefineLangType> defineLangs = new ArrayList<OutputDefineLangType>();

	/**
	 * 原始参数
	 * 
	 * @return 原始参数
	 * @see CmdArgsOrigin
	 */
	public CmdArgsOrigin getCmdArgsOrigin() {
		return cmdArgsOrigin;
	}

	/**
	 * 配置源路径
	 * 
	 * @return 源路径字符串
	 */
	public String getSourcePath() {
		return sourcePath;
	}

	/**
	 * 设置新源路径
	 * 
	 * @param sourcePath
	 *            源路径
	 */
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	/**
	 * 配置目标路径
	 * 
	 * @return 目标路径字符串
	 */
	public String getTargetPath() {
		return targetPath;
	}

	/**
	 * 设置新目标路径
	 * 
	 * @param targetPath
	 *            目标路径
	 */
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	/**
	 * 是否用输出类型定义
	 * 
	 * @return true:有，false:没有
	 */
	public boolean hasOutType() {
		return null != outType;
	}

	/**
	 * 输出类型
	 * 
	 * @return 输出类型
	 * 
	 * @see OutputType
	 */
	public OutputType getOutType() {
		return outType;
	}

	/**
	 * 设置输出类型
	 * 
	 * @param outType
	 *            输出类型
	 */
	public void setOutType(OutputType outType) {
		this.outType = outType;
	}

	/**
	 * 是否有字段范围类型定义
	 * 
	 * @return true:有，false:没有
	 */
	public boolean hasFieldRangeType() {
		return null != fieldRangeType;
	}

	/**
	 * 导出的字段范围类型
	 * 
	 * @return 导出的字段范围类型
	 * 
	 * @see FieldRangeType
	 */
	public FieldRangeType getFieldRangeType() {
		return fieldRangeType;
	}

	/**
	 * 设置导出的字段范围类型
	 * 
	 * @param fieldRangeType
	 *            导出的字段范围类型
	 * @see FieldRangeType
	 */
	public void setFieldRangeType(FieldRangeType fieldRangeType) {
		this.fieldRangeType = fieldRangeType;
	}

	/**
	 * 是否有输出格式定义
	 * 
	 * @return true:有，false:没有
	 */
	public boolean hasOutputDataFormat() {
		return dataFormats.size() > 0;
	}

	/**
	 * 数据文件的输出格式列表
	 * 
	 * @return 数据文件的输出格式列表
	 * 
	 * @see OutputDataFormat
	 */
	public List<OutputDataFormat> getDataFormats() {
		return dataFormats;
	}

	/**
	 * 是否有编程语言定义
	 * 
	 * @return true:有，false:没有
	 */
	public boolean hasOutputDefineLangType() {
		return defineLangs.size() > 0;
	}

	/**
	 * 编程语言列表
	 * 
	 * @return 编程语言列表
	 * 
	 * @see OutputDefineLangType
	 */
	public List<OutputDefineLangType> getDefineLangs() {
		return defineLangs;
	}

	@Override
	public String toString() {
		return "CmdArgsRuntime {\n" + "sourcePath=" + sourcePath + "\ntargetPath=" + targetPath + "\noutType=" + outType
				+ "\nfieldRangeType=" + fieldRangeType + "\ndataFormats=" + dataFormats + "\ndefineLangs=" + defineLangs
				+ "\n}";
	}

	private CmdArgsRuntime(CmdArgsOrigin cmdArgsOrigin) {
		super();
		this.cmdArgsOrigin = cmdArgsOrigin;
		this.handleArgs();
	}

	private void handleArgs() {
		this.sourcePath = PathUtil.handPath(cmdArgsOrigin.argsMap.get(CmdArgKeys.KEY_SOURCE));
		String targetPath = PathUtil.handPath(cmdArgsOrigin.argsMap.get(CmdArgKeys.KEY_TARGET));
		if (targetPath.endsWith("/")) {
			targetPath = targetPath.substring(0, targetPath.length() - 2);
		}
		this.targetPath = targetPath;
		this.outType = OutputType.from(cmdArgsOrigin.argsMap.get(CmdArgKeys.KEY_OUTTYPE));
		this.fieldRangeType = FieldRangeType.from(cmdArgsOrigin.argsMap.get(CmdArgKeys.KEY_FIELD));
	}

	/**
	 * 生成参数数据结构
	 * 
	 * @param args
	 *            参数列表长度必须为偶数，并且前一位为Key，后一位为Value
	 * @return 参数映射表
	 */
	public static CmdArgsRuntime createArgsMap(String[] args) {
		CmdArgsOrigin origin = CmdArgsOrigin.createArgsMap(args);
		CmdArgsRuntime runtime = new CmdArgsRuntime(origin);
		return runtime;
	}
}
