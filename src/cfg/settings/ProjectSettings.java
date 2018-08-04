package cfg.settings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import code.file.FileUtils;
import code.math.NumberSystemUtil;

public class ProjectSettings {
	protected int[] clientDefineLoc;
	protected int[] clientDataLoc;
	protected int[] serverDefineLoc;
	protected int[] serverDataLoc;
	protected int[] dbDefineLoc;
	protected int[] dbDataLoc;
	
	protected int[] dataKeyLoc;

	protected int nameRowIndex;
	protected int remarkRowIndex;
	protected int validRowIndex;

	protected int dataTypeRowIndex;
	protected int startRowIndex;

	protected Map<String, Integer> fieldNameRowIndexMap;

	/**
	 * 字段范围(客户端)
	 * 定义文件名的单元格坐标，格式：[col,row]; col,row从1开始
	 * 
	 * @return 单元格坐标
	 */
	public int[] getClientDefineLoc() {
		return clientDefineLoc;
	}

	/**
	 * 字段范围(客户端)
	 * 数据文件名的单元格坐标，格式：[col,row]; col,row从1开始
	 * 
	 * @return 单元格坐标
	 */
	public int[] getClientDataLoc() {
		return clientDataLoc;
	}

	/**
	 * 字段范围(服务端)
	 * 定义文件名的单元格坐标，格式：[col,row]; col,row从1开始
	 * 
	 * @return 单元格坐标
	 */
	public int[] getServerDefineLoc() {
		return serverDefineLoc;
	}

	/**
	 * 字段范围(服务端)
	 * 数据文件名的单元格坐标，格式：[col,row]; col,row从1开始
	 * 
	 * @return 单元格坐标
	 */
	public int[] getServerDataLoc() {
		return serverDataLoc;
	}

	/**
	 * 字段范围(DB)
	 * 定义文件名的单元格坐标，格式：[col,row]; col,row从1开始
	 * 
	 * @return 单元格坐标
	 */
	public int[] getDbDefineLoc() {
		return dbDefineLoc;
	}

	/**
	 * 字段范围(DB)
	 * 数据文件名的单元格坐标，格式：[col,row]; col,row从1开始
	 * 
	 * @return 单元格坐标
	 */
	public int[] getDbDataLoc() {
		return dbDataLoc;
	}

	/**
	 * 数据主键格式的单元格坐标，格式：[col,row]; col,row从1开始
	 * 
	 * @return 单元格坐标
	 */
	public int[] getDataKeyLoc() {
		return dataKeyLoc;
	}

	/**
	 * 数据名称行。
	 * 
	 * @return 自然数，对应project.json文件的Name.value值减1
	 */
	public int getNameRowIndex() {
		return nameRowIndex;
	}

	/**
	 * 数据注释行。
	 * 
	 * @return 自然数，对应project.json文件的Remark.value值减1
	 */
	public int getRemarkRowIndex() {
		return remarkRowIndex;
	}

	/**
	 * 输出选择行，格式: 'c,s,d'。
	 * 
	 * @return 自然数，对应project.json文件的Valid.value值减1
	 */
	public int getValidRowIndex() {
		return validRowIndex;
	}

	/**
	 * 数据格式行
	 * 
	 * @return 自然数，对应project.json文件的DataType.value值减1
	 */
	public int getDataTypeRowIndex() {
		return dataTypeRowIndex;
	}

	/**
	 * 数据开始行
	 * 
	 * @return 自然数，对应project.json文件的StartRow.value值减1
	 */
	public int getStartRowIndex() {
		return startRowIndex;
	}

	/**
	 * 导出定义时对应的字段名、属性名或key。(db)为数据库字段名，(json)为key，(java,ts,c++,c#)为属性名
	 * 
	 * @return 自然数，对应project.json文件的FieldName.value.语言的值减1
	 */
	public Map<String, Integer> getFieldNameRowNumMap() {
		return fieldNameRowIndexMap;
	}

	/**
	 * 导出定义时对应的字段名、属性名或key。(db)为数据库字段名，(json)为key，(java,ts,c++,c#)为属性名
	 * 
	 * @param langNamed
	 *            语言
	 * @return 自然数，对应project.json文件的ServerOut.value值减1
	 */
	public int getFieldNameRowIndex(String langNamed) {
		return fieldNameRowIndexMap.get(langNamed);
	}

	public static final ProjectSettings parseByPath(String filePath) {
		String jsonContent = FileUtils.readFileContent(filePath);
		System.out.println(jsonContent);
		return parseByJson(jsonContent);
	}

	@Override
	public String toString() {
		return "ProjectSettings [clientDefineLoc=" + Arrays.toString(clientDefineLoc) + ", clientDataLoc="
				+ Arrays.toString(clientDataLoc) + ", serverDefineLoc=" + Arrays.toString(serverDefineLoc)
				+ ", serverDataLoc=" + Arrays.toString(serverDataLoc) + ", dbTableLoc=" + Arrays.toString(dbDefineLoc)
				+ ", dbSqlLoc=" + Arrays.toString(dbDataLoc) + ", keyLoc=" + Arrays.toString(dataKeyLoc) + ", nameRowIndex="
				+ nameRowIndex + ", remarkRowIndex=" + remarkRowIndex + ", validRowIndex=" + validRowIndex
				+ ", dataTypeRowIndex=" + dataTypeRowIndex + ", startRowIndex=" + startRowIndex
				+ ", fieldNameRowIndexMap=" + fieldNameRowIndexMap + "]";
	}

	public static final ProjectSettings parseByJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		ProjectSettings settings = new ProjectSettings();

		JSONObject configOut = jsonObj.getJSONObject("ConfigOut");
		settings.clientDefineLoc = getLoc(configOut.getString("ClientDefine"));
		settings.clientDataLoc = getLoc(configOut.getString("ClientData"));
		settings.serverDefineLoc = getLoc(configOut.getString("ServerDefine"));
		settings.serverDataLoc = getLoc(configOut.getString("ServerData"));
		settings.dbDefineLoc = getLoc(configOut.getString("DBDefine"));
		settings.dbDataLoc = getLoc(configOut.getString("DBData"));
		settings.dataKeyLoc = getLoc(configOut.getString("DataKey"));

		settings.nameRowIndex = jsonObj.getJSONObject("Name").getInt("value") - 1;
		settings.remarkRowIndex = jsonObj.getJSONObject("Remark").getInt("value") - 1;
		settings.validRowIndex = jsonObj.getJSONObject("Valid").getInt("value") - 1;
		settings.dataTypeRowIndex = jsonObj.getJSONObject("DataType").getInt("value") - 1;
		settings.startRowIndex = jsonObj.getJSONObject("StartRow").getInt("value") - 1;

		JSONObject langMap = jsonObj.getJSONObject("FieldName").getJSONObject("value");
		Map<String, Integer> map = new HashMap<String, Integer>();
		String key = null;
		@SuppressWarnings("rawtypes")
		Iterator iterator = langMap.keys();
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			map.put(key, langMap.getInt(key) - 1);
		}
		settings.fieldNameRowIndexMap = map;
		return settings;
	}

	private static final int[] getLoc(String locStr) {
		String[] info = locStr.split("_");
		int[] rs = new int[2];
		rs[0] = NumberSystemUtil.toNumberSystem10(info[0]);
		rs[1] = Integer.parseInt(info[1]);
		return rs;
	}
}
