package cfg.source.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cfg.serialize.FieldDataFormat;
import cfg.serialize.FieldRangeType;
import cfg.serialize.exceptions.SheetDefineException;
import cfg.settings.ProjectSettings;
import cfg.settings.Settings;
import cfg.source.WorkbookUtil;

public class SheetDefine {
	private String sheetNamed;
	private int maxColLength;// 字段个数

	private int[] keys;// 主键索引(未使用)
	private Map<FieldRangeType, SheetValidInfo> infoMap = new HashMap<FieldRangeType, SheetValidInfo>();
	private String[] names;// 字段名
	private String[] remarks;// 字段注释
	private Map<String, String[]> fieldNameMap;
	private String[] dataTypes;// 每个字段对应的数据类型

	private FieldDataFormat[] dataTypeInstances;

	private SheetDefine(String sheetNamed) {
		super();
		this.sheetNamed = sheetNamed;
		this.infoMap.put(FieldRangeType.Client, new SheetValidInfo());
		this.infoMap.put(FieldRangeType.Server, new SheetValidInfo());
		this.infoMap.put(FieldRangeType.DB, new SheetValidInfo());
	}

	/**
	 * Excel表格的Sheet名称
	 * 
	 * @return 表上Sheet的名称
	 */
	public String getSheetNamed() {
		return this.sheetNamed;
	}

	/**
	 * 检查有效字段的数量是否大于0
	 * 
	 * @param fieldRangeType
	 *            字段范围类型
	 * @see FieldRangeType
	 * @return 大于0返回true，否则返回false
	 */
	public boolean isFieldRangeEmpty(FieldRangeType fieldRangeType) {
		return infoMap.get(fieldRangeType).getValidIndexCount() <= 0;
	}

	/**
	 * 字段总数量<br>
	 * 当前值由表中的字段名称数据决定
	 * 
	 * @return 字段有效索引长度(总数量)
	 */
	public int getMaxColLength() {
		return maxColLength;
	}

	/**
	 * 作为主键字段索引
	 * 
	 * @return 字段索引数组
	 */
	public int[] getKeys() {
		return keys;
	}

	/**
	 * 取对应字段的名称
	 * 
	 * @param index
	 *            索引
	 * @return 字段的名称
	 */
	public String getName(int index) {
		return names[index];
	}

	/**
	 * 取对应字段的注释
	 * 
	 * @param index
	 *            索引
	 * @return 字段的注释
	 */
	public String getRemark(int index) {
		return remarks[index];
	}

	/**
	 * 取对应语言的字段导出属性名
	 * 
	 * @param lang
	 *            语言
	 * @return 字段属性名数组
	 */
	public String[] getFieldNameArray(String lang) {
		return fieldNameMap.get(lang).clone();
	}

	/**
	 * 取字段导出属性名
	 * 
	 * @param lang
	 *            语言
	 * @param index
	 *            字段索引
	 * @return 字段属性名
	 */
	public String getFieldName(String lang, int index) {
		return fieldNameMap.get(lang)[index];
	}

	/**
	 * 取每个字段对应的数据类型
	 * 
	 * @return 配置表上全部原始的数据类型字符串
	 */
	public String[] getDataTypes() {
		return dataTypes;
	}

	/**
	 * 取单个字段对应的数据类型
	 * 
	 * @param index
	 *            字段索引
	 * @return 配置表上原始的数据类型字符串
	 */
	public String getDataType(int index) {
		return dataTypes[index];
	}

	/**
	 * 取每个字段对应的Java类型定义
	 * 
	 * @return 字段的数据格式
	 */
	public FieldDataFormat[] getDataTypeInstances() {
		return dataTypeInstances;
	}

	/**
	 * 对单个字段对应的Java类型定义
	 * 
	 * @param index
	 *            字段索引
	 * @return 字段的数据格式
	 */
	public FieldDataFormat getDataTypeInstance(int index) {
		return dataTypeInstances[index];
	}

	/**
	 * 取导出数据文件名
	 * 
	 * @param fieldRange
	 *            导出的字段范围类型
	 * @return 导出的字段范围类型有效字段索引数据
	 */
	public SheetValidInfo getExportInfo(FieldRangeType fieldRange) {
		return this.infoMap.get(fieldRange);
	}

