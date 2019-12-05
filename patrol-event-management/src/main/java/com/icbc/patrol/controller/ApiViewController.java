package com.icbc.patrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/12/2 15:52
 * @description : ApiViewController，控制显示api接口的html
 */
@Controller
@RequestMapping("api")
public class ApiViewController {

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String apiView() {
        return "patrolApiDocs";
    }

}
