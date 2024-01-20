package com.assignment.store.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String type;
    private String name;
    private BigDecimal price;
    private String material;
    private String materialDescription;
    private BigDecimal discount;
    private Integer stock;
    private String supplier;
}
