package cfg.source.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cfg.serialize.FieldDataFormat;
import cfg.serialize.FieldRangType;
import cfg.settings.ProjectSettings;
import cfg.settings.Settings;
import cfg.source.WorkbookUtil;

public class SheetDefine {

	private int maxColLength;// 字段个数

	private int[] keys;// 主键索引(未使用)
	private Map<FieldRangType, SheetValidInfo> infoMap = new HashMap<FieldRangType, SheetValidInfo>();
	private String[] names;// 字段名
	private String[] remarks;// 字段注释
	private Map<String, String[]> fieldNameMap;
	private String[] dataTypes;// 每个字段对应的数据类型

	private FieldDataFormat[] dataTypeInstances;

	private SheetDefine() {
		super();
		this.infoMap.put(FieldRangType.Server, new SheetValidInfo());
		this.infoMap.put(FieldRangType.Client, new SheetValidInfo());
		this.infoMap.put(FieldRangType.Sql, new SheetValidInfo());
	}

	/**
	 * 字段总数量
	 * 
	 * @return
	 */
	public int getMaxColLength() {
		return maxColLength;
	}

	/**
	 * 主键索引
	 * 
	 * @return
	 */
	public int[] getKeys() {
		return keys;
	}

	/**
	 * 取对应字段的名称
	 * 
	 * @param index
	 *            索引
	 * @return
	 */
	public String getName(int index) {
		return names[index];
	}

	/**
	 * 取对应字段的注释
	 * 
	 * @param index
	 *            索引
	 * @return
	 */
	public String getRemark(int index) {
		return remarks[index];
	}

	/**
	 * 取对应语言的字段导出属性名
	 * 
	 * @param lang
	 *            语言
	 * @return
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
	 *            索引
	 * @return
	 */
	public String getFieldName(String lang, int index) {
		return fieldNameMap.get(lang)[index];
	}

	/**
	 * 取每个字段对应的数据类型
	 * 
	 * @return
	 */
	public String[] getDataTypes() {
		return dataTypes;
	}

	/**
	 * 取单个字段对应的数据类型
	 * 
	 * @param index
	 * @return 配置表上原始的数据类型字符串
	 */
	public String getDataType(int index) {
		return dataTypes[index];
	}

	/**
	 * 取每个字段对应的Java类型定义
	 * 
	 * @return
	 */
	public FieldDataFormat[] getDataTypeInstances() {
		return dataTypeInstances;
	}

	/**
	 * 对单个字段对应的Java类型定义
	 * 
	 * @param index
	 *            索引
	 * @return
	 */
	public FieldDataFormat getDataTypeInstance(int index) {
		return dataTypeInstances[index];
	}

	/**
	 * 取导出数据文件名
	 * 
	 * @param projectType
	 * @return
	 */
	public SheetValidInfo getExportInfo(FieldRangType projectType) {
		return this.infoMap.get(projectType);
	}

	public static SheetDefine parse(Sheet sheet) {
		SheetDefine define = new SheetDefine();
		Settings settings = Settings.getInstance();
		ProjectSettings prjectSettings = settings.getProjectSettings();
		Row nameRow = sheet.getRow(prjectSettings.getNameRowIndex());
		int len = nameRow.getLastCellNum();
		define.maxColLength = len;
		// System.out.println("\nNameRow");
		define.names = WorkbookUtil.getContentArray(sheet, prjectSettings.getNameRowIndex(), len);
		// System.out.println("\nRemarkRow");
		define.remarks = WorkbookUtil.getContentArray(sheet, prjectSettings.getRemarkRowIndex(), len);

		// System.out.println("\ntServerOutRow");
		String[] serverOut = WorkbookUtil.getContentArray(sheet, prjectSettings.getServerOutRowIndex(), 5);
		// System.out.println("\nClientOutRow");
		String[] clientOut = WorkbookUtil.getContentArray(sheet, prjectSettings.getClientOutRowIndex(), 3);
		String serverClassName = serverOut[1].trim();
		String serverDataFileName = serverOut[2].trim();
		String sqlTableName = serverOut[3].trim();
		String sqlFileName = serverOut[4].trim();
		String clientClassName = clientOut[1].trim();
		String clientDataFileName = clientOut[2].trim();
		// System.out.println("\nValidRow");
		int validRowIndex = prjectSettings.getValidRowIndex();
		String[] validStrAry = WorkbookUtil.getContentArray(sheet, validRowIndex, len);
		Integer[][] valids = new Integer[2][];// 0为server, 1为client,
		handleValidData(define, validStrAry, prjectSettings.getValidRowIndex(), valids);
		define.infoMap.get(FieldRangType.Server).setInfo(serverClassName, serverDataFileName, valids[0]);
		define.infoMap.get(FieldRangType.Client).setInfo(clientClassName, clientDataFileName, valids[1]);
		define.infoMap.get(FieldRangType.Sql).setInfo(sqlTableName, sqlFileName, valids[0]);

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
		char client;
		char server;
		char nameBaseChar = 'A';
		int index = 0;
		for (String str : validStrAry) {
			if (str.length() == 0) {
				index++;
				continue;
			}
			if (str.length() != 3) {
				throw new Error("Format Error At : (" + (rowIndex + 1) + "," + (nameBaseChar + index) + ")");
			}
			server = str.charAt(0);
			client = str.charAt(2);
			if ('0' != client) {
				clientList.add(index);
			}
			if ('0' != server) {
				serverList.add(index);
			}
			index++;
		}
		Integer[] s = new Integer[serverList.size()];
		valids[0] = serverList.toArray(s);
		Integer[] c = new Integer[clientList.size()];
		valids[1] = clientList.toArray(c);
	}

	private static void handleDataTypeInstances(SheetDefine define, String[] dataTypes) {
		FieldDataFormat[] instances = new FieldDataFormat[dataTypes.length];
		for (int i = 0; i < dataTypes.length; i++) {
			instances[i] = FieldDataFormat.from(dataTypes[i]);
		}
		define.dataTypeInstances = instances;
	}
}
