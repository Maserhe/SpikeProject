package com.maserhe.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-20 20:37
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object bean) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(bean);

        if (constraintViolations.size() > 0) {
            // 有错误
            result.setHasError(true);
            // 设置错误的 属性还有格式
            constraintViolations.forEach(constraintViolation -> {
                String errorMessage = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getGetErrorMsg().put(errorMessage,propertyName);
            });
        }
        return result;

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //通过 Validation 实例化工厂进行实例化 validator
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
