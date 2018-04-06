package xu;

import org.json.JSONObject;

/**
 * @author xuzhuoxi Created on 2018-04-06.
 */
public class CfgBuilding {
	// ID
	private int building_type;
	// 建筑
	private String name;
	// 是否可以升级
	private int promotion;
	// 规格
	private int layoutX;
	// 建筑key
	private long type_idx;
	// 门X坐标
	private float gateX;
	// 门Y坐标
	private float gateY;
	// 地形
	private int terrain_flags;
	// 供需人口类型
	private int supply_population_type;
	// 有没有门
	private int isDoor;
	// 建筑描述
	private String desc;

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
	 * @remark 0：代表不能升级 1：代表可以升级
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
	 * @remark 平民 1 -> 修改为 233 骑士 2 -> 修改为 234 贵族 3 -> 修改为 235
	 * 
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
	 * @remark 建筑描述 string(20)
	 * @returns {String}
	 */
	public String getDesc() {
		return this.desc;
	}

	@Override
	public String toString() {
		return "CfgBuilding [building_type=" + building_type + ", name=" + name + ", promotion=" + promotion
				+ ", layoutX=" + layoutX + ", type_idx=" + type_idx + ", gateX=" + gateX + ", gateY=" + gateY
				+ ", terrain_flags=" + terrain_flags + ", supply_population_type=" + supply_population_type
				+ ", isDoor=" + isDoor + ", desc=" + desc + "]";
	}

	public void parseJson(JSONObject data) {
		this.building_type = (int) data.getInt("building_type");
		this.name = (String) data.getString("name");
		this.promotion = (int) data.getInt("promotion");
		this.layoutX = (int) data.getInt("layoutX");
		this.type_idx = (long) data.getLong("type_idx");
		this.gateX = (float) data.getDouble("gateX");
		this.gateY = (float) data.getDouble("gateY");
		this.terrain_flags = (int) data.getInt("terrain_flags");
		this.supply_population_type = (int) data.getInt("supply_population_type");
		this.isDoor = (int) data.getInt("isDoor");
		this.desc = (String) data.getString("desc");

	}

	public void parseBinary(BinaryReaderProxy proxy) throws Exception {
		this.building_type = (int) proxy.readUInt16();
		this.name = (String) proxy.readString();
		this.promotion = (int) proxy.readUInt8();
		this.layoutX = (int) proxy.readUInt16();
		this.type_idx = (long) proxy.readUInt32();
		this.gateX = (float) proxy.readFloat32();
		this.gateY = (float) proxy.readFloat32();
		this.terrain_flags = (int) proxy.readInt32();
		this.supply_population_type = (int) proxy.readInt16();
		this.isDoor = (int) proxy.readInt8();
		this.desc = (String) proxy.readString();

	}
}