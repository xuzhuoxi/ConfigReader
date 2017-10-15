package cfg.cmd;

import java.util.List;

import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDataFormat;
import cfg.serialize.OutputType;
import cfg.serialize.SerializeDataUtil;
import cfg.source.WorkbookInfo;
import cfg.source.data.SheetInfo;

/**
 * 数据生成命令
 * 
 * @author xuzhuoxi<br>
 *         create on 2017年9月4日.<br>
 */
public class CmdDataHandler extends CmdHandlerBase {

	public CmdDataHandler(CmdArgsRuntime cmdArgs) {
		super(cmdArgs);
	}

	@Override
	protected void handleArgs() {
		super.handleArgs();
		String[] dataOut = this.splitArg(CmdArgKeys.KEY_DATAOUT);
		for (int i = 0; i < dataOut.length; i++) {
			this.runtimeArgs.getDataFormats().add(OutputDataFormat.from(dataOut[i]));
		}
	}

	@Override
	protected boolean argsVerify() {
		if (!super.argsVerify()) {
			return false;
		}
		if (!this.runtimeArgs.hasOutputDataFormat()) {
			System.err.println("Params \"-DataOut\"  is must.");
			return false;
		}
		return true;
	}

	@Override
	protected void doExecute() {
		String filePath = this.runtimeArgs.getSourcePath();
		String targetFolder = this.runtimeArgs.getTargetPath();
		List<OutputDataFormat> dataFormats = this.runtimeArgs.getDataFormats();
		FieldRangeType fieldRangeType = this.runtimeArgs.getFieldRangeType();

		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (OutputDataFormat dataFormat : dataFormats) {
			SerializeDataUtil.serializeData(sheets, dataFormat, fieldRangeType,
					targetFolder + "/data/" + fieldRangeType.getValue(), dataFormat.getFieldKey());
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
	 *            -DataOut (当OutType＝data时)<br>
	 *            输出数据，支持json，binary，sql，多个间用英文逗号(,)分隔 <br>
	 */
	public static void main(String[] args) {
		CmdArgsRuntime cmdArgsRuntime = CmdArgsRuntime.createArgsMap(args);
		cmdArgsRuntime.setOutType(OutputType.Data);
		new CmdDataHandler(cmdArgsRuntime).execute();
	}

}
