package com.assignment.store.util.validator;

import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.util.FieldProcessingUtil;
import com.assignment.store.util.enums.ProductProperty;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        // remove blank spaces from the beginning and the end of name
        productDTO.setName(productDTO.getName().strip());
        for (Field field : FieldProcessingUtil.getAllFields(target.getClass())) {
            field.setAccessible(true);
            try {
                ProductProperty.validateField(field.getName(), field.get(productDTO), errors);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
