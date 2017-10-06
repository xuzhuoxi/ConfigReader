package cfg.cmd;

import java.util.Map;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputType;
import cfg.util.PathUtil;

/**
 * 命令行处理基类<br>
 * 包括参数整合、参数验证、命令执行功能<br>
 * 
 * @author xuzhuoxi<br>
 *         create on 2017年9月4日.<br>
 */
public class CmdHandlerBase {
	/**
	 * 参数表
	 */
	protected Map<String, String> argsMap = null;
	/**
	 * 数据源目录路径或文件路径
	 */
	protected String sourcePath = null;
	/**
	 * 输出目录路径
	 */
	protected String targetPath = null;
	/**
	 * 输出类型
	 * 
	 * @see OutputType
	 */
	protected OutputType outType = null;
	/**
	 * 字段范围
	 * 
	 * @see FieldRangeType
	 */
	protected FieldRangeType fieldRangeType = null;

	public CmdHandlerBase(Map<String, String> argsMap) {
		super();
		this.argsMap = argsMap;
	}

	/**
	 * 命令执行
	 */
	final public void execute() {
		if (!this.argsVerify()) {
			System.err.println("Param Error!");
			return;
		}
		this.handleArgs();
		this.doExecute();
	}

	/**
	 * 参数验证
	 * 
	 * @return true:验证通过，false:验证不通过
	 */
	protected boolean argsVerify() {
		if (!this.hasArg(CmdArgs.KEY_OUTTYPE)) {
			System.err.println("Params \"-OutType\"  is must.");
			return false;
		}
		if (!this.hasArg(CmdArgs.KEY_FIELD)) {
			System.err.println("Params \"-Field\"  is must.");
			return false;
		}
		return true;
	}

	/**
	 * 参数处理
	 */
	protected void handleArgs() {
		this.sourcePath = PathUtil.handPath(argsMap.get(CmdArgs.KEY_SOURCE));
		String targetPath = PathUtil.handPath(argsMap.get(CmdArgs.KEY_TARGET));
		if (targetPath.endsWith("/")) {
			targetPath = targetPath.substring(0, targetPath.length() - 2);
		}
		this.targetPath = targetPath;
		this.outType = OutputType.from(argsMap.get(CmdArgs.KEY_OUTTYPE));
		this.fieldRangeType = FieldRangeType.from(argsMap.get(CmdArgs.KEY_FIELD));
	}

	/**
	 * 命令实际执行入口，子类重写
	 */
	protected void doExecute() {
		throw new Error("Not Override!");
	}

	/**
	 * 是否包含参数
	 * 
	 * @param paramKey
	 *            参数键，支持内容请看CmdArgs
	 * @return true:包含，false不包含
	 * @see CmdArgs
	 */
	protected boolean hasArg(String paramKey) {
		return this.argsMap.containsKey(paramKey);
	}

	/**
	 * 取参数键对应的原始参数值
	 * 
	 * @param paramKey
	 *            参数键，支持内容请看CmdArgs
	 * @return 原始参数值
	 * @see CmdArgs
	 */
	protected String getArg(String paramKey) {
		return this.argsMap.get(paramKey);
	}

	/**
	 * 分裂参数内容，只支持英文逗号(,)
	 * 
	 * @param paramKey
	 *            参数键，支持内容请看CmdArgs
	 * @return 分裂后的参数内容
	 * @see CmdArgs
	 */
	protected String[] splitArg(String paramKey) {
		String value = getArg(paramKey);
		if (value.contains(",")) {
			return value.split(",");
		} else {
			return new String[] { value };
		}
	}
}
