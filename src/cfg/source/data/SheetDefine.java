package cfg.source.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cfg.serialize.AttributeDataType;
import cfg.settings.Settings;
import cfg.source.WorkbookUtil;

public class SheetDefine {

	private int maxColLength;// 字段个数
	private String serverClassName;// server导出类类名
	private String serverDataFileName;// server数据文件名
	private String sqlTableName;// sql表名
	private String sqlFileName;// sql脚本文件名
	private String clientClassName;// client导出类类名
	private String clientDataFileName;// client数据文件名

	private int[] keys;// 主键索引
	private String[] names;// 字段名
	private String[] remarks;// 字段注释
	private Integer[] clientValidIndexs;// client字段索引
	private Integer[] serverValidIndexs;// server字段索引
	private Map<String, String[]> fieldNameMap;
	private String[] dataTypes;// 每个字段对应的数据类型

	private AttributeDataType[] dataTypeInstances;

	private SheetDefine() {
		super();
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
	 * server导出类类名
	 * 
	 * @return
	 */
	public String getServerClassName() {
		return serverClassName;
	}

	/**
	 * server数据文件名
	 * 
	 * @return
	 */
	public String getServerDataFileName() {
		return serverDataFileName;
	}

	/**
	 * sql表名
	 * 
	 * @return
	 */
	public String getSqlTableName() {
		return sqlTableName;
	}

	/**
	 * sql脚本文件名
	 * 
	 * @return
	 */
	public String getSqlFileName() {
		return sqlFileName;
	}

	/**
	 * client导出类类名
	 * 
	 * @return
	 */
	public String getClientClassName() {
		return clientClassName;
	}

	/**
	 * client数据文件名
	 * 
	 * @return
	 */
	public String getClientDataFileName() {
		return clientDataFileName;
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
	 * client字段索引
	 * 
	 * @return
	 */
	public Integer[] getClientValidIndexs() {
		return clientValidIndexs.clone();
	}

	/**
	 * server字段索引
	 * 
	 * @return
	 */
	public Integer[] getServerValidIndexs() {
		return serverValidIndexs.clone();
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
	 * @return
	 */
	public String getDataType(int index) {
		return dataTypes[index];
	}

	/**
	 * 取每个字段对应的Java类型定义
	 * 
	 * @return
	 */
	public AttributeDataType[] getDataTypeInstances() {
		return dataTypeInstances;
	}

	/**
	 * 对单个字段对应的Java类型定义
	 * 
	 * @param index
	 *            索引
	 * @return
	 */
	public AttributeDataType getDataTypeInstance(int index) {
		return dataTypeInstances[index];
	}

	public static SheetDefine parse(Sheet sheet, Settings settings) {
		SheetDefine define = new SheetDefine();
		Row nameRow = sheet.getRow(settings.getNameRowIndex());
		int len = nameRow.getLastCellNum();
		define.maxColLength = len;
		// System.out.println("\nNameRow");
		define.names = WorkbookUtil.getContentArray(sheet, settings.getNameRowIndex(), len);

		// System.out.println("\ntServerOutRow");
		String[] serverOut = WorkbookUtil.getContentArray(sheet, settings.getServerOutRowIndex(), 5);
		// System.out.println("\nClientOutRow");
		String[] clientOut = WorkbookUtil.getContentArray(sheet, settings.getClientOutRowIndex(), 3);
		define.serverClassName = serverOut[1].trim();
		define.serverDataFileName = serverOut[2].trim();
		define.sqlTableName = serverOut[3].trim();
		define.sqlFileName = serverOut[4].trim();
		define.clientClassName = clientOut[1].trim();
		define.clientDataFileName = clientOut[2].trim();

		// System.out.println("\nRemarkRow");
		define.remarks = WorkbookUtil.getContentArray(sheet, settings.getRemarkRowIndex(), len);

		// System.out.println("\nValidRow");
		int validRowIndex = settings.getValidRowIndex();
		String[] validStrAry = WorkbookUtil.getContentArray(sheet, validRowIndex, len);
		handleValidData(define, validStrAry, settings.getValidRowIndex());

		Map<String, Integer> fieldNameIndexMap = settings.getFieldNameRowNumMap();
		handlFieldNameMap(define, sheet, fieldNameIndexMap, len);

		// System.out.println("\nDataTypeRow");
		define.dataTypes = WorkbookUtil.getContentArray(sheet, settings.getDataTypeRowIndex(), len);
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

	private static void handleValidData(SheetDefine define, String[] validStrAry, int rowIndex) {
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
			client = str.charAt(0);
			server = str.charAt(2);
			if ('0' != client) {
				clientList.add(index);
			}
			if ('0' != server) {
				serverList.add(index);
			}
			index++;
		}
		Integer[] c = new Integer[clientList.size()];
		define.clientValidIndexs = clientList.toArray(c);
		Integer[] s = new Integer[serverList.size()];
		define.clientValidIndexs = serverList.toArray(s);
	}

	private static void handleDataTypeInstances(SheetDefine define, String[] dataTypes) {
		AttributeDataType[] instances = new AttributeDataType[dataTypes.length];
		for (int i = 0; i < dataTypes.length; i++) {
			instances[i] = AttributeDataType.from(dataTypes[i]);
		}
		define.dataTypeInstances = instances;
	}
}
