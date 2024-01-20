package com.assignment.store.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Table(name = "CLOTHING")
public class ClothingApparel extends Product {

    @Column(name = "COLOR")
    private String color;

    @Column(name = "EU_SIZE")
    private String euSize;

}
