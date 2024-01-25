package com.assignment.store.dto.product;

import com.assignment.store.util.enums.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscountDTO {
    private BigDecimal discountValue;
    private ProductType productType;
}
