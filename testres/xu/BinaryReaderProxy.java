package xu;

import java.io.DataInputStream;

public class BinaryReaderProxy {

	private DataInputStream dis;

	public BinaryReaderProxy(DataInputStream dis) {
		super();
		this.dis = dis;
	}

	public boolean readBoolean() throws Exception {
		return dis.readBoolean();
	}

	public int readByte() throws Exception {
		return dis.readByte();
	}

	public int readShort() throws Exception {
		return dis.readShort();
	}

	public int readInt() throws Exception {
		return dis.readInt();
	}

	public long readLong() throws Exception {
		return dis.readLong();
	}

	public String readString() throws Exception {
		int len = dis.readShort();
		byte[] strByte = new byte[len];
		dis.read(strByte);
		return new String(strByte, "UTF-8").toString();
	}
}
