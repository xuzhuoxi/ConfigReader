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
		short value = (short) (dis.readByte() & (short) 0x00ff);
		return value;
	}

	public int readUInt16() throws Exception {
		int value = (int) (dis.readShort() & 0x0000ffff);
		return value;
	}

	public long readUInt32() throws Exception {
		long value = (long) (dis.readInt() & 0x000000ffffffff);
		return value;
	}

	public String readString() throws Exception {
		int len = dis.readShort();
		byte[] strByte = new byte[len];
		dis.read(strByte);
		return new String(strByte, charset).toString();
	}
}
