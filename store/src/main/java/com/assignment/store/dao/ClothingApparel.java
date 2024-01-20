package com.assignment.store.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Table(name = "CLOTHING")
@Entity
public class ClothingApparel extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "EU_SIZE")
    private String euSize;

}
