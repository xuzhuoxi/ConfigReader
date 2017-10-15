package cfg.cmd;

/**
 * 命令行处理基类<br>
 * 包括参数整合、参数验证、命令执行功能<br>
 * 
 * @author xuzhuoxi<br>
 *         create on 2017年9月4日.<br>
 */
public class CmdHandlerBase {

	protected CmdArgsRuntime runtimeArgs;

	public CmdHandlerBase(CmdArgsRuntime cmdArgs) {
		super();
		this.runtimeArgs = cmdArgs;
	}

	/**
	 * 参数处理
	 */
	protected void handleArgs() {
		return;
	}

	/**
	 * 参数验证
	 * 
	 * @return true:验证通过，false:验证不通过
	 */
	protected boolean argsVerify() {
		if (!this.runtimeArgs.hasOutType()) {
			System.err.println("Params \"-OutType\"  is must.");
			return false;
		}
		if (!this.runtimeArgs.hasFieldRangeType()) {
			System.err.println("Params \"-Field\"  is must.");
			return false;
		}
		return true;
	}

	/**
	 * 命令执行
	 */
	final public void execute() {
		this.handleArgs();
//		System.out.println(this.runtimeArgs.getCmdArgsOrigin().toString());
//		System.out.println(this.runtimeArgs.toString());
		if (!this.argsVerify()) {
			System.err.println("Param Error!");
			return;
		}
		this.doExecute();
	}

	/**
	 * 命令实际执行入口，子类重写
	 */
	protected void doExecute() {
		throw new Error("Not Override!");
	}

	/**
	 * 分裂参数内容，只支持英文逗号(,)
	 * 
	 * @param paramKey
	 *            参数键，支持内容请看CmdArgs
	 * @return 分裂后的参数内容
	 * @see CmdArgsOrigin
	 */
	protected String[] splitArg(String paramKey) {
		String value = this.runtimeArgs.getCmdArgsOrigin().argsMap.get(paramKey);
		if (value.contains(",")) {
			return value.split(",");
		} else {
			return new String[] { value };
		}
	}
}
