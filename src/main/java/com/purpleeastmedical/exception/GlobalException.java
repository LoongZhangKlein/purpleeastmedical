package com.purpleeastmedical.exception;

import com.purpleeastmedical.enums.GlobalEnum;
import lombok.Data;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-16-10:21
 */
@Data
public class GlobalException extends RuntimeException{
    private String code;
    private String message;
    public GlobalException(String message){
        super(message);
    }
    public GlobalException(String code,String message){
        super(message);
        this.code=code;
    }
    public GlobalException(GlobalEnum globalEnum){
        super(globalEnum.getMsg());
        this.code=globalEnum.getCode();
    }
}
