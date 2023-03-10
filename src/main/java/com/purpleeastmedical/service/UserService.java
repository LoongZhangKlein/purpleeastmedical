package com.purpleeastmedical.service;

import com.purpleeastmedical.dto.UserDTO;
import com.purpleeastmedical.entity.User;

import java.util.Map;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-16-14:07
 */
public interface UserService {
    /**
     * 用户登录
     */
    String userLogin(UserDTO userDTO);
    UserDTO userInfo(String token);
    /**
     * 用户注册模块
     * @param userDTO
     * @return
     */
    UserDTO userRegister(UserDTO userDTO);
}
