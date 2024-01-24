package com.assignment.store.util.validator;

import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.repository.ClothingApparelRepository;
import com.assignment.store.repository.SupplierRepository;
import com.assignment.store.util.FieldProcessingUtil;
import com.assignment.store.util.enums.ProductProperty;
import com.assignment.store.util.enums.ProductType;
import com.assignment.store.util.exception.SupplierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.isAssignableFrom(clazz);
    }

    @Autowired
    private ClothingApparelRepository clothingApparelRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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
        validateUniqueConstraint(productDTO, errors);
    }

    private void validateUniqueConstraint(ProductDTO productDTO, Errors errors) {
        Supplier supplier = supplierRepository.findByName(productDTO.getSupplier());
        if (supplier == null) {
            throw new SupplierNotFoundException("Corresponding supplier not found.");
        }
        if (ProductType.findByName(productDTO.getType()).getCorrespondingClass().equals(ClothingApparel.class)) {
            List<ClothingApparel> conflictingItems = clothingApparelRepository.findByNameAndSupplierIdAndColorAndEuSize(productDTO.getName(), supplier.getId(), productDTO.getColor(), productDTO.getEuSize());
            if (conflictingItems.size() == 0) {
                return;
            }
            if (conflictingItems.size() > 1 || conflictingItems.get(0).getId() != productDTO.getId()) {
                errors.reject("A conflicting item with same name, supplier, color and size was found.");
            }
        }
    }
}
