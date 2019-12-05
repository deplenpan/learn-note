package com.icbc.patrol.service;

import com.icbc.patrol.model.User;
import org.springframework.stereotype.Service;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/18 10:07
 */
@Service
public interface UserService {
    User queryUserById(Long id);

    int addUser(User user);

    int updateUserById(User user);

    int deleteUserById(Long id);
}
