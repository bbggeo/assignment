package com.assignment.store.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    protected Long id;
    protected String type;
    protected String name;
    protected BigDecimal price;
    protected Long materialId;
    protected String material;
    protected String materialDescription;
    protected BigDecimal discount;
    protected Integer stock;
    protected String supplier;
}
