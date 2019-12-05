package com.icbc.patrol.controller;

import com.icbc.common.constants.ExceptionEnum;
import com.icbc.common.constants.MessageConstants;
import com.icbc.common.constants.StatusConstants;
import com.icbc.common.exceptions.CustomException;
import com.icbc.common.util.ValidatorUtil;
import com.icbc.common.model.vo.ResponseResult;
import com.icbc.patrol.model.User;
import com.icbc.patrol.service.UserService;
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
import java.util.Objects;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/15 15:15
 */
@RestController
@RequestMapping("user")
@Api(value = "用户管理模块", tags = "针对用户数据进行增删改查")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 调用登录接口次数统计
     */
    private static int LOGIN_COUNT;

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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<User> test() {
        LOGIN_COUNT++;
        logger.info("调用登录接口" + LOGIN_COUNT + "次");
        User data = new User();
        data.setId(1);
        data.setName("zhangsan");
        data.setAge(12);
        data.setUsername("007");
        data.setPassword("123456");
        System.out.println(data);
        logger.info("data" + data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * 根据id查询用户
     *
     * @param id user id
     * @return response result information
     */
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询用户", httpMethod = "GET", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")})
    public ResponseEntity<User> queryUser(@PathVariable Long id) {
        QUERY_COUNT++;
        logger.info("调用查询数据接口" + QUERY_COUNT + "次");
        User user = null;
        try {
            user = userService.queryUserById(id);
            if (Objects.isNull(user)) {
                logger.warn("user can not be null");
                throw new CustomException(ExceptionEnum.PARAMETER_CANNOT_BE_NULL);
            }
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * 新增用户信息
     *
     * @param user specified added user
     * @return response result
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ApiOperation(value = "新增用户", httpMethod = "POST", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult<Void>> addUser(@RequestBody @Validated User user, BindingResult bindingResult) {
        ADD_COUNT++;
        logger.info("调用新增数据接口" + ADD_COUNT + "次");
        ValidatorUtil.validData(bindingResult);
        logger.info(">>>>>>>>>>>>>>>> 新增用户 <<<<<<<<<<<<<<<<");
        try {
            userService.addUser(user);
        } catch (Exception e) {
            logger.error("新增用户[{}]失败", user, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("新增用户[{}]成功", user);
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }

    /**
     * 修改用户信息
     *
     * @param user specified updated user
     * @return response result
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = {"content-type=application/json"})
    @ApiOperation(value = "修改用户", httpMethod = "PUT", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult<Void>> updateUser(@RequestBody @Validated({IdValidator.class, Default.class}) User user, BindingResult bindingResult) {
        UPDATE_COUNT++;
        logger.info("调用更新数据接口" + UPDATE_COUNT + "次");
        ValidatorUtil.validData(bindingResult);
        logger.info(">>>>>>>>>>>>>>>> 修改用户 <<<<<<<<<<<<<<<<");
        try {
            int update = userService.updateUserById(user);
            if (update < 1) {
                throw new CustomException(ExceptionEnum.PARAMETER_ID_NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("修改用户[{}]失败", user, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("修改用户[{}]成功", user);
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }

    /**
     * 删除用户
     *
     * @param id user id
     * @return response result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除用户", httpMethod = "DELETE", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")})
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult<Void>> deleteUser(@PathVariable Long id) {
        DELETE_COUNT++;
        logger.info("调用删除数据接口" + DELETE_COUNT + "次");
        logger.info(">>>>>>>>>>>>>>>> 删除用户 <<<<<<<<<<<<<<<<");
        try {
            int delete = userService.deleteUserById(id);
            if (delete < 1) {
                throw new CustomException(ExceptionEnum.PARAMETER_ID_NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("删除用户失败", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new CustomException(ExceptionEnum.OPERATION_FAILED);
        }
        logger.info("删除用户成功");
        return new ResponseEntity<>(new ResponseResult<>(StatusConstants.SUCCESS, MessageConstants.OPERATION_SUCCESS, null), HttpStatus.OK);
    }


}
