package com.icbc.patrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icbc.patrol.model.User;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/15 15:16
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询用户
     *
     * @param id user id
     * @return user
     */
    User queryUserById(Long id);

    /**
     * 新增用户
     *
     * @param user add user
     * @return insert result
     */
    int addUser(User user);

    /**
     * 根据主键修改用户数据
     *
     * @param user update specified user
     * @return update result
     */
    int updateUserById(User user);

    /**
     * 根据主键删除用户数据
     *
     * @param id user id
     * @return delete result
     */
    int deleteUserById(Long id);

}
