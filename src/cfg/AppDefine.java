package cfg;

import code.path.BasePathUtils;

public class AppDefine {
	public static final AppDefine instance = new AppDefine();

	private String basePath;

	public String getBasePath() {
		return basePath;
	}

	private AppDefine() {
		this.basePath = BasePathUtils.getBasePath(this.getClass());
	}
}