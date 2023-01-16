package com.purpleeastmedical.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author loongzhang
 * @Description 用户数据表
 * @date 2023-01-16-10:55
 */
@Data
public class User implements Serializable {
    @TableId
    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String userName;
//    @NotNull(message = "账号不能为空")
//    private String accounts;
    @NotNull(message = "密码不能为空")
    private String passWord;
    @NotNull(message = "电话不能为空")
    private String mobile;
    @NotNull(message = "邮箱不能为空")
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String address;
    private String deleteFlag;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String updateBy;

}
