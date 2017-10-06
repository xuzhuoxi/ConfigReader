package cfg.cmd;

import java.util.List;
import java.util.Map;

import cfg.AppDefine;
import cfg.serialize.FieldRangeType;
import cfg.serialize.OutputDefineLangType;
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

	private OutputDefineLangType[] defineLangs = null;

	public CmdDefineHandler(Map<String, String> argsMap) {
		super(argsMap);
	}

	@Override
	protected boolean argsVerify() {
		if (!super.argsVerify()) {
			return false;
		}
		if (!this.hasArg(CmdArgs.KEY_DEFINEOUT)) {
			System.err.println("Params \"-DefineOut\"  is must.");
			return false;
		}
		return true;
	}

	@Override
	protected void handleArgs() {
		super.handleArgs();
		String[] defineOut = this.splitArg(CmdArgs.KEY_DATAOUT);
		OutputDefineLangType[] defineOutObjArr = new OutputDefineLangType[defineOut.length];
		for (int i = 0; i < defineOut.length; i++) {
			defineOutObjArr[i] = OutputDefineLangType.from(defineOut[i]);
		}
		this.defineLangs = defineOutObjArr;
	}

	@Override
	protected void doExecute() {
		String basePath = AppDefine.instance.getBasePath();
		String filePath = basePath + this.sourcePath;
		WorkbookInfo info = new WorkbookInfo(filePath);
		info.loadSheetInfos();
		List<SheetInfo> sheets = info.getSheetInfos();

		for (SheetInfo sheetInfo : sheets) {
			for (OutputDefineLangType defineLang : defineLangs) {
				SerializeDefineUtil.serializeDefine(sheetInfo, this.fieldRangeType, defineLang,
						this.targetPath + "/define/" + this.fieldRangeType.getValue(), defineLang.getExtensionName());
			}

			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, OutputDefineLangType.TypeScript,
					basePath + "/../testres/dist/define/client", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Client, OutputDefineLangType.CSharp,
					basePath + "/../testres/dist/define/client", "cs");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, OutputDefineLangType.Java,
					basePath + "/../testres/dist/define/server", "java");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, OutputDefineLangType.TypeScript,
					basePath + "/../testres/dist/define/server", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.Server, OutputDefineLangType.CSharp,
					basePath + "/../testres/dist/define/server", "cs");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.DB, OutputDefineLangType.Java,
					basePath + "/../testres/dist/define/db", "java");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.DB, OutputDefineLangType.TypeScript,
					basePath + "/../testres/dist/define/db", "ts");
			SerializeDefineUtil.serializeDefine(sheetInfo, FieldRangeType.DB, OutputDefineLangType.CSharp,
					basePath + "/../testres/dist/define/db", "cs");
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
	 *            -DefineOut(当OutType＝define时)<br>
	 *            定义语言选择，支持java，ts，多个间用英文逗号(,)分隔 <br>
	 */
	public static void main(String[] args) {
		Map<String, String> argsMap = CmdArgs.createArgsMap(args);
		new CmdDefineHandler(argsMap).execute();
	}
}
