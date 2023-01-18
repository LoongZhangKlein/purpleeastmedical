package com.purpleeastmedical.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.purpleeastmedical.common.R;
import com.purpleeastmedical.config.Check.UserCheck;
import com.purpleeastmedical.dto.UserDTO;
import com.purpleeastmedical.entity.User;
import com.purpleeastmedical.enums.GlobalEnum;
import com.purpleeastmedical.exception.GlobalException;
import com.purpleeastmedical.mapper.UserMapper;
import com.purpleeastmedical.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-10-16:21
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    UserService userService;
    @Resource
    UserCheck userCheck;

    /**
     * 用户登录模块
     * @param userMap
     * @return
     */
    @PostMapping("/login")
    public R userLogin(@RequestBody Map<String,String> userMap){
            log.info("用户登录请求信息",userMap);
        if (CollectionUtils.isEmpty(userMap)){
            throw new GlobalException(GlobalEnum.PARAMS_EXPRESSION);
        }
        UserDTO user = userService.userLogin(userMap);
        if (userCheck.checkUser(user).length()==0){
            return R.success(GlobalEnum.SUCCESS,user);
        }
        return R.fail(GlobalEnum.FAIL);
    }

    /**
     * 用户注册模块
     * @param
     * @return
     */
    @PostMapping("/register")
    public R userRegister(@RequestBody UserDTO userDTO){
        UserDTO userDTOResult = userService.userRegister(userDTO);
        if (userDTOResult==null){
            return R.fail(GlobalEnum.FAIL);
        }
        return R.success(GlobalEnum.SUCCESS,userDTOResult);
    }
}
