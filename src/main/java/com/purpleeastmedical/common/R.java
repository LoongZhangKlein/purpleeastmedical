package com.purpleeastmedical.common;

import com.purpleeastmedical.enums.GlobalEnum;
import lombok.Data;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-16-14:48
 */
@Data
public class R<T> {
    private String code;
    private String message;
    private T date;
    private static R r;
    public static <T> R success(GlobalEnum globalEnum,T t){
        r=new R();
        r.code=globalEnum.getCode();
        r.message=globalEnum.getMsg();
        r.date=t;
        return r;
    }
    public static <T> R fail(GlobalEnum globalEnum,T t){
        r=new R();
        r.code=globalEnum.getCode();
        r.message=globalEnum.getMsg();
        r.date=t;
        return r;
    }
    public static <T> R fail(GlobalEnum globalEnum){
        r=new R();
        r.code=globalEnum.getCode();
        r.message=globalEnum.getMsg();
        return r;
    }

}
