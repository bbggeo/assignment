package com.assignment.store.dao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "ACCESSORY")
@Entity
public class Accessory extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCESSORY_SEQ")
    @SequenceGenerator(name = "ACCESSORY_SEQ", sequenceName = "ACCESSORY_SEQ", allocationSize = 1)
    protected Long id;
}
