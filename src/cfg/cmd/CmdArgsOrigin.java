package cfg.cmd;

import java.util.HashMap;
import java.util.Map;

import cfg.settings.Settings;

/**
 * 命令行参数定义
 * 
 * @author xuzhuoxi<br>
 *         create on 2017年9月4日.<br>
 */
public class CmdArgsOrigin {

	/**
	 * 原始参数表
	 */
	public final Map<String, String> argsMap;

	private CmdArgsOrigin(Map<String, String> originArgsMap) {
		super();
		this.argsMap = originArgsMap;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Origin:\n");
		for (String key : argsMap.keySet()) {
			sb.append(key + ":" + argsMap.get(key) + "\n");
		}
		return "CmdArgs [" + sb.toString() + "]";
	}

	/**
	 * 生成参数映射表
	 * 
	 * @param args
	 *            参数列表长度必须为偶数，并且前一位为Key，后一位为Value
	 * @return 参数映射表
	 */
	public static CmdArgsOrigin createArgsMap(String[] args) {
//		System.out.println("打印，Len=" + args.length);
		Map<String, String> argsMap = new HashMap<String, String>();
		for (int index = 0; index < args.length; index += 2) {
			argsMap.put(args[index], args[index + 1]);
//			System.out.println(args[index] + "=" + args[index + 1]);
		}
//		System.out.println("打印结束");
		Settings settings = Settings.getInstance();
		if (!argsMap.containsKey(CmdArgKeys.KEY_SOURCE)) {
			argsMap.put(CmdArgKeys.KEY_SOURCE, settings.getSysSettings().getSource());
		}
		if (!argsMap.containsKey(CmdArgKeys.KEY_TARGET)) {
			argsMap.put(CmdArgKeys.KEY_TARGET, settings.getSysSettings().getTarget());
		}
		CmdArgsOrigin rs = new CmdArgsOrigin(argsMap);
		return rs;
	}
}
