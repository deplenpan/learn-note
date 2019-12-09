package com.icbc.patrol.controller;

import com.icbc.common.constants.ExceptionEnum;
import com.icbc.common.constants.MessageConstants;
import com.icbc.common.constants.StatusConstants;
import com.icbc.common.exceptions.CustomException;
import com.icbc.common.model.vo.ResponseResult;
import com.icbc.common.util.ValidatorUtil;
import com.icbc.patrol.model.Event;
import com.icbc.patrol.service.EventService;
import com.icbc.patrol.validator.IdValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;
import java.util.Objects;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/24 14:28
 */
@RestController
@RequestMapping("event")
@Api(value = "事件单管理模块", tags = "针对事件单数据进行增删改查")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    /**
     * 调用查询数据接口次数统计
     */
    private static int QUERY_COUNT;

    /**
     * 调用新增数据接口次数统计
     */
    private static int ADD_COUNT;

    /**
     * 调用更新数据接口次数统计
     */
    private static int UPDATE_COUNT;

    /**
     * 调用删除数据接口次数统计
     */
    private static int DELETE_COUNT;

    @RequestMapping(value = "/query/{eventNum}", method = RequestMethod.GET)
    @ApiOperation(value = "根据事件单编号查询事件单", httpMethod = "GET", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "eventNum", value = "事件单编号", required = true, paramType = "path")})
    public ResponseEntity<ResponseResult<Event>> query(@PathVariable String eventNum) {
        QUERY_COUNT++;
        logger.info("调用查询数据接口" + QUERY_COUNT + "次");
        Event event = null;
        try {
            event = eventService.queryByEventNum(eventNum);
            logger.info("event variable is " + event);
            if (Objects.isNull(event)) {
                throw new CustomException(ExceptionEnum.EVENT_NUM_NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("查询失败，事件单编号 = [{}]", eventNum, e);
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("查询成功，事件单编号 = [{}]", eventNum);
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, event), HttpStatus.OK);
    }


    @RequestMapping(value = "/queryPeriod", method = RequestMethod.GET)
    @ApiOperation(value = "根据时间段查询事件", httpMethod = "GET", produces = "application/json")
    public ResponseEntity<ResponseResult<List<Event>>> queryPeriod(@RequestParam String startTime, @RequestParam String endTime) {
        QUERY_COUNT++;
        logger.info("调用查询事件接口：" + QUERY_COUNT + "次");
        List<Event> eventList = null;
        try {
            eventList = eventService.queryPeriodEventList(startTime, endTime);
        } catch (Exception e) {
            logger.error("查询开始时间: [{}], 查询结束时间: [{}]", startTime, endTime, e);
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, eventList), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ApiOperation(value = "新增事件单", httpMethod = "POST", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult<Void>> add(@RequestBody @Validated Event event, BindingResult bindingResult) {
        ADD_COUNT++;
        logger.info("调用新增数据接口" + ADD_COUNT + "次");
        ValidatorUtil.validData(bindingResult);
        try {
            eventService.addEvent(event);
        } catch (Exception e) {
            logger.error("新增事件单失败", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("新增事件单成功，事件单编号 = [{}]", event.getEventNum());
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }

    @RequestMapping(value = "/update/status", method = RequestMethod.PUT, headers = {"content-type=application/json"})
    @ApiOperation(value = "修改事件单状态", httpMethod = "PUT", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult<Void>> update(@RequestBody @Validated({IdValidator.class, Default.class}) Event event, BindingResult bindingResult) {
        UPDATE_COUNT++;
        logger.info("调用更新数据接口" + UPDATE_COUNT + "次");
        ValidatorUtil.validData(bindingResult);
        try {
            int update = eventService.updateByEventNum(event);
            if (update < 1) {
                throw new CustomException(ExceptionEnum.EVENT_NUM_NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("修改事件单失败，事件单编号 = [{}]", event, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("修改事件单成功，事件单编号 = [{}]", event);
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }


    /**
     * 使用mybatis-plus自带方法进行逻辑删除
     *
     * @param event request event
     * @return delete result
     */
    @RequestMapping(value = "/update/logic_delete", method = RequestMethod.PUT)
    @ApiOperation(value = "逻辑删除事件单", httpMethod = "PUT", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult<Void>> logicDelete(@RequestBody @Validated({IdValidator.class, Default.class}) Event event, BindingResult bindingResult) {
        ValidatorUtil.validData(bindingResult);
        try {
            int logic = eventService.logicDeleteByEventNum(event);
            if (logic < 1) {
                throw new CustomException(ExceptionEnum.EVENT_NUM_NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("逻辑删除失败，事件单编号 = [{}]", event.getEventNum(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("逻辑删除成功，事件单编号 = [{}]", event.getEventNum());
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }


    @RequestMapping(value = "/delete/{eventNum}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据事件单编号删除事件单", httpMethod = "DELETE", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "eventNum", value = "事件单编号", required = true, paramType = "path")})
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult<Void>> delete(@PathVariable String eventNum) {
        DELETE_COUNT++;
        logger.info("调用删除数据接口" + DELETE_COUNT + "次");
        try {
            int delete = eventService.deleteByEventNum(eventNum);
            if (delete < 1) {
                throw new CustomException(ExceptionEnum.EVENT_NUM_NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("删除事件单失败", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("删除事件单成功");
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }


}
