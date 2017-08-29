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

	public int getServerOutRowIndex() {
		return serverOutRowIndex;
	}

	public int getClientOutRowIndex() {
		return clientOutRowIndex;
	}

	public int getDbOutRowIndex() {
		return dbOutRowIndex;
	}

	public int getNameRowIndex() {
		return nameRowIndex;
	}

	public int getRemarkRowIndex() {
		return remarkRowIndex;
	}

	public int getValidRowIndex() {
		return validRowIndex;
	}

	public int getDataTypeRowIndex() {
		return dataTypeRowIndex;
	}

	public int getStartRowIndex() {
		return startRowIndex;
	}

	public Map<String, Integer> getFieldNameRowNumMap() {
		return fieldNameRowIndexMap;
	}

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
