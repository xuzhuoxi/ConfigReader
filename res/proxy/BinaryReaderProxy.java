package xu;

import java.io.DataInputStream;
import java.nio.charset.Charset;

public class BinaryReaderProxy {

	private DataInputStream dis;
	private Charset charset;

	public BinaryReaderProxy(DataInputStream dis, String charsetName) {
		super();
		this.dis = dis;
		this.charset = Charset.forName(charsetName);
	}

	public boolean readBoolean() throws Exception {
		return dis.readBoolean();
	}

	public byte readInt8() throws Exception {
		return dis.readByte();
	}

	public short readInt16() throws Exception {
		return dis.readShort();
	}

	public int readInt32() throws Exception {
		return dis.readInt();
	}

	public short readUInt8() throws Exception {
		byte val = dis.readByte();
		if (val < 0) {
			return (short) (val + ((short) Byte.MAX_VALUE + 1) * 2);
		} else {
			return (short) val;
		}
	}

	public int readUInt16() throws Exception {
		short val = dis.readShort();
		if (val < 0) {
			return (int) (val + ((int) Short.MAX_VALUE + 1) * 2);
		} else {
			return (int) val;
		}
	}

	public long readUInt32() throws Exception {
		int val = dis.readInt();
		if (val < 0) {
			return (long) (val + ((long) Long.MAX_VALUE + 1) * 2);
		} else {
			return (long) val;
		}
	}

	public float readFloat32() throws Exception {
		float value = dis.readFloat();
		return value;
	}

	public String readString() throws Exception {
		int len = dis.readShort();
		byte[] strByte = new byte[len];
		dis.read(strByte);
		return new String(strByte, charset).toString();
	}
}
