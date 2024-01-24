package com.assignment.store.util.enums;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;

import java.util.Optional;
import java.util.stream.Stream;

public enum ProductType {
    TSHIRT("T-shirt", ClothingApparel.class),
    JEANS("Jeans", ClothingApparel.class),
    SKIRT("Skirt", ClothingApparel.class),
    RING("Ring", Accessory.class),
    EARRINGS("Earrings", Accessory.class);
    private final String name;
    private final Class correspondingClass;

    private ProductType(String name, Class correspondingClass) {
        this.name = name;
        this.correspondingClass = correspondingClass;
    }

    public String getName() {
        return name;
    }

    public static Optional<ProductType> findByName(String name) {
        return Stream.of(ProductType.values()).filter(productType -> productType.getName().equals(name)).findFirst();
    }
    public Class getCorrespondingClass() {
        return correspondingClass;
    }
}

