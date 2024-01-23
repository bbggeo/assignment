package com.assignment.store.util.converters;

import com.assignment.store.util.enums.ProductType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, String> {
    @Override
    public String convertToDatabaseColumn(ProductType productType) {
        if (productType == null) {
            return null;
        }
        return productType.getName();
    }

    @Override
    public ProductType convertToEntityAttribute(String s) {
        if(s == null) {
            return null;
        }
        return ProductType.findByName(s);
    }
}
