package com.purpleeastmedical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.purpleeastmedical.config.Check.UserCheck;
import com.purpleeastmedical.dto.UserDTO;
import com.purpleeastmedical.entity.User;
import com.purpleeastmedical.enums.GlobalEnum;
import com.purpleeastmedical.exception.GlobalException;
import com.purpleeastmedical.mapper.UserMapper;
import com.purpleeastmedical.service.UserService;
import com.purpleeastmedical.utils.GenerateToken;
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
     * @param userMap
     * accounts: "账户"
     * pwd: "登录密码"
     * type: "账户类型"
     * verify: "验证码"
     */
    @Override
    public UserDTO userLogin(Map<String,String> userMap) {
        if (CollectionUtils.isEmpty(userMap)){
            throw new GlobalException(GlobalEnum.PARAMS_EXPRESSION);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userMap.get("accounts"));
        queryWrapper.eq(User::getPassWord,userMap.get("passWord"));
        User user = userMapper.selectOne(queryWrapper);
        // 用户验证成功
        UserDTO userDTO = new UserDTO();
        if (user!=null){
            // 拷贝
            BeanUtils.copyProperties(user,userDTO);
            String token = GenerateToken.generateToken(userDTO);
            userDTO.setToken(token);
            userDTO.setCode("0");
            userDTO.setAccounts(user.getUserName());
        }
        return userDTO;
    }
    /**
     * 用户注册模块[入参不推荐使用Map]
     * @param userDTO
     * @return
     */
    @Override
    public UserDTO userRegister(UserDTO userDTO) {
        if (userCheck.checkUser(userDTO).length()>0){
            throw new GlobalException(GlobalEnum.PARAMS_EXPRESSION);
        }
        // 组装用户数据
        User user = buildUserMessage(userDTO);
        // 更新用户
        int insert = userMapper.insert(user);
        if (insert<=0){
            return null;
        }
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setToken(GenerateToken.generateToken(userDTO));
        return userDTO;
    }

    /**
     * 组装用户数据
     * @param userDTO
     * @return
     */
    private User buildUserMessage(UserDTO userDTO){
        User user = new User();
        if (userDTO==null){
            return null;
        }
        BeanUtils.copyProperties(userDTO,user);
        Date date = new Date();
        user.setUpdateTime(date);
        user.setAddTime(date);
        return user;
    }


}
