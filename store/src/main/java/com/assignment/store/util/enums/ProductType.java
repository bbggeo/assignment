package com.assignment.store.util.enums;

import java.util.stream.Stream;

public enum ProductType {
    TSHIRT("T-shirt"),
    JEANS("Jeans"),
    SKIRT("Skirt"),
    RING("Ring"),
    EARRINGS("Earring");
    private final String name;

    private ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProductType findByName(String name) {
        return Stream.of(ProductType.values()).filter(productType -> productType.getName().equals(name)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}

