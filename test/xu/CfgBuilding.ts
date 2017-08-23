module xu {
	/**
 * @author xuzhuoxi
 * Created on 2017-08-23.
 */
export class CfgBuilding {
        //ID
    private _building_type: number;
     //后端类型
    private _main_type: number;
     //建筑
    private _name: string;
     //建筑描述
    private _desc: string;
     //是否可以升级
    private _promotion: number;
     //规格X宽度
    private _layoutX: number;
     //规格Y宽度
    private _layoutY: number;
     //门X坐标
    private _gateX: number;
     //门Y坐标
    private _gateY: number;
     //建筑key
    private _type_idx: number;
     //前端类型
    private _type: number;
     //前端排序
    private _building_order: number;
     //路
    private _needRoad: number;
     //可建造地形
    private _terrain_flags: number;
     //有没有门
    private _isDoor: number;
     //供需类型
    private _supply_type: number;
     //供需人口类型
    private _supply_population_type: number;
     //环境等级
    private _environment_level: number;
     //环境辐射范围
    private _environment_radiate_range: number;
     //官员类型
    private _official_main_type: number;
     //官员等级
    private _official_min_level: number;
     //官员技能类型
    private _official_skill_main_type: number;
     //语言包建筑ID
    private _name_id: number;
     //语言包建筑描述
    private _description_id: number;
     //语言包建筑作用ID
    private _effect_id: number;
     //语言包建筑说明ID
    private _explain_id: number;
     //详情按钮
    private _details_button: number;
     //升级按钮
    private _upgrade_button: number;
     //技能按钮
    private _skill_button: number;
     //兵器按钮
    private _weapons_button: number;
     //官员按钮
    private _officer_button: number;
     //铲除按钮
    private _dismantle_button: number;
     //功能完善提示
    private _is_open: number;
     //计算建筑
    private _is_count: number;
     //音效
    private _sound: number;
     //当NPC进入此建筑时NPC是否有半透明效果
    private _translucence: number;

    /**
     * ID
     *
     * @remark 注释列
     * @returns {number}
     */
    get building_type(): number {
        return this._building_type;
    }
    /**
     * 后端类型
     *
     * @remark 特效type50仅为前端用到，后端无用，更改需告知前后端
     * @returns {number}
     */
    get main_type(): number {
        return this._main_type;
    }
    /**
     * 建筑
     *
     * @remark 建筑名称
     * @returns {string}
     */
    get name(): string {
        return this._name;
    }
    /**
     * 建筑描述
     *
     * @remark 建筑描述
     * @returns {string}
     */
    get desc(): string {
        return this._desc;
    }
    /**
     * 是否可以升级
     *
     * @remark 0：代表不能升级
1：代表可以升级
     * @returns {number}
     */
    get promotion(): number {
        return this._promotion;
    }
    /**
     * 规格X宽度
     *
     * @remark 
     * @returns {number}
     */
    get layoutX(): number {
        return this._layoutX;
    }
    /**
     * 规格Y宽度
     *
     * @remark 
     * @returns {number}
     */
    get layoutY(): number {
        return this._layoutY;
    }
    /**
     * 门X坐标
     *
     * @remark 坐标从0开始
     * @returns {number}
     */
    get gateX(): number {
        return this._gateX;
    }
    /**
     * 门Y坐标
     *
     * @remark 
     * @returns {number}
     */
    get gateY(): number {
        return this._gateY;
    }
    /**
     * 建筑key
     *
     * @remark 建筑key指的是对应类型的1级建筑的ID
     * @returns {number}
     */
    get type_idx(): number {
        return this._type_idx;
    }
    /**
     * 前端类型
     *
     * @remark 建筑列表分类
     * @returns {number}
     */
    get type(): number {
        return this._type;
    }
    /**
     * 前端排序
     *
     * @remark 建筑列表排序
0不显示
     * @returns {number}
     */
    get building_order(): number {
        return this._building_order;
    }
    /**
     * 路
     *
     * @remark 0不需要
1需要
     * @returns {number}
     */
    get needRoad(): number {
        return this._needRoad;
    }
    /**
     * 可建造地形
     *
     * @remark 4.0
     * @returns {number}
     */
    get terrain_flags(): number {
        return this._terrain_flags;
    }
    /**
     * 有没有门
     *
     * @remark 5.0
     * @returns {number}
     */
    get isDoor(): number {
        return this._isDoor;
    }
    /**
     * 供需类型
     *
     * @remark 0 无
1 提供工人
2 需要工人

     * @returns {number}
     */
    get supply_type(): number {
        return this._supply_type;
    }
    /**
     * 供需人口类型
     *
     * @remark 平民 1 -> 修改为 233
骑士 2 -> 修改为 234
贵族 3 -> 修改为 235

     * @returns {number}
     */
    get supply_population_type(): number {
        return this._supply_population_type;
    }
    /**
     * 环境等级
     *
     * @remark 
     * @returns {number}
     */
    get environment_level(): number {
        return this._environment_level;
    }
    /**
     * 环境辐射范围
     *
     * @remark 
     * @returns {number}
     */
    get environment_radiate_range(): number {
        return this._environment_radiate_range;
    }
    /**
     * 官员类型
     *
     * @remark 0-无效类型，代表该建筑无法放置官员
1-农政官
2-军官
3-鉴政官
     * @returns {number}
     */
    get official_main_type(): number {
        return this._official_main_type;
    }
    /**
     * 官员等级
     *
     * @remark 军团只能放10级千夫长。
其他建筑1级官员只要满足前置技能就可以放置。
     * @returns {number}
     */
    get official_min_level(): number {
        return this._official_min_level;
    }
    /**
     * 官员技能类型
     *
     * @remark 0：没有
1：种植
2：采集
3：畜牧
4：冶炼
5：制造
6：酿造
7：贩卖
8：仓储
9：意志
10：格杀
11：破甲
12：突袭
13：精准
14：格挡
15：秩序
16：教育
17：医疗
18：卫生
19：宗教
20：司法
21：艺术
22：竞争
     * @returns {number}
     */
    get official_skill_main_type(): number {
        return this._official_skill_main_type;
    }
    /**
     * 语言包建筑ID
     *
     * @remark 填写语言包对应ID
     * @returns {number}
     */
    get name_id(): number {
        return this._name_id;
    }
    /**
     * 语言包建筑描述
     *
     * @remark 填写语言包建筑描述对应ID
     * @returns {number}
     */
    get description_id(): number {
        return this._description_id;
    }
    /**
     * 语言包建筑作用ID
     *
     * @remark 填写语言包建筑作用对应ID
     * @returns {number}
     */
    get effect_id(): number {
        return this._effect_id;
    }
    /**
     * 语言包建筑说明ID
     *
     * @remark 填写语言包建筑说明对应ID
     * @returns {number}
     */
    get explain_id(): number {
        return this._explain_id;
    }
    /**
     * 详情按钮
     *
     * @remark 0：不开放
1：开放
     * @returns {number}
     */
    get details_button(): number {
        return this._details_button;
    }
    /**
     * 升级按钮
     *
     * @remark 0：不开放
1：开放
     * @returns {number}
     */
    get upgrade_button(): number {
        return this._upgrade_button;
    }
    /**
     * 技能按钮
     *
     * @remark 0：不开放
1：开放
     * @returns {number}
     */
    get skill_button(): number {
        return this._skill_button;
    }
    /**
     * 兵器按钮
     *
     * @remark 0：不开放
1：开放
     * @returns {number}
     */
    get weapons_button(): number {
        return this._weapons_button;
    }
    /**
     * 官员按钮
     *
     * @remark 0：不开放
1：开放
     * @returns {number}
     */
    get officer_button(): number {
        return this._officer_button;
    }
    /**
     * 铲除按钮
     *
     * @remark 0：不开放
1：开放
     * @returns {number}
     */
    get dismantle_button(): number {
        return this._dismantle_button;
    }
    /**
     * 功能完善提示
     *
     * @remark 0：不开放
1：开放
填0提示：功能暂未开放
     * @returns {number}
     */
    get is_open(): number {
        return this._is_open;
    }
    /**
     * 计算建筑
     *
     * @remark 0：不计算建筑数量
1：计算建筑数量

     * @returns {number}
     */
    get is_count(): number {
        return this._is_count;
    }
    /**
     * 音效
     *
     * @remark 
     * @returns {number}
     */
    get sound(): number {
        return this._sound;
    }
    /**
     * 当NPC进入此建筑时NPC是否有半透明效果
     *
     * @remark 0:不透明
1：透明
     * @returns {number}
     */
    get translucence(): number {
        return this._translucence;
    }
   
    public parseJson(data: any): void {
    	this._building_type = data.building_type;
	this._main_type = data.main_type;
	this._name = data.name;
	this._desc = data.desc;
	this._promotion = data.promotion;
	this._layoutX = data.layoutX;
	this._layoutY = data.layoutY;
	this._gateX = data.gateX;
	this._gateY = data.gateY;
	this._type_idx = data.type_idx;
	this._type = data.type;
	this._building_order = data.building_order;
	this._needRoad = data.needRoad;
	this._terrain_flags = data.terrain_flags;
	this._isDoor = data.isDoor;
	this._supply_type = data.supply_type;
	this._supply_population_type = data.supply_population_type;
	this._environment_level = data.environment_level;
	this._environment_radiate_range = data.environment_radiate_range;
	this._official_main_type = data.official_main_type;
	this._official_min_level = data.official_min_level;
	this._official_skill_main_type = data.official_skill_main_type;
	this._name_id = data.name_id;
	this._description_id = data.description_id;
	this._effect_id = data.effect_id;
	this._explain_id = data.explain_id;
	this._details_button = data.details_button;
	this._upgrade_button = data.upgrade_button;
	this._skill_button = data.skill_button;
	this._weapons_button = data.weapons_button;
	this._officer_button = data.officer_button;
	this._dismantle_button = data.dismantle_button;
	this._is_open = data.is_open;
	this._is_count = data.is_count;
	this._sound = data.sound;
	this._translucence = data.translucence;
	
    }
}
}