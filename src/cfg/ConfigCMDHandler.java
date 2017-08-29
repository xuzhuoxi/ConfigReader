package cfg;

public class ConfigCMDHandler {
	private String settingPath;// 工具配置文件路径
	private String sourcePath;// 配置表路径 或 配置表文件夹路径
	private String targetFolder;// 输入文件路径

	/**
	 * @param args
	 *            -Source (非必要，可以直接在project.json中配置)<br>
	 *            配置表文件路径，字符串，支持文件或文件夹<br>
	 * 
	 *            -Target (非必要，可以直接在project.json中配置)<br>
	 *            输出文件夹路径，支持文件夹<br>
	 * 
	 *            -OutType(必要)<br>
	 *            输出类型，支持data、define两种
	 * 
	 *            -Field(必要)<br>
	 *            配置字段选择输出数据，支持client，server，db，多个间用英文逗号(,)分隔 <br>
	 * 
	 *            -DataOut(当OutType＝data时)<br>
	 *            输出数据，支持json，binary，sql，多个间用英文逗号(,)分隔 <br>
	 * 
	 *            -DefineOut(当OutType＝define时)<br>
	 *            定义语言选择，支持java，ts，多个间用英文逗号(,)分隔 <br>
	 */
	public static void main(String[] args) {
		int groupLen = args.length / 2;
		for (int index = 0; index < groupLen; index++) {

		}
	}
}
