package cfg.serialize;

/**
 * 字段的数据格式
 * 
 * @author xuzhuoxi
 */
public class FieldDataFormat {
	/**
	 * 无符号8位整数
	 */
	public static final FieldDataFormat UInt8 = new FieldDataFormat("uint8", 1, false);
	/**
	 * 数组：无符号8位整数
	 */
	public static final FieldDataFormat A_UInt8 = new FieldDataFormat("uint8[]", 1, true);
	/**
	 * 无符号16位整数
	 */
	public static final FieldDataFormat UInt16 = new FieldDataFormat("uint16", 2, false);
	/**
	 * 数组：无符号16位整数
	 */
	public static final FieldDataFormat A_UInt16 = new FieldDataFormat("uint16[]", 2, true);
	/**
	 * 无符号32位整数
	 */
	public static final FieldDataFormat UInt32 = new FieldDataFormat("uint32", 4, false);
	/**
	 * 数组：无符号32位整数
	 */
	public static final FieldDataFormat A_UInt32 = new FieldDataFormat("uint32[]", 4, true);
	/**
	 * 有符号8位整数
	 */
	public static final FieldDataFormat Int8 = new FieldDataFormat("int8", 1, false);
	/**
	 * 数组：有符号8位整数
	 */
	public static final FieldDataFormat A_Int8 = new FieldDataFormat("int8[]", 1, true);
	/**
	 * 有符号16位整数
	 */
	public static final FieldDataFormat Int16 = new FieldDataFormat("int16", 2, false);
	/**
	 * 数组：有符号16位整数
	 */
	public static final FieldDataFormat A_Int16 = new FieldDataFormat("int16[]", 2, true);
	/**
	 * 有符号32位整数
	 */
	public static final FieldDataFormat Int32 = new FieldDataFormat("int32", 4, false);
	/**
	 * 数组：有符号32位整数
	 */
	public static final FieldDataFormat A_Int32 = new FieldDataFormat("int32[]", 4, true);
	/**
	 * 有符号32位浮点数
	 */
	public static final FieldDataFormat Float32 = new FieldDataFormat("float32", 4, false);
	/**
	 * 数组：有符号32位浮点数
	 */
	public static final FieldDataFormat A_Float32 = new FieldDataFormat("float32[]", 4, true);
	/**
	 * 布尔型
	 */
	public static final FieldDataFormat Boolean = new FieldDataFormat("boolean", 1, false);
	/**
	 * 数组：布尔型
	 */
	public static final FieldDataFormat A_Boolean = new FieldDataFormat("boolean[]", 1, true);
	/**
	 * Json
	 */
	public static final FieldDataFormat Json = new FieldDataFormat("json", -1, false);
	/**
	 * 数组：Json
	 */
	public static final FieldDataFormat A_Json = new FieldDataFormat("json[]", -1, true);
	/**
	 * 字符型 格式：string 或 string(*)
	 */
	public static final FieldDataFormat String = new FieldDataFormat("string", -1, false);
	/**
	 * 数组：字符型 格式：string[] 或 string(*)[]
	 */
	public static final FieldDataFormat A_String = new FieldDataFormat("string[]", -1, true);

	private static final String StartChar = "(";
	private static final String EndChar = ")";

	private String dataFormatName;
	private int dataLen;
	private boolean isArray;

	/**
	 * 数据类型的存储长度
	 * 
	 * @return 当数据类型为String时，返回字符长度<br>
	 *         其它返回字节长度<br>
	 */
	public int getDataLen() {
		return dataLen;
	}

	/**
	 * 字段数据类型 注意：Excel表上string(*)得到的是string
	 * 
	 * @return 数据类型的字符串表示
	 */
	public String getDataFormatName() {
		return dataFormatName;
	}

	/**
	 * 是否为数组类型
	 * 
	 * @return true是false否
	 */
	public boolean getIsArray() {
		return isArray;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldDataFormat other = (FieldDataFormat) obj;
		if (dataLen != other.dataLen)
			return false;
		if (null == dataFormatName && null == other.dataFormatName)
			return true;
		if (null == dataFormatName || null == other.dataFormatName)
			return false;
		return dataFormatName.equals(other.dataFormatName);
	}

	@Override
	public String toString() {
		return "DataType [typeName=" + dataFormatName + ", byteCount=" + dataLen + "]";
	}

	public String toOriginalString() {
		if ("string".equals(dataFormatName)) {
			if (-1 == dataLen) {
				return dataFormatName + "(" + dataLen + ")";
			} else {
				return dataFormatName + "(" + (dataLen >> 1) + ")";
			}
		} else {
			return dataFormatName;
		}
	}

	private FieldDataFormat(String value, int dataLen, boolean isArray) {
		this.dataFormatName = value;
		this.dataLen = dataLen;
		this.isArray = isArray;
	}

	public static FieldDataFormat from(String value) throws Exception {
		if (null == value || value.length() == 0) {
			return null;
		}
		String stringValue = value.toLowerCase().trim();
		if (stringValue.equals(UInt8.dataFormatName)) {
			return UInt8;
		} else if (stringValue.equals(A_UInt8.dataFormatName)) {
			return A_UInt8;
		} else if (stringValue.equals(UInt16.dataFormatName)) {
			return UInt16;
		} else if (stringValue.equals(A_UInt16.dataFormatName)) {
			return A_UInt16;
		} else if (stringValue.equals(UInt32.dataFormatName)) {
			return UInt32;
		} else if (stringValue.equals(A_UInt32.dataFormatName)) {
			return A_UInt32;
		} else if (stringValue.equals(Int8.dataFormatName)) {
			return Int8;
		} else if (stringValue.equals(A_Int8.dataFormatName)) {
			return A_Int8;
		} else if (stringValue.equals(Int16.dataFormatName)) {
			return Int16;
		} else if (stringValue.equals(A_Int16.dataFormatName)) {
			return A_Int16;
		} else if (stringValue.equals(Int32.dataFormatName)) {
			return Int32;
		} else if (stringValue.equals(A_Int32.dataFormatName)) {
			return A_Int32;
		} else if (stringValue.equals(Float32.dataFormatName)) {
			return Float32;
		} else if (stringValue.equals(A_Float32.dataFormatName)) {
			return A_Float32;
		} else if (stringValue.equals(Boolean.dataFormatName)) {
			return Boolean;
		} else if (stringValue.equals(A_Boolean.dataFormatName)) {
			return A_Boolean;
		} else if (stringValue.equals(Json.dataFormatName)) {
			return Json;
		} else if (stringValue.equals(A_Json.dataFormatName)) {
			return A_Json;
		} else if (stringValue.equals(String.dataFormatName)) {
			return String;
		} else if (stringValue.equals(A_String.dataFormatName)) {
			return A_String;
		} else {// 字符串
			if (!stringValue.startsWith(String.dataFormatName)) {
				throw new Exception(value);
			}
			int charCount = getStringLen(stringValue, value);
			if (stringValue.endsWith("[]")) {
				return new FieldDataFormat("string[]", charCount, true);
			} else {
				return new FieldDataFormat("string", charCount, false);
			}
		}
	}

	private static int getStringLen(String stringValue, String value) throws Exception {
		int startIndex = stringValue.indexOf(StartChar) + 1;
		int endIndex = stringValue.indexOf(EndChar);
		if (startIndex == -1 || endIndex == -1 || endIndex <= startIndex) {
			throw new Exception(value);
		}
		String charLen = value.substring(startIndex, endIndex);
		int charCount = Integer.parseInt(charLen);
		return charCount;

	}
}
