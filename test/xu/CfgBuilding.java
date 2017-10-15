package xu;

import org.json.JSONObject;

/**
 * @author xuzhuoxi Created on 2017-08-23.
 */
public class CfgBuilding {
	// ID
	private int building_type;
	// 后端类型
	private int main_type;
	// 建筑
	private String name;
	// 建筑描述
	private String desc;
	// 是否可以升级
	private int promotion;
	// 规格X宽度
	private int layoutX;
	// 规格Y宽度
	private int layoutY;
	// 门X坐标
	private int gateX;
	// 门Y坐标
	private int gateY;
	// 建筑key
	private long type_idx;
	// 前端类型
	private int type;
	// 前端排序
	private int building_order;
	// 路
	private int needRoad;
	// 可建造地形
	private long terrain_flags;
	// 有没有门
	private int isDoor;
	// 供需类型
	private int supply_type;
	// 供需人口类型
	private int supply_population_type;
	// 环境等级
	private int environment_level;
	// 环境辐射范围
	private int environment_radiate_range;
	// 官员类型
	private int official_main_type;
	// 官员等级
	private int official_min_level;
	// 官员技能类型
	private int official_skill_main_type;
	// 语言包建筑ID
	private int name_id;
	// 语言包建筑描述
	private int description_id;
	// 语言包建筑作用ID
	private int effect_id;
	// 语言包建筑说明ID
	private int explain_id;
	// 详情按钮
	private int details_button;
	// 升级按钮
	private int upgrade_button;
	// 技能按钮
	private int skill_button;
	// 兵器按钮
	private int weapons_button;
	// 官员按钮
	private int officer_button;
	// 铲除按钮
	private int dismantle_button;
	// 功能完善提示
	private int is_open;
	// 计算建筑
	private int is_count;
	// 音效
	private int sound;
	// 当NPC进入此建筑时NPC是否有半透明效果
	private int translucence;

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
	 * 后端类型
	 *
	 * @remark 特效type50仅为前端用到，后端无用，更改需告知前后端
	 * @returns {int}
	 */
	public int getMain_type() {
		return this.main_type;
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
	 * 建筑描述
	 *
	 * @remark 建筑描述
	 * @returns {String}
	 */
	public String getDesc() {
		return this.desc;
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
	 * 规格X宽度
	 *
	 * @remark
	 * @returns {int}
	 */
	public int getLayoutX() {
		return this.layoutX;
	}

	/**
	 * 规格Y宽度
	 *
	 * @remark
	 * @returns {int}
	 */
	public int getLayoutY() {
		return this.layoutY;
	}

	/**
	 * 门X坐标
	 *
	 * @remark 坐标从0开始
	 * @returns {int}
	 */
	public int getGateX() {
		return this.gateX;
	}

	/**
	 * 门Y坐标
	 *
	 * @remark
	 * @returns {int}
	 */
	public int getGateY() {
		return this.gateY;
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
	 * 前端类型
	 *
	 * @remark 建筑列表分类
	 * @returns {int}
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * 前端排序
	 *
	 * @remark 建筑列表排序 0不显示
	 * @returns {int}
	 */
	public int getBuilding_order() {
		return this.building_order;
	}

	/**
	 * 路
	 *
	 * @remark 0不需要 1需要
	 * @returns {int}
	 */
	public int getNeedRoad() {
		return this.needRoad;
	}

	/**
	 * 可建造地形
	 *
	 * @remark 4.0
	 * @returns {long}
	 */
	public long getTerrain_flags() {
		return this.terrain_flags;
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
	 * 供需类型
	 *
	 * @remark 0 无 1 提供工人 2 需要工人
	 * 
	 * @returns {int}
	 */
	public int getSupply_type() {
		return this.supply_type;
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
	 * 环境等级
	 *
	 * @remark
	 * @returns {int}
	 */
	public int getEnvironment_level() {
		return this.environment_level;
	}

	/**
	 * 环境辐射范围
	 *
	 * @remark
	 * @returns {int}
	 */
	public int getEnvironment_radiate_range() {
		return this.environment_radiate_range;
	}

	/**
	 * 官员类型
	 *
	 * @remark 0-无效类型，代表该建筑无法放置官员 1-农政官 2-军官 3-鉴政官
	 * @returns {int}
	 */
	public int getOfficial_main_type() {
		return this.official_main_type;
	}

	/**
	 * 官员等级
	 *
	 * @remark 军团只能放10级千夫长。 其他建筑1级官员只要满足前置技能就可以放置。
	 * @returns {int}
	 */
	public int getOfficial_min_level() {
		return this.official_min_level;
	}

	/**
	 * 官员技能类型
	 *
	 * @remark 0：没有 1：种植 2：采集 3：畜牧 4：冶炼 5：制造 6：酿造 7：贩卖 8：仓储 9：意志 10：格杀 11：破甲
	 *         12：突袭 13：精准 14：格挡 15：秩序 16：教育 17：医疗 18：卫生 19：宗教 20：司法 21：艺术 22：竞争
	 * @returns {int}
	 */
	public int getOfficial_skill_main_type() {
		return this.official_skill_main_type;
	}

	/**
	 * 语言包建筑ID
	 *
	 * @remark 填写语言包对应ID
	 * @returns {int}
	 */
	public int getName_id() {
		return this.name_id;
	}

	/**
	 * 语言包建筑描述
	 *
	 * @remark 填写语言包建筑描述对应ID
	 * @returns {int}
	 */
	public int getDescription_id() {
		return this.description_id;
	}

	/**
	 * 语言包建筑作用ID
	 *
	 * @remark 填写语言包建筑作用对应ID
	 * @returns {int}
	 */
	public int getEffect_id() {
		return this.effect_id;
	}

	/**
	 * 语言包建筑说明ID
	 *
	 * @remark 填写语言包建筑说明对应ID
	 * @returns {int}
	 */
	public int getExplain_id() {
		return this.explain_id;
	}

	/**
	 * 详情按钮
	 *
	 * @remark 0：不开放 1：开放
	 * @returns {int}
	 */
	public int getDetails_button() {
		return this.details_button;
	}

	/**
	 * 升级按钮
	 *
	 * @remark 0：不开放 1：开放
	 * @returns {int}
	 */
	public int getUpgrade_button() {
		return this.upgrade_button;
	}

	/**
	 * 技能按钮
	 *
	 * @remark 0：不开放 1：开放
	 * @returns {int}
	 */
	public int getSkill_button() {
		return this.skill_button;
	}

	/**
	 * 兵器按钮
	 *
	 * @remark 0：不开放 1：开放
	 * @returns {int}
	 */
	public int getWeapons_button() {
		return this.weapons_button;
	}

	/**
	 * 官员按钮
	 *
	 * @remark 0：不开放 1：开放
	 * @returns {int}
	 */
	public int getOfficer_button() {
		return this.officer_button;
	}

	/**
	 * 铲除按钮
	 *
	 * @remark 0：不开放 1：开放
	 * @returns {int}
	 */
	public int getDismantle_button() {
		return this.dismantle_button;
	}

	/**
	 * 功能完善提示
	 *
	 * @remark 0：不开放 1：开放 填0提示：功能暂未开放
	 * @returns {int}
	 */
	public int getIs_open() {
		return this.is_open;
	}

	/**
	 * 计算建筑
	 *
	 * @remark 0：不计算建筑数量 1：计算建筑数量
	 * 
	 * @returns {int}
	 */
	public int getIs_count() {
		return this.is_count;
	}

	/**
	 * 音效
	 *
	 * @remark
	 * @returns {int}
	 */
	public int getSound() {
		return this.sound;
	}

	/**
	 * 当NPC进入此建筑时NPC是否有半透明效果
	 *
	 * @remark 0:不透明 1：透明
	 * @returns {int}
	 */
	public int getTranslucence() {
		return this.translucence;
	}

	@Override
	public String toString() {
		return "CfgBuilding [building_type=" + building_type + ", main_type=" + main_type + ", name=" + name + ", desc="
				+ desc + ", promotion=" + promotion + ", layoutX=" + layoutX + ", layoutY=" + layoutY + ", gateX="
				+ gateX + ", gateY=" + gateY + ", type_idx=" + type_idx + ", type=" + type + ", building_order="
				+ building_order + ", needRoad=" + needRoad + ", terrain_flags=" + terrain_flags + ", isDoor=" + isDoor
				+ ", supply_type=" + supply_type + ", supply_population_type=" + supply_population_type
				+ ", environment_level=" + environment_level + ", environment_radiate_range="
				+ environment_radiate_range + ", official_main_type=" + official_main_type + ", official_min_level="
				+ official_min_level + ", official_skill_main_type=" + official_skill_main_type + ", name_id=" + name_id
				+ ", description_id=" + description_id + ", effect_id=" + effect_id + ", explain_id=" + explain_id
				+ ", details_button=" + details_button + ", upgrade_button=" + upgrade_button + ", skill_button="
				+ skill_button + ", weapons_button=" + weapons_button + ", officer_button=" + officer_button
				+ ", dismantle_button=" + dismantle_button + ", is_open=" + is_open + ", is_count=" + is_count
				+ ", sound=" + sound + ", translucence=" + translucence + "]";
	}

	public void parseJson(JSONObject data) {
		this.building_type = data.getInt("building_type");
		this.main_type = data.getInt("main_type");
		this.name = data.getString("name");
		this.desc = data.getString("desc");
		this.promotion = data.getInt("promotion");
		this.layoutX = data.getInt("layoutX");
		this.layoutY = data.getInt("layoutY");
		this.gateX = data.getInt("gateX");
		this.gateY = data.getInt("gateY");
		this.type_idx = data.getLong("type_idx");
		this.type = data.getInt("type");
		this.building_order = data.getInt("building_order");
		this.needRoad = data.getInt("needRoad");
		this.terrain_flags = data.getLong("terrain_flags");
		this.isDoor = data.getInt("isDoor");
		this.supply_type = data.getInt("supply_type");
		this.supply_population_type = data.getInt("supply_population_type");
		this.environment_level = data.getInt("environment_level");
		this.environment_radiate_range = data.getInt("environment_radiate_range");
		this.official_main_type = data.getInt("official_main_type");
		this.official_min_level = data.getInt("official_min_level");
		this.official_skill_main_type = data.getInt("official_skill_main_type");
		this.name_id = data.getInt("name_id");
		this.description_id = data.getInt("description_id");
		this.effect_id = data.getInt("effect_id");
		this.explain_id = data.getInt("explain_id");
		this.details_button = data.getInt("details_button");
		this.upgrade_button = data.getInt("upgrade_button");
		this.skill_button = data.getInt("skill_button");
		this.weapons_button = data.getInt("weapons_button");
		this.officer_button = data.getInt("officer_button");
		this.dismantle_button = data.getInt("dismantle_button");
		this.is_open = data.getInt("is_open");
		this.is_count = data.getInt("is_count");
		this.sound = data.getInt("sound");
		this.translucence = data.getInt("translucence");

	}

	public void parseBinary(BinaryReaderProxy proxy) throws Exception {
		this.building_type = proxy.readUInt16();
		this.main_type = proxy.readUInt8();
		this.name = proxy.readString();
		this.desc = proxy.readString();
		this.promotion = proxy.readUInt8();
		this.layoutX = proxy.readUInt8();
		this.layoutY = proxy.readUInt8();
		this.gateX = proxy.readUInt8();
		this.gateY = proxy.readUInt8();
		this.type_idx = proxy.readUInt32();
		this.type = proxy.readUInt8();
		this.building_order = proxy.readUInt8();
		this.needRoad = proxy.readUInt8();
		this.terrain_flags = proxy.readUInt32();
		this.isDoor = proxy.readUInt8();
		this.supply_type = proxy.readUInt8();
		this.supply_population_type = proxy.readUInt16();
		this.environment_level = proxy.readInt8();
		this.environment_radiate_range = proxy.readUInt16();
		this.official_main_type = proxy.readUInt8();
		this.official_min_level = proxy.readUInt8();
		this.official_skill_main_type = proxy.readUInt16();
		this.name_id = proxy.readUInt8();
		this.description_id = proxy.readUInt8();
		this.effect_id = proxy.readUInt8();
		this.explain_id = proxy.readUInt8();
		this.details_button = proxy.readUInt8();
		this.upgrade_button = proxy.readUInt8();
		this.skill_button = proxy.readUInt8();
		this.weapons_button = proxy.readUInt8();
		this.officer_button = proxy.readUInt8();
		this.dismantle_button = proxy.readUInt8();
		this.is_open = proxy.readUInt8();
		this.is_count = proxy.readUInt8();
		this.sound = proxy.readUInt16();
		this.translucence = proxy.readUInt8();

	}
}