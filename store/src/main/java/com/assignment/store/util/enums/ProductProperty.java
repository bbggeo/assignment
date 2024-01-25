package com.assignment.store.util.enums;

import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

public enum ProductProperty {
    NAME("name", 5, 100, true),
    PRICE("price", BigDecimal.valueOf(0.3), BigDecimal.valueOf(999999), true),
    DISCOUNT("discount", BigDecimal.ZERO, BigDecimal.valueOf(99), false),
    MATERIAL_ID("materialId", true),
    SUPPLIER("supplier", true);
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

    private ProductProperty(String name, Integer minSize, Integer maxSize, boolean required) {
        this.name = name;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.required = required;
    }

    private ProductProperty(String name, BigDecimal minValue, BigDecimal maxValue, boolean required) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.required = required;
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
                errors.reject(CustomErrorCodes.REQUIRED_FIELD.name(), "Required field " + fieldName + " can not be empty.");
            }
            if (!isValueNull && property.minSize != null && value.toString().length() < property.minSize) {
                errors.reject(CustomErrorCodes.MINIMUM_LENGTH_NOT_COMPLIANT.name(), "Minimum length for " + fieldName + " is " + property.minSize + ".");
            }
            if (!isValueNull && property.maxSize != null && value.toString().length() > property.maxSize) {
                errors.reject(CustomErrorCodes.MAXIMUM_LENGTH_EXCEEDED.name(), "Maximum length for " + fieldName + " should be " + property.minSize + ".");
            }
            if (!isValueNull && property.minValue != null && ((BigDecimal) value).compareTo(property.minValue) < 0) {
                errors.reject(CustomErrorCodes.VALUE_OUT_OF_BOUNDS.name(), "Minimum allowed value for" + fieldName + " is " + property.minValue + ".");
            }
            if (!isValueNull && property.maxValue != null && ((BigDecimal) value).compareTo(property.maxValue) > 0) {
                errors.reject(CustomErrorCodes.VALUE_OUT_OF_BOUNDS.name(), "Maximum allowed value for " + fieldName + " is " + property.maxValue + ".");
            }
        }
    }

}

