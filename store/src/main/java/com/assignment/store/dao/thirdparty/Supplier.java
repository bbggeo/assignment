package com.assignment.store.dao.thirdparty;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "SUPPLIER")
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLIER_SEQ")
    @SequenceGenerator(name = "SUPPLIER_SEQ", sequenceName = "SUPPLIER_SEQ", allocationSize = 1)
    protected Long id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "PARTNERSHIP_VALID")
    protected Boolean validPartnership;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ClothingApparel> clothingApparelList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Accessory> accessoryList = new ArrayList<>();
}
