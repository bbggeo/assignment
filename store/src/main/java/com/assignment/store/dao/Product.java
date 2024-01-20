package com.assignment.store.dao;

import com.assignment.store.dao.thirdparty.Supplier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name="SUPPLIER_ID", nullable=false)
    protected Supplier supplier;
    
}
