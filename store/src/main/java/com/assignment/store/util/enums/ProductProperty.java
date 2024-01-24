package com.assignment.store.util.enums;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

public enum ProductProperty {
    NAME("name", 5, 100),
    PRICE("price", BigDecimal.valueOf(0.3), BigDecimal.valueOf(999999)),
    DISCOUNT("discount", BigDecimal.ZERO, BigDecimal.valueOf(99)),
    MATERIAL_ID("materialId", true);
    private final String name;
    private Integer minSize;

    private Integer maxSize;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private boolean required;


    private ProductProperty(String name) {
        this.name = name;
    }

    private ProductProperty(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    private ProductProperty(String name, Integer minSize, Integer maxSize) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    private ProductProperty(String name, BigDecimal minValue, BigDecimal maxValue) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    private static Optional<ProductProperty> findByName(String name) {
        return Stream.of(ProductProperty.values()).filter(productType -> productType.getName().equals(name)).findFirst();
    }

    public static void validateField(String fieldName, Object value, Errors errors) {
        Optional<ProductProperty> propertyOptional = ProductProperty.findByName(fieldName);
        if (propertyOptional.isPresent()) {
            ProductProperty property = propertyOptional.get();
            boolean isValueNull = value == null;
            if (property.required && isValueNull) {
                errors.reject("Required field " + fieldName + "can not be empty.");
            }
            if (!isValueNull && property.minSize != null && value.toString().length() < property.minSize) {
                errors.reject("Minimum length for " + fieldName + "  is " + property.minSize);
            }
            if (!isValueNull && property.maxSize != null && value.toString().length() > property.maxSize) {
                errors.reject("Maximum length for " + fieldName + " should be " + property.minSize);
            }
            if (!isValueNull && property.minValue != null && ((BigDecimal) value).compareTo(property.minValue) < 0) {
                errors.reject("Minimum allowed value for" + fieldName + " is " + property.minValue);
            }
            if (!isValueNull && property.maxValue != null && ((BigDecimal) value).compareTo(property.maxValue) > 0) {
                errors.reject("Maximum allowed value for " + fieldName + " is " + property.minValue);
            }
        }
    }

}

