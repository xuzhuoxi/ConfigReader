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
	public static final FieldDataFormat UInt8 = new FieldDataFormat("uint8", 1);
	/**
	 * 无符号16位整数
	 */
	public static final FieldDataFormat UInt16 = new FieldDataFormat("uint16", 2);
	/**
	 * 无符号32位整数
	 */
	public static final FieldDataFormat UInt32 = new FieldDataFormat("uint32", 4);
	/**
	 * 有符号8位整数
	 */
	public static final FieldDataFormat Int8 = new FieldDataFormat("int8", 1);
	/**
	 * 有符号16位整数
	 */
	public static final FieldDataFormat Int16 = new FieldDataFormat("int16", 2);
	/**
	 * 有符号32位整数
	 */
	public static final FieldDataFormat Int32 = new FieldDataFormat("int32", 4);
	/**
	 * 有符号32位浮点数
	 */
	public static final FieldDataFormat Float32 = new FieldDataFormat("float32", 4);
	/**
	 * 布尔型
	 */
	public static final FieldDataFormat Boolean = new FieldDataFormat("boolean", 1);
	/**
	 * 字符型
	 */
	public static final FieldDataFormat String = new FieldDataFormat("string", -1);

	private static final String StartChar = "(";
	private static final String EndChar = ")";

	private String typeName;
	private int dataLen;

	/**
	 * 数据类型的存储长度
	 * 
	 * @return 当数据类型为String时，返回字符长度<br>
	 *         其它返回字节长度<br>
	 */
	public int getDataLen() {
		return dataLen;
	}

	public String getTypeName() {
		return typeName;
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
		if (null == typeName && null == other.typeName)
			return true;
		if (null == typeName || null == other.typeName)
			return false;
		return typeName.equals(other.typeName);
	}

	@Override
	public String toString() {
		return "DataType [typeName=" + typeName + ", byteCount=" + dataLen + "]";
	}

	public String toOriginalString() {
		if ("string".equals(typeName)) {
			if (-1 == dataLen) {
				return typeName + "(" + dataLen + ")";
			} else {
				return typeName + "(" + (dataLen >> 1) + ")";
			}
		} else {
			return typeName;
		}
	}

	private FieldDataFormat(String value, int dataLen) {
		this.typeName = value;
		this.dataLen = dataLen;
	}

	public static FieldDataFormat from(String value) {
		if (null == value || value.length() == 0) {
			return null;
		}
		String stringValue = value.toLowerCase().trim();
		if (stringValue.equals(UInt8.typeName)) {
			return UInt8;
		} else if (stringValue.equals(UInt16.typeName)) {
			return UInt16;
		} else if (stringValue.equals(UInt32.typeName)) {
			return UInt32;
		} else if (stringValue.equals(Int8.typeName)) {
			return Int8;
		} else if (stringValue.equals(Int16.typeName)) {
			return Int16;
		} else if (stringValue.equals(Int32.typeName)) {
			return Int32;
		} else if (stringValue.equals(Float32.typeName)) {
			return Float32;
		} else if (stringValue.equals(Boolean.typeName)) {
			return Boolean;
		} else if (stringValue.equals(String.typeName)) {
			return String;
		} else {// 字符串
			int startIndex = stringValue.indexOf(StartChar) + 1;
			int endIndex = stringValue.indexOf(EndChar);
			if (startIndex == -1 || endIndex == -1 || endIndex <= startIndex) {
				return null;
			}
			String charLen = value.substring(startIndex, endIndex);
			int byteCount = Integer.parseInt(charLen);
			return new FieldDataFormat("string", byteCount);
		}
	}
}
