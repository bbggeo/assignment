package com.assignment.store.controller;

import com.assignment.store.composite.ProductsCompositeService;
import com.assignment.store.dto.product.ClothingApparelDTO;
import com.assignment.store.dto.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsCompositeService productsCompositeService;

    @PostMapping(value = "/clothing")
    public ProductDTO saveClothingApparel(@RequestBody ClothingApparelDTO clothingApparelDTO) {
        return productsCompositeService.saveProduct(clothingApparelDTO);
    }

    @PostMapping()
    @Secured("ADMIN")
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        return productsCompositeService.saveProduct(productDTO);
    }

    @GetMapping
    public String test() {
        return "Eok";
    }
}