	public static SheetDefine parse(Sheet sheet) throws SheetDefineException {
		SheetDefine define = new SheetDefine(sheet.getSheetName());
		Settings settings = Settings.getInstance();
		ProjectSettings prjectSettings = settings.getProjectSettings();
		Row nameRow = sheet.getRow(prjectSettings.getNameRowIndex());
		int len = nameRow.getLastCellNum();
		define.maxColLength = len;
		// System.out.println("\nNameRow");
		define.names = WorkbookUtil.getContentArray(sheet, prjectSettings.getNameRowIndex(), len);
		// System.out.println("\nRemarkRow");
		define.remarks = WorkbookUtil.getContentArray(sheet, prjectSettings.getRemarkRowIndex(), len);

		// System.out.println("\nClientOutRow" +
		// prjectSettings.getClientOutRowIndex());
		String[] clientOut = WorkbookUtil.getContentArray(sheet, prjectSettings.getClientOutRowIndex(), 3);
		// System.out.println("\nServerOutRow" +
		// prjectSettings.getServerOutRowIndex());
		String[] serverOut = WorkbookUtil.getContentArray(sheet, prjectSettings.getServerOutRowIndex(), 3);
		// System.out.println("\nDbOutRow" + prjectSettings.getDbOutRowIndex());
		String[] dbOut = WorkbookUtil.getContentArray(sheet, prjectSettings.getDbOutRowIndex(), 3);
		String clientClassName = clientOut[1].trim();
		String clientDataFileName = clientOut[2].trim();
		String serverClassName = serverOut[1].trim();
		String serverDataFileName = serverOut[2].trim();
		String dbTableName = dbOut[1].trim();
		String dbFileName = dbOut[2].trim();
		// System.out.println("\nValidRow");
		int validRowIndex = prjectSettings.getValidRowIndex();
		String[] validStrAry = WorkbookUtil.getContentArray(sheet, validRowIndex, len);
		Integer[][] valids = new Integer[3][];// 0为client, 1为server, 2为db
		handleValidData(define, validStrAry, validRowIndex, valids);
		define.infoMap.get(FieldRangeType.Client).setInfo(clientClassName, clientDataFileName, valids[0]);
		define.infoMap.get(FieldRangeType.Server).setInfo(serverClassName, serverDataFileName, valids[1]);
		define.infoMap.get(FieldRangeType.DB).setInfo(dbTableName, dbFileName, valids[2]);

		Map<String, Integer> fieldNameIndexMap = prjectSettings.getFieldNameRowNumMap();
		handlFieldNameMap(define, sheet, fieldNameIndexMap, len);

		// System.out.println("\nDataTypeRow");
		define.dataTypes = WorkbookUtil.getContentArray(sheet, prjectSettings.getDataTypeRowIndex(), len);
		handleDataTypeInstances(define, define.dataTypes);
		return define;
	}

	private static void handlFieldNameMap(SheetDefine define, Sheet sheet, Map<String, Integer> map, int len) {
		Map<String, String[]> fieldNameMap = new HashMap<String, String[]>();
		int rowIndex;
		for (String key : map.keySet()) {
			rowIndex = map.get(key);
			fieldNameMap.put(key, WorkbookUtil.getContentArray(sheet, rowIndex, len));
		}
		define.fieldNameMap = fieldNameMap;
	}

	private static void handleValidData(SheetDefine define, String[] validStrAry, int rowIndex, Integer[][] valids) {
		List<Integer> clientList = new ArrayList<Integer>();
		List<Integer> serverList = new ArrayList<Integer>();
		List<Integer> dbList = new ArrayList<Integer>();
		char client;
		char server;
		char db;
		char nameBaseChar = 'A';
		int index = 0;
		for (String str : validStrAry) {
			if (str.length() == 0) {
				index++;
				continue;
			}
			if (str.length() != 5) {
				throw new Error("Format Error At : (" + (rowIndex + 1) + "," + (nameBaseChar + index) + ")");
			}
			client = str.charAt(0);
			if ('0' != client) {
				clientList.add(index);
			}
			server = str.charAt(2);
			if ('0' != server) {
				serverList.add(index);
			}
			db = str.charAt(4);
			if ('0' != db) {
				dbList.add(index);
			}
			index++;
		}
		Integer[] c = new Integer[clientList.size()];
		valids[0] = clientList.toArray(c);
		Integer[] s = new Integer[serverList.size()];
		valids[1] = serverList.toArray(s);
		Integer[] d = new Integer[dbList.size()];
		valids[2] = dbList.toArray(d);
	}

	private static void handleDataTypeInstances(SheetDefine define, String[] dataTypes) throws SheetDefineException {
		FieldDataFormat[] instances = new FieldDataFormat[dataTypes.length];
		int i = 0;
		for (i = 0; i < dataTypes.length; i++) {
			try {
				instances[i] = FieldDataFormat.from(dataTypes[i]);
			} catch (Exception e) {
				throw new SheetDefineException("Sheet(\"" + define.sheetNamed + "\") DataType Define Error In Column: "
						+ i + ". Value is \"" + dataTypes[i] + "\".");
			}
		}
		define.dataTypeInstances = instances;
	}
}
