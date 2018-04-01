namespace xu {
	/**
 * @author xuzhuoxi
 * Created on 2018-04-01.
 */
export class CfgBuilding {
        //ID
    private _building_type: number;
     //后端类型
    private _main_type: number;
     //建筑
    private _name: string;
     //是否可以升级
    private _promotion: number;
     //规格
    private _layoutX: number;
     //建筑key
    private _type_idx: number;
     //门X坐标
    private _gateX: number;
     //门Y坐标
    private _gateY: number;
     //地形
    private _terrain_flags: number;
     //供需人口类型
    private _supply_population_type: number;
     //有没有门
    private _isDoor: number;
     //建筑描述
    private _desc: string;

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
     * 规格
     *
     * @remark 
     * @returns {number}
     */
    get layoutX(): number {
        return this._layoutX;
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
     * 地形
     *
     * @remark 4.0
     * @returns {number}
     */
    get terrain_flags(): number {
        return this._terrain_flags;
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
     * 有没有门
     *
     * @remark 5.0
     * @returns {number}
     */
    get isDoor(): number {
        return this._isDoor;
    }
    /**
     * 建筑描述
     *
     * @remark 建筑描述
string(20)
     * @returns {string}
     */
    get desc(): string {
        return this._desc;
    }
   
    public parseJson(data: any): void {
    	this._building_type = data.building_type;
	this._main_type = data.main_type;
	this._name = data.name;
	this._promotion = data.promotion;
	this._layoutX = data.layoutX;
	this._type_idx = data.type_idx;
	this._gateX = data.gateX;
	this._gateY = data.gateY;
	this._terrain_flags = data.terrain_flags;
	this._supply_population_type = data.supply_population_type;
	this._isDoor = data.isDoor;
	this._desc = data.desc;
	
    }
    
    public parseBinary(proxy: xu.BinaryReaderProxy): void {
    	this._building_type = proxy.readUInt16();
	this._main_type = proxy.readUInt8();
	this._name = proxy.readString();
	this._promotion = proxy.readUInt8();
	this._layoutX = proxy.readUInt16();
	this._type_idx = proxy.readUInt32();
	this._gateX = proxy.readFloat32();
	this._gateY = proxy.readFloat32();
	this._terrain_flags = proxy.readInt32();
	this._supply_population_type = proxy.readInt16();
	this._isDoor = proxy.readInt8();
	this._desc = proxy.readString();
	
    }
}
}