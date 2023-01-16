package com.purpleeastmedical.config.Check;

import com.purpleeastmedical.dto.UserDTO;
import com.purpleeastmedical.entity.User;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-16-14:20
 */
@Component
public class UserCheck {


    /**
     * 验证用户信息
     *
     * @param user
     * @return
     */
    public String checkUser(UserDTO user) {
        StringBuilder checkMessage=new StringBuilder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        // 验证所有bean的约束
        Set<ConstraintViolation<UserDTO>> validateSet = validator.validate(user);
        validateSet.stream().forEach(validatorOne -> {
            if (validatorOne.getMessage()!=null){
                checkMessage.append(validatorOne.getMessage() + ",");
            }

        });
        return checkMessage.toString();
    }

}
