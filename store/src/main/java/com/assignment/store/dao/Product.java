package com.assignment.store.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "TYPE")
    protected String type;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "PRICE")
    protected BigDecimal price;

    @Column(name = "MATERIAL_ID")
    protected Long materialId;

    @Column(name = "DISCOUNT")
    protected BigDecimal discount;

    @Column(name = "STOCK")
    protected Integer stock;

    @Column(name = "SUPPLIER_ID")
    protected Long supplierId;
}
