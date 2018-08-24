package xu;

import java.util.Arrays;

import org.json.JSONObject;
import xu.BinaryReaderProxy;
import xu.JsonReaderProxy;

/**
 * @author xuzhuoxi
 * Created on 2018-08-25.
 */
public class CfgBuilding {
	//以下为属性声明----------------
     //ID
    private int building_type;
     //建筑
    private String name;
     //是否可以升级
    private int promotion;
     //规格
    private int layoutX;
     //建筑key
    private long type_idx;
     //门X坐标
    private float gateX;
     //门Y坐标
    private float gateY;
     //地形
    private int terrain_flags;
     //供需人口类型
    private int supply_population_type;
     //有没有门
    private int isDoor;
     //建筑描述
    private String desc1;
     //建筑描述
    private String desc2;
     //建筑描述
    private JSONObject desc3;
     //测试1
    private boolean[] f1;
     //测试2
    private short[] f2;
     //测试3
    private int[] f3;
     //测试4
    private long[] f4;
     //测试5
    private byte[] f5;
     //测试6
    private short[] f6;
     //测试7
    private int[] f7;
     //测试8
    private float[] f8;
     //测试9
    private String[] f9;
     //测试10
    private String[] f10;

	
	//以下为Get方法-----------------
    /**
     * ID
     *
     * @remark 注释列
     * @returns {int}
     */
    public int getBuilding_type() {
        return this.building_type;
    }
    /**
     * 建筑
     *
     * @remark 建筑名称
     * @returns {String}
     */
    public String getName() {
        return this.name;
    }
    /**
     * 是否可以升级
     *
     * @remark 0：代表不能升级
1：代表可以升级
     * @returns {int}
     */
    public int getPromotion() {
        return this.promotion;
    }
    /**
     * 规格
     *
     * @remark 
     * @returns {int}
     */
    public int getLayoutX() {
        return this.layoutX;
    }
    /**
     * 建筑key
     *
     * @remark 建筑key指的是对应类型的1级建筑的ID
     * @returns {long}
     */
    public long getType_idx() {
        return this.type_idx;
    }
    /**
     * 门X坐标
     *
     * @remark 坐标从0开始
     * @returns {float}
     */
    public float getGateX() {
        return this.gateX;
    }
    /**
     * 门Y坐标
     *
     * @remark 
     * @returns {float}
     */
    public float getGateY() {
        return this.gateY;
    }
    /**
     * 地形
     *
     * @remark 4
     * @returns {int}
     */
    public int getTerrain_flags() {
        return this.terrain_flags;
    }
    /**
     * 供需人口类型
     *
     * @remark 平民 1 -> 修改为 233
骑士 2 -> 修改为 234
贵族 3 -> 修改为 235

     * @returns {int}
     */
    public int getSupply_population_type() {
        return this.supply_population_type;
    }
    /**
     * 有没有门
     *
     * @remark 5.0
     * @returns {int}
     */
    public int getIsDoor() {
        return this.isDoor;
    }
    /**
     * 建筑描述
     *
     * @remark 建筑描述
string(20)
     * @returns {String}
     */
    public String getDesc1() {
        return this.desc1;
    }
    /**
     * 建筑描述
     *
     * @remark 建筑描述
string
     * @returns {String}
     */
    public String getDesc2() {
        return this.desc2;
    }
    /**
     * 建筑描述
     *
     * @remark 建筑描述
json
     * @returns {JSONObject}
     */
    public JSONObject getDesc3() {
        return this.desc3;
    }
    /**
     * 测试1
     *
     * @remark 测试1
     * @returns {boolean[]}
     */
    public boolean[] getF1() {
        return this.f1;
    }
    /**
     * 测试2
     *
     * @remark 测试2
     * @returns {short[]}
     */
    public short[] getF2() {
        return this.f2;
    }
    /**
     * 测试3
     *
     * @remark 测试3
     * @returns {int[]}
     */
    public int[] getF3() {
        return this.f3;
    }
    /**
     * 测试4
     *
     * @remark 测试4
     * @returns {long[]}
     */
    public long[] getF4() {
        return this.f4;
    }
    /**
     * 测试5
     *
     * @remark 测试5
     * @returns {byte[]}
     */
    public byte[] getF5() {
        return this.f5;
    }
    /**
     * 测试6
     *
     * @remark 测试6
     * @returns {short[]}
     */
    public short[] getF6() {
        return this.f6;
    }
    /**
     * 测试7
     *
     * @remark 测试7
     * @returns {int[]}
     */
    public int[] getF7() {
        return this.f7;
    }
    /**
     * 测试8
     *
     * @remark 测试8
     * @returns {float[]}
     */
    public float[] getF8() {
        return this.f8;
    }
    /**
     * 测试9
     *
     * @remark 测试9
     * @returns {String[]}
     */
    public String[] getF9() {
        return this.f9;
    }
    /**
     * 测试10
     *
     * @remark 测试10
     * @returns {String[]}
     */
    public String[] getF10() {
        return this.f10;
    }

    
	@Override
	public String toString() {
		return "CfgBuilding [building_type=" + building_type + ", name=" + name + ", promotion=" + promotion
				+ ", layoutX=" + layoutX + ", type_idx=" + type_idx + ", gateX=" + gateX + ", gateY=" + gateY
				+ ", terrain_flags=" + terrain_flags + ", supply_population_type=" + supply_population_type
				+ ", isDoor=" + isDoor + ", desc1=" + desc1 + ", desc2=" + desc2 + ", desc3=" + desc3 + ", f1="
				+ Arrays.toString(f1) + ", f2=" + Arrays.toString(f2) + ", f3=" + Arrays.toString(f3) + ", f4="
				+ Arrays.toString(f4) + ", f5=" + Arrays.toString(f5) + ", f6=" + Arrays.toString(f6) + ", f7="
				+ Arrays.toString(f7) + ", f8=" + Arrays.toString(f8) + ", f9=" + Arrays.toString(f9) + ", f10="
				+ Arrays.toString(f10) + "]";
	}
	//以下为解释数据方法------------
    public void parseJson(JsonReaderProxy  proxy) {
		this.building_type = proxy.getUInt16("building_type");
		this.name = proxy.getString("name");
		this.promotion = proxy.getUInt8("promotion");
		this.layoutX = proxy.getUInt16("layoutX");
		this.type_idx = proxy.getUInt32("type_idx");
		this.gateX = proxy.getFloat32("gateX");
		this.gateY = proxy.getFloat32("gateY");
		this.terrain_flags = proxy.getInt32("terrain_flags");
		this.supply_population_type = proxy.getInt16("supply_population_type");
		this.isDoor = proxy.getInt8("isDoor");
		this.desc1 = proxy.getString("desc1");
		this.desc2 = proxy.getString("desc2");
		this.desc3 = proxy.getJsonObject("desc3");
		this.f1 = proxy.getBooleanArray("f1");
		this.f2 = proxy.getUInt8Array("f2");
		this.f3 = proxy.getUInt16Array("f3");
		this.f4 = proxy.getUInt32Array("f4");
		this.f5 = proxy.getInt8Array("f5");
		this.f6 = proxy.getInt16Array("f6");
		this.f7 = proxy.getInt32Array("f7");
		this.f8 = proxy.getFloat32Array("f8");
		this.f9 = proxy.getStringArray("f9");
		this.f10 = proxy.getStringArray("f10");

    }
    
    public void parseBinary(BinaryReaderProxy proxy)  throws Exception {
		this.building_type = proxy.readUInt16();
		this.name = proxy.readString();
		this.promotion = proxy.readUInt8();
		this.layoutX = proxy.readUInt16();
		this.type_idx = proxy.readUInt32();
		this.gateX = proxy.readFloat32();
		this.gateY = proxy.readFloat32();
		this.terrain_flags = proxy.readInt32();
		this.supply_population_type = proxy.readInt16();
		this.isDoor = proxy.readInt8();
		this.desc1 = proxy.readString();
		this.desc2 = proxy.readString();
		this.desc3 = proxy.readJsonObject();
		this.f1 = proxy.readBooleanArray();
		this.f2 = proxy.readUInt8Array();
		this.f3 = proxy.readUInt16Array();
		this.f4 = proxy.readUInt32Array();
		this.f5 = proxy.readInt8Array();
		this.f6 = proxy.readInt16Array();
		this.f7 = proxy.readInt32Array();
		this.f8 = proxy.readFloat32Array();
		this.f9 = proxy.readStringArray();
		this.f10 = proxy.readStringArray();

    }
}