package com.icbc.common.exceptions;

import com.icbc.common.constants.ExceptionEnum;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/20 9:08
 */
public class CustomException extends RuntimeException {

    private int status;

    private ExceptionEnum exceptionEnum;

    public CustomException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;
        this.status = exceptionEnum.getStatus();
    }

    public CustomException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum.getMessage(), cause);
        this.status = exceptionEnum.getStatus();
        this.exceptionEnum = exceptionEnum;
    }

    public int getStatus() {
        return status;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
