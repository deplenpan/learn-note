package com.icbc.patrol.controller;

import com.icbc.common.constants.MessageConstants;
import com.icbc.common.constants.StatusConstants;
import com.icbc.common.model.vo.ResponseResult;
import com.icbc.patrol.socket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/12/6 16:39
 */
@Controller
@RequestMapping("checkcenter")
public class CheckCenterController {

    private static Logger logger = LoggerFactory.getLogger(CheckCenterController.class);

    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public ResponseEntity<ResponseResult<Void>> pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            logger.error(cid + "#" + e.getMessage());
        }
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }

}
