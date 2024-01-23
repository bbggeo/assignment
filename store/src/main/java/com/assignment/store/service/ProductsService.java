package com.assignment.store.service;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.Product;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.repository.AccessoryRepository;
import com.assignment.store.repository.ClothingApparelRepository;
import com.assignment.store.util.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private ClothingApparelRepository clothingRepository;

    public Product saveProduct(Product product) {
        Product savedProduct = null;
        switch (product) {
            case ClothingApparel clothingApparel -> {
                savedProduct = clothingRepository.save(clothingApparel);
            }
            case Accessory accessory -> {
                savedProduct = accessoryRepository.save(accessory);
            }
            default -> throw new RuntimeException();
        }
        return savedProduct;
    }

    public List<ClothingApparel> searchClothing(ProductType type) {
        return clothingRepository.findByType(type);
    }
}
