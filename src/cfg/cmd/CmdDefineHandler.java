package cfg.cmd;

import java.util.List;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDefineLangType;
import cfg.serialize.OutputType;
import cfg.serialize.SerializeDefineUtil;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;

/**
 * 编程语言定义生成命令
 * 
 * @author xuzhuoxi<br>
 *         create on 2017年9月4日.<br>
 */
public class CmdDefineHandler extends CmdHandlerBase {

	public CmdDefineHandler(CmdArgsRuntime cmdArgs) {
		super(cmdArgs);
	}

	@Override
	protected void handleArgs() {
		super.handleArgs();
		String[] defineOut = this.splitArg(CmdArgKeys.KEY_DEFINEOUT);
		for (int i = 0; i < defineOut.length; i++) {
			this.runtimeArgs.getDefineLangs().add(OutputDefineLangType.from(defineOut[i]));
		}
	}

	@Override
	protected boolean argsVerify() {
		if (!super.argsVerify()) {
			return false;
		}
		if (!this.runtimeArgs.hasOutputDefineLangType()) {
			System.err.println("Params \"-DefineOut\"  is must.");
			return false;
		}
		return true;
	}

	@Override
	protected void doExecute() {
		String filePath = this.runtimeArgs.getSourcePath();
		String targetFolder = this.runtimeArgs.getTargetPath();
		List<OutputDefineLangType> defineLangs = this.runtimeArgs.getDefineLangs();
		FieldRangeType fieldRangeType = this.runtimeArgs.getFieldRangeType();

		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();

		for (OutputDefineLangType defineLang : defineLangs) {
			SerializeDefineUtil.serializeDefine(sheets, fieldRangeType, defineLang,
					targetFolder + "/define/" + fieldRangeType.getValue(), defineLang.getExtensionName());
		}
	}

	/**
	 * @param args
	 *            -Source (非必要，可以直接在project.json中配置)<br>
	 *            配置表文件路径，字符串，支持文件或文件夹<br>
	 * 
	 *            -Target (非必要，可以直接在project.json中配置)<br>
	 *            输出文件夹路径，支持文件夹<br>
	 * 
	 *            -Field (必要)<br>
	 *            配置字段选择输出数据，支持client，server，db<br>
	 * 
	 *            -DefineOut (当OutType＝define时)<br>
	 *            定义语言选择，支持java，ts，多个间用英文逗号(,)分隔 <br>
	 */
	public static void main(String[] args) {
		CmdArgsRuntime cmdArgsRuntime = CmdArgsRuntime.createArgsMap(args);
		cmdArgsRuntime.setOutType(OutputType.Define);
		new CmdDefineHandler(cmdArgsRuntime).execute();
	}
}
