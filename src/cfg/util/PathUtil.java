package cfg.util;

import cfg.AppDefine;

public class PathUtil {
	/**
	 * 处理路径<br>
	 * 1.包含":"，视为完整路径<br>
	 * 2.以"/"开头，视为完整路径<br>
	 * 4其它，在前面补上 当前路径+"/
	 * 
	 * @param path
	 *            传入的路径
	 * @return 处理后的路径信息
	 */
	public static String handPath(String path) {
		if (path.contains(":")) {
			return path;
		}
		if (path.startsWith("/")) {
			return path;
		}
		return AppDefine.instance.getBasePath() + "/" + path;
	}
}
