package com.assignment.store.dao;

import com.assignment.store.dao.staticdata.Material;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.util.converters.ProductTypeConverter;
import com.assignment.store.util.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@MappedSuperclass
public class Product {

    @Column(name = "TYPE")
    @Convert(converter = ProductTypeConverter.class)
    protected ProductType type;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "PRICE")
    protected BigDecimal price;

    @Column(name = "DISCOUNT")
    protected BigDecimal discount;

    @Column(name = "STOCK")
    protected Integer stock;

    @Column(name = "SUPPLIER_ID", insertable=false, updatable=false, nullable=false)
    protected Long supplierId;

    @ManyToOne
    @JoinColumn(name="SUPPLIER_ID")
    protected Supplier supplier;

    @ManyToOne
    @JoinColumn(name="MATERIAL_ID")
    protected Material material;

}
