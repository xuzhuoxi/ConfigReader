package cfg.settings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import code.file.FileUtils;

public class ProjectSettings {
	protected int serverOutRowIndex;
	protected int clientOutRowIndex;
	protected int dbOutRowIndex;
	protected int nameRowIndex;
	protected int remarkRowIndex;
	protected int validRowIndex;

	protected int dataTypeRowIndex;
	protected int startRowIndex;

	protected Map<String, Integer> fieldNameRowIndexMap;

	/**
	 * 服务端输出信息行，B列为数据结构名，C列为单独数据文件名(不包含扩展名)。
	 * 
	 * @return 自然数，对应project.json文件的ServerOut.value值减1
	 */
	public int getServerOutRowIndex() {
		return serverOutRowIndex;
	}

	/**
	 * 客户端输出信息行，B列为数据结构名，C列为单独数据文件名(不包含扩展名)。
	 * 
	 * @return 自然数，对应project.json文件的ClientOut.value值减1
	 */
	public int getClientOutRowIndex() {
		return clientOutRowIndex;
	}

	/**
	 * 数据库输出信息行，B列为表名，C列为单独数据文件名(不包含扩展名)。
	 * 
	 * @return 自然数，对应project.json文件的DBOut.value值减1
	 */
	public int getDbOutRowIndex() {
		return dbOutRowIndex;
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

	@Override
	public String toString() {
		return "ProjectSettings [serverOutRowIndex=" + serverOutRowIndex + ", clientOutRowIndex=" + clientOutRowIndex
				+ ", dbOutRowIndex=" + dbOutRowIndex + ", nameRowIndex=" + nameRowIndex + ", remarkRowIndex="
				+ remarkRowIndex + ", validRowIndex=" + validRowIndex + ", dataTypeRowIndex=" + dataTypeRowIndex
				+ ", startRowIndex=" + startRowIndex + ", fieldNameRowIndexMap=" + fieldNameRowIndexMap + "]";
	}

	public static final ProjectSettings parseByPath(String filePath) {
		String jsonContent = FileUtils.readFileContent(filePath);
		System.out.println(jsonContent);
		return parseByJson(jsonContent);
	}

	public static final ProjectSettings parseByJson(String json) {
		JSONObject jsonObj = new JSONObject(json);
		ProjectSettings settings = new ProjectSettings();

		settings.clientOutRowIndex = jsonObj.getJSONObject("ClientOut").getInt("value") - 1;
		settings.serverOutRowIndex = jsonObj.getJSONObject("ServerOut").getInt("value") - 1;
		settings.dbOutRowIndex = jsonObj.getJSONObject("DBOut").getInt("value") - 1;
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
}
