package com.icbc.common.constants;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/20 9:03
 */
public enum ExceptionEnum {

    /**
     * 参数不能为空
     */
    PARAMETER_CANNOT_BE_NULL(400, "参数不能为空"),
    PARAMETER_ID_NOT_EXIST(400, "抱歉，您查询的id不存在，请输入正确的id!"),
    EVENT_NUM_NOT_EXIST(400, "抱歉，您查询的事件单编号不存在，请输入正确的事件单编号!"),
    OPERATION_FAILED(500,"操作失败"),
    PARAMETER_ID_MUST_BE_POSITIVE(400,"id必须为正数");

    private int status;
    private String message;

    ExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
