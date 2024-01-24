package com.assignment.store.dao.staticdata;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "MATERIAL")
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIAL_SEQ")
    @SequenceGenerator(name = "MATERIAL_SEQ", sequenceName = "MATERIAL_SEQ", allocationSize = 1)
    protected Long id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "DESCRIPTION")
    protected String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<ClothingApparel> clothingApparelList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Accessory> accessoryList = new ArrayList<>();
}
