package com.purpleeastmedical.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author loongzhang
 * @Description 用户数据表
 * @date 2023-01-16-10:55
 */
@Data
public class UserDTO implements Serializable {

    @NotNull
    private String userName;
    private String code;

    private String accounts;

    private String passWord;
    @NotNull
    private String mobile;
    @NotNull
    private String token;

    private String email;
    private String gender;
    private String numberCode;
    private String systemType;
    private String status;
    private String nickName;
    private String avatar;
    private String province;
    private String city;
    private String county;
    private String birthday;
    private String address;
    private String deleteFlag;
    private String addTime;
    private String updateTime;
    private String updateBy;

}
