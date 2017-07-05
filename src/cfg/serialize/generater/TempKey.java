package cfg.serialize.generater;

/**
 * 文件模板中使用的替换定义
 * 
 * @author xuzhuoxi
 *
 */
public class TempKey {
	/**
	 * 模块名、包名、命名空间
	 */
	public static final String KEY_MODULE = "${MODULE}";

	/**
	 * 类名
	 */
	public static final String KEY_CLASS_NAME = "${CLASS}";
	/**
	 * 属性名说明
	 */
	public static final String KEY_PROPERTY_NAME = "${PROPERTY_NAME}";
	/**
	 * 属性名
	 */
	public static final String KEY_PROPETY = "${PROPERTY}";
	/**
	 * Json属性名
	 */
	public static final String KEY_PROPETY_JSON = "${PROPERTY_JSON}";
	/**
	 * 属性名(首字母大写)
	 */
	public static final String KEY_PROPERTY_UP = "${UP_PROPERTY}";
	/**
	 * 注释内容
	 */
	public static final String KEY_REMARK = "${REMARK}";
	/**
	 * 数据类型
	 */
	public static final String KEY_DATA_TYPE = "${DATA_TYPE}";
	/**
	 * 作者
	 */
	public static final String KEY_AUTHOR = "${AUTHOR}";
	/**
	 * 当前时间
	 */
	public static final String KEY_DATE = "${DATE}";

	/**
	 * 通用分隔符
	 */
	public static final String KEY_CONNOM_SEPARATOR = "${SEPARATOR}";

	/**
	 * 类实体
	 */
	public static final String KEY_CONTENT_CLASS = "${CLASS_CONTENT}";
	/**
	 * 属性实体
	 */
	public static final String KEY_CONTENT_PROPERTY = "${PROPERTY_CONTENT}";
	/**
	 * Json实体解释
	 */
	public static final String KEY_CONTENT_PARSE_JSON = "${PARSE_JSON_CONTENT}";

	private TempKey() {
		super();
	}
}
