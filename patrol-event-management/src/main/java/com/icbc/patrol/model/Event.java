package com.icbc.patrol.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.icbc.common.model.po.BasePojo;
import com.icbc.patrol.validator.IdValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 17:13
 */
@ApiModel(value = "事件单信息")
@Accessors(chain = true)
public class Event extends BasePojo implements Serializable {

    private static final long serialVersionUID = 3428966904217724955L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(required = true, name = "id", value = "事件表主键", dataType = "query")
    @NotNull(groups = IdValidator.class, message = "id不能为空")
    private Long id;

    @ApiModelProperty(name = "eventNum", value = "事件单编号", dataType = "query")
    @NotNull(message = "事件单编号不能为空")
    private String eventNum;

    @ApiModelProperty(name = "equipmentID", value = "设备id", dataType = "query")
    @NotNull(message = "设备编号不能为空")
    private String equipmentId;

    @ApiModelProperty(name = "location", value = "设备故障发生位置", dataType = "query")
    @NotNull(message = "设备故障发生位置不能为空")
    private String location;

    @ApiModelProperty(name = "model", value = "含义待定", dataType = "query")
    private String model;

    @ApiModelProperty(name = "roomNum", value = "设备所在房间编号", dataType = "query")
    @NotNull(message = "房间编号不能为空")
    private String roomNum;

    @ApiModelProperty(name = "problem", value = "设备故障描述", dataType = "query")
    private String problem;

    @ApiModelProperty(name = "cause", value = "设备故障发生原因", dataType = "query")
    private String cause;

    @ApiModelProperty(name = "solution", value = "解决方案", dataType = "query")
    private String solution;

    @ApiModelProperty(name = "isCompleted", value = "设备故障问题是否解决，0：解决，1：未解决", dataType = "query")
    private int isCompleted;

    /**
     * 使用mybatis-plus自带方法删除和查找都会附带逻辑删除功能 (自己写的xml不会)
     */
    @ApiModelProperty(name = "isDeleted", value = "逻辑删除标识，0：删除，1：未删除", dataType = "query")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(name = "version", value = "版本", dataType = "query")
    private String version;

    @Override
    public String toString() {
        return String.format("Event{id=%d, eventNum='%s', equipmentId='%s', location='%s', model='%s', roomNum='%s', problem='%s', cause='%s', solution='%s', isCompleted=%d, isDeleted=%d, version='%s'}", id, eventNum, equipmentId, location, model, roomNum, problem, cause, solution, isCompleted, isDeleted, version);
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventNum() {
        return eventNum;
    }

    public void setEventNum(String eventNum) {
        this.eventNum = eventNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }


}
