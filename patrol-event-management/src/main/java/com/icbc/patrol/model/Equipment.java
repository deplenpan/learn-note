package com.icbc.patrol.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.gson.annotations.SerializedName;
import com.icbc.common.model.po.BasePojo;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/21 9:47
 */
@Accessors(chain = true)
@TableName("equipment")
public class Equipment extends BasePojo implements Serializable {
    private static final long serialVersionUID = 2470615504859328706L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * equipment_id : 0101
     * brand : MGE
     * type : 机房专用空调
     * name : 西3-23
     * capacity : 67.28
     * physical_position : 6号楼西3-23
     * logical_position : 西3-23
     * fault : 电池内阻过低设备故障告警
     */

    @SerializedName("equipment_id")
    private String equipmentId;
    @SerializedName("brand")
    private String brand;
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("capacity")
    private String capacity;
    @SerializedName("physical_position")
    private String physicalPosition;
    @SerializedName("logical_position")
    private String logicalPosition;
    @SerializedName("fault")
    private String fault;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPhysicalPosition() {
        return physicalPosition;
    }

    public void setPhysicalPosition(String physicalPosition) {
        this.physicalPosition = physicalPosition;
    }

    public String getLogicalPosition() {
        return logicalPosition;
    }

    public void setLogicalPosition(String logicalPosition) {
        this.logicalPosition = logicalPosition;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }
}
