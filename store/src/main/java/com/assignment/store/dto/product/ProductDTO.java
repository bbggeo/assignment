package com.assignment.store.dto.product;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.math.BigDecimal;

@Data
@MappedSuperclass
public class ProductDTO {
    protected Long id;
    protected String type;
    protected String name;
    protected BigDecimal price;
    protected String material;
    protected String materialDescription;
    protected BigDecimal discount;
    protected Integer stock;
    protected String supplier;
}
