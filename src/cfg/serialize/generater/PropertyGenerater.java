package cfg.serialize.generater;

import java.util.ArrayList;
import java.util.List;

import cfg.source.data.SheetDefine;

public class PropertyGenerater extends GeneraterBase implements IContentGenerater {

	private String propertyDefine;// 声名模板
	private String propertyGetFunc;// get方法模板
	private List<String> defineList = new ArrayList<String>(); // 声名内容列表
	private List<String> getFuncList = new ArrayList<String>();// get方法内容列表

	@Override
	public void setTempPath(String tempUrl) {
		super.setTempPath(tempUrl);
		this.splitTempContent();
	}

	protected void splitTempContent() {
		String separator = TempKey.KEY_CONNOM_SEPARATOR;
		int index = this.tempContent.indexOf(separator);
		this.propertyDefine = this.tempContent.substring(0, index);
		this.propertyGetFunc = this.tempContent.substring(index + separator.length());
	}

	@Override
	public String serialize(SheetDefine sheetDefine) {
		this.sb.setLength(0);
		this.defineList.clear();
		this.getFuncList.clear();

		Integer[] indexs = sheetDefine.getExportInfo(this.fieldRange).getValidIndexs();
		for (Integer index : indexs) {
			this.cacheSerialize(sheetDefine, index);
		}
		for (String string : this.defineList) {
			this.sb.append(string);
		}
		for (String string : this.getFuncList) {
			this.sb.append(string);
		}
		return this.sb.toString();
	}

	/**
	 * 生成属性定义 和 生成属性对应的Get方法
	 * 
	 * @param sheetDefine
	 * @param index
	 */
	protected void cacheSerialize(SheetDefine sheetDefine, Integer index) {
		String propertyName = sheetDefine.getName(index);
		String property = sheetDefine.getFieldName(this.lang.getValue(), index);
		String upProperty = property.substring(0, 1).toUpperCase() + property.substring(1);
		String dataType = this.langConversion.conversionDataType(sheetDefine.getDataTypeInstance(index));
		String remark = sheetDefine.getRemark(index);
		this.defineList.add(this.serializePropertyDefine(propertyName, property, dataType));
		this.getFuncList.add(this.serializePropertyGet(propertyName, property, upProperty, dataType, remark));
	}

	/**
	 * 生成属性定义
	 * 
	 * @param propertyName
	 *            属性内容说明
	 * @param property
	 *            属性内容
	 * @return
	 */
	protected String serializePropertyDefine(String propertyName, String property, String dataType) {
		return this.propertyDefine.replace(TempKey.KEY_DATA_TYPE, dataType).replace(TempKey.KEY_PROPETY, property)
				.replace(TempKey.KEY_PROPERTY_NAME, propertyName);
	}

	/**
	 * 生成Get方法
	 * 
	 * @param propertyName
	 *            属性内容说明
	 * @param property
	 *            属性内容
	 * @param upProperty
	 *            大写开关的属性内容
	 * @param dataType
	 *            返回值内容
	 * @param remark
	 *            注释内容
	 * @return
	 */
	protected String serializePropertyGet(String propertyName, String property, String upProperty, String dataType,
			String remark) {
		String rs = this.propertyGetFunc.replace(TempKey.KEY_REMARK, remark);
		rs = rs.replace(TempKey.KEY_DATA_TYPE, dataType);
		rs = rs.replace(TempKey.KEY_PROPERTY_UP, upProperty);
		rs = rs.replace(TempKey.KEY_PROPETY, property);
		rs = rs.replace(TempKey.KEY_PROPERTY_NAME, propertyName);
		return rs;
	}
}
