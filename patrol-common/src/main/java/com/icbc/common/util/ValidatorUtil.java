package com.icbc.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import javax.validation.ValidationException;
import java.io.Serializable;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 16:04
 */
public class ValidatorUtil implements Serializable {

    private static final long serialVersionUID = 2121126347649428306L;

    private static final Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    /**
     * 校验数据
     *
     * @param bindingResult binding result
     */
    public static void validData(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            for (ObjectError error : bindingResult.getAllErrors()) {
                sb.append(error.getDefaultMessage());
            }
            throw new ValidationException(sb.toString());
        }
    }

}
