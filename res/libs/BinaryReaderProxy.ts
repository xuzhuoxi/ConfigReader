/**
 * Created by Administrator on 2017/8/28.
 * @author xuzhuoxi
 */
namespace xu {
    export class BinaryReaderProxy {
        private byteArray: egret.ByteArray;
        private charsetNamed: string;

        constructor(byteArray: egret.ByteArray, charsetNamed: string) {
            this.byteArray = byteArray;
            this.charsetNamed = charsetNamed;
        }

        public readBoolean(): boolean {
            return this.byteArray.readBoolean();
        }

        public readInt8(): number {
            return this.byteArray.readByte();
        }

        public readInt16(): number {
            return this.byteArray.readShort();
        }

        public readInt32(): number {
            return this.byteArray.readInt();
        }

        public readUInt8(): number {
            return this.byteArray.readUnsignedByte();
        }

        public readUInt16(): number {
            return this.byteArray.readUnsignedShort();
        }

        public readUInt32(): number {
            return this.byteArray.readUnsignedInt();
        }

        public readString(): string {
            let len: number = this.byteArray.readUnsignedShort();
            return this.byteArray.readUTFBytes(len);
        }
    }
}