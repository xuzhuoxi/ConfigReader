package cfg.settings.lang;

/**
 * 文件模板中使用的替换定义
 * 
 * @author xuzhuoxi
 *
 */
public class LangTempKey {
	/**
	 * 模块名、包名、命名空间
	 */
	public static final String KEY_MODULE = "${MODULE}";
	/**
	 * 类名
	 */
	public static final String KEY_CLASS_NAME = "${CLASS}";
	/**
	 * 作者
	 */
	public static final String KEY_AUTHOR = "${AUTHOR}";
	/**
	 * 当前时间
	 */
	public static final String KEY_DATE = "${DATE}";

	// -----------------------------------------------------------------------------------

	/**
	 * 属性名
	 */
	public static final String KEY_PROPETY = "${PROPERTY}";
	/**
	 * 属性名(首字母大写)
	 */
	public static final String KEY_PROPERTY_UP = "${UP_PROPERTY}";
	/**
	 * Json属性名
	 */
	public static final String KEY_PROPETY_JSON = "${PROPERTY_JSON}";
	/**
	 * 属性名说明
	 */
	public static final String KEY_PROPERTY_NAME = "${PROPERTY_NAME}";
	/**
	 * 属性注释内容
	 */
	public static final String KEY_PROPETY_REMARK = "${PROPETY_REMARK}";
	/**
	 * 属性数据类型
	 */
	public static final String KEY_PROPETY_DATA_TYPE = "${PROPETY_DATA_TYPE}";
	/**
	 * 解释数据方法
	 */
	public static final String KEY_LANG_FUNCTION_PARSE = "${LANG_PARSE}";

	// -----------------------------------------------------------------------------------
	/**
	 * 字段声明
	 */
	public static final String KEY_CONTENT_PROPERTY_FIELD = "${PROPERTY_FIELD}";
	/**
	 * 字段读取
	 */
	public static final String KEY_CONTENT_PROPERTY_FIELD_GET = "${PROPERTY_FIELD_GET}";
	/**
	 * 字段读取2
	 */
	public static final String KEY_CONTENT_PROPERTY_FIELD_GET2 = "${PROPERTY_FIELD_GET2}";
	/**
	 * Json实体解释
	 */
	public static final String KEY_CONTENT_PARSE_JSON = "${PARSE_JSON_CONTENT}";
	/**
	 * Binary实体解释
	 */
	public static final String KEY_CONTENT_PARSE_BINARY = "${PARSE_BINARY_CONTENT}";

	private LangTempKey() {
		super();
	}
}
