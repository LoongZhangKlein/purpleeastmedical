package com.purpleeastmedical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purpleeastmedical.config.Check.UserCheck;
import com.purpleeastmedical.dto.UserDTO;
import com.purpleeastmedical.entity.User;
import com.purpleeastmedical.enums.GlobalEnum;
import com.purpleeastmedical.exception.GlobalException;
import com.purpleeastmedical.mapper.UserMapper;
import com.purpleeastmedical.service.UserService;
import com.purpleeastmedical.utils.GenerateToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.annotation.Resource;

import java.util.Date;
import java.util.Map;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-16-14:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    UserCheck userCheck;

    /**
     * 用户登录
     *
     * @param userMap accounts: "账户"
     *                pwd: "登录密码"
     *                type: "账户类型"
     *                verify: "验证码"
     */


    @Override
    public String userLogin(UserDTO userDTO) {
        // 校验用户账号密码
        //成功生成token 返回
        return null;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public UserDTO userInfo(String token) {
        // 通过token获取用户信息
        return null;
    }

    /**
     * 用户注册模块[入参不推荐使用Map]
     *
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO userRegister(UserDTO userDTO) {
        // 校验用户入参信息是否正确
        if (userCheck.checkUser(userDTO).length() > 0) {
            throw new GlobalException(GlobalEnum.PARAMS_EXPRESSION);
        }
        // 校验用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 前端入参用户为accounts
        queryWrapper.eq(User::getAccounts, userDTO.getAccounts());
        User userSelectResult = userMapper.selectOne(queryWrapper);
        if (userSelectResult != null) {
            throw new GlobalException(GlobalEnum.USER_EXIST);
        }
        // 组装用户数据
        User user = buildUserMessage(userDTO);
        if (user == null) {
            throw new GlobalException(GlobalEnum.PARAMS_EXPRESSION);
        }
        // 更新用户
        int insert = userMapper.insert(user);
        if (insert <= 0) {
            return null;
        }
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setToken(GenerateToken.generateToken(userDTO));
        return userDTO;
    }

    /**
     * 组装用户数据
     *
     * @param userDTO
     * @return
     */
    private User buildUserMessage(UserDTO userDTO) {
        User user = new User();
        if (userDTO == null || StringUtils.isEmpty(userDTO.getAccounts())) {
            return null;
        }
        BeanUtils.copyProperties(userDTO, user);
        Date date = new Date();
        user.setUpdateTime(date);
        user.setAddTime(date);
        return user;
    }


}
