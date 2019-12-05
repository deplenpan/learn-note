package com.icbc.common.constants;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 13:18
 */
public enum StatusConstants {
    /**
     * 操作成功，状态码为0
     */
    SUCCESS(0),

    /**
     * 操作失败，状态码为1
     */
    FAILURE(1);

    private final int code;

    StatusConstants(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
