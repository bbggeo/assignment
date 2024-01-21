package com.assignment.store.service;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.Product;
import com.assignment.store.dto.product.AccessoryDTO;
import com.assignment.store.dto.product.ClothingApparelDTO;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.repository.AccessoryRepository;
import com.assignment.store.repository.ClothingApparelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductsService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private ClothingApparelRepository clothingRepository;

    public Product saveProduct(Product product) {
        Product savedProduct = null;
        switch (product) {
            case ClothingApparel clothingApparelDTO -> {
                savedProduct = clothingRepository.save((ClothingApparel) product);
            }
            case Accessory accessory -> {
                savedProduct = accessoryRepository.save(accessory);
            }
            default -> throw new RuntimeException();
        }
        return savedProduct;
    }
}
