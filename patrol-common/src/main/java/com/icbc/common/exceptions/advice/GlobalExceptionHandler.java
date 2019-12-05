package com.icbc.common.exceptions.advice;

import com.icbc.common.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/20 8:53
 * @description : @ControllerAdvice 声明当前这个类就是一个对Controller进行拦截的通知
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 注解@ExceptionHandler(CustomException.class) 声明当前这个方法要处理的异常的类型
     *
     * @param e 拦截controller以后，接收抛出的具体异常
     * @return response result
     */
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<String> handleException(CustomException e) {
        int code = e.getExceptionEnum().getStatus();
        String message = e.getExceptionEnum().getMessage();
        return ResponseEntity.status(code).body(message);
    }

    /**
     * 处理Validator框架验证的错误
     *
     * @param exception ValidationException
     * @return response result
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(ValidationException exception) {
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                logger.info(item.getMessage());
            }
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

}
