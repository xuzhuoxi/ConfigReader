package cfg.cmd;

import java.util.List;
import java.util.Map;

import cfg.AppDefine;
import cfg.serialize.OutputDataFormat;
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

	private OutputDataFormat[] dataFormats = null;

	public CmdDataHandler(Map<String, String> argsMap) {
		super(argsMap);
	}

	@Override
	protected boolean argsVerify() {
		if (!super.argsVerify()) {
			return false;
		}
		if (!this.hasArg(CmdArgs.KEY_DATAOUT)) {
			System.err.println("Params \"-DataOut\"  is must.");
			return false;
		}
		return true;
	}

	@Override
	protected void handleArgs() {
		super.handleArgs();
		String[] dataOut = this.splitArg(CmdArgs.KEY_DATAOUT);
		OutputDataFormat[] dataOutObjArr = new OutputDataFormat[dataOut.length];
		for (int i = 0; i < dataOut.length; i++) {
			dataOutObjArr[i] = OutputDataFormat.from(dataOut[i]);
		}
		this.dataFormats = dataOutObjArr;
	}

	@Override
	protected void doExecute() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + this.sourcePath;
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();
		for (SheetInfo sheetInfo : sheets) {
			for (OutputDataFormat dataFormat : dataFormats) {
				SerializeDataUtil.serializeData(sheetInfo, dataFormat, this.fieldRangeType,
						this.targetPath + "/data/" + this.fieldRangeType.getValue(), dataFormat.getFieldKey());
			}
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
	 *            -Field(必要)<br>
	 *            配置字段选择输出数据，支持client，server，db<br>
	 * 
	 *            -DataOut(当OutType＝data时)<br>
	 *            输出数据，支持json，binary，sql，多个间用英文逗号(,)分隔 <br>
	 */
	public static void main(String[] args) {
		Map<String, String> argsMap = CmdArgs.createArgsMap(args);
		new CmdDataHandler(argsMap).execute();
	}

}
