package com.assignment.store.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "MATERIAL_ID")
    private Long materialId;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "SUPPLIER_ID")
    private Long supplierId;
}
