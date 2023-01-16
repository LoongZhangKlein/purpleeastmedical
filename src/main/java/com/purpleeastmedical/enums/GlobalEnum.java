package com.purpleeastmedical.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-16-10:15
 */


@NoArgsConstructor
@AllArgsConstructor
public enum GlobalEnum {


    FAIL("-1","操作失败"),
    SUCCESS("0","操作成功"),
    PARAMS_EXPRESSION("2","参数校验失败");
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
