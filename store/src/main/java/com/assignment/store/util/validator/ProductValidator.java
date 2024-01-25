package com.assignment.store.util.validator;

import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.staticdata.Material;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.repository.ClothingApparelRepository;
import com.assignment.store.repository.MaterialRepository;
import com.assignment.store.repository.SupplierRepository;
import com.assignment.store.util.FieldProcessingUtil;
import com.assignment.store.util.enums.ProductProperty;
import com.assignment.store.util.enums.ProductType;
import com.assignment.store.util.exception.MaterialNotFoundException;
import com.assignment.store.util.exception.SupplierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private MaterialRepository materialRepository;

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
        validateMaterial(productDTO);
        Long supplierId = validateSupplier(productDTO);
        validateUniqueConstraint(productDTO, errors, supplierId);
    }

    private void validateUniqueConstraint(ProductDTO productDTO, Errors errors, Long supplierId) {
        if (ProductType.findByName(productDTO.getType()).get().getCorrespondingClass().equals(ClothingApparel.class)) {
            List<ClothingApparel> conflictingItems = clothingApparelRepository.findByNameAndSupplierIdAndColorAndEuSize(productDTO.getName(), supplierId, productDTO.getColor(), productDTO.getEuSize());
            if (conflictingItems.size() == 0) {
                return;
            }
            var maxSize = 1;
            if (conflictingItems.size() > maxSize || conflictingItems.get(0).getId() != productDTO.getId()) {
                errors.reject("A conflicting item with same name, supplier, color and size was found.");
            }
        }
    }

    private void validateMaterial(ProductDTO productDTO) {
        Optional<Material> material = materialRepository.findById(productDTO.getMaterialId());
        if (material.isEmpty()) {
            throw new MaterialNotFoundException("Corresponding material not found.");
        }
    }

    private Long validateSupplier(ProductDTO productDTO) {
        Supplier supplier = supplierRepository.findByName(productDTO.getSupplier());
        if (supplier == null) {
            throw new SupplierNotFoundException("Corresponding supplier not found.");
        }
        return supplier.getId();
    }

}
