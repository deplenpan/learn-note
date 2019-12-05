package com.icbc.common.constants;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 13:21
 */
public enum MessageConstants {

    /**
     * 操作成功
     */
    OPERATION_SUCCESS("Operation Success"),

    /**
     * 操作失败
     */
    OPERATION_FAILURE("Operation Failure");

    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
