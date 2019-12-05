package com.icbc.common.model.vo;

import com.icbc.common.constants.MessageConstants;
import com.icbc.common.constants.StatusConstants;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/20 10:18
 */
public class ResponseResult<T> extends Object{

    private int code;

    private String message;

    private T data;

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(StatusConstants sc, MessageConstants mc, T data) {
        this.code = sc.getCode();
        this.message = mc.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
