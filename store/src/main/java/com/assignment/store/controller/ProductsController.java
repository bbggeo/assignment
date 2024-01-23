package com.assignment.store.controller;

import com.assignment.store.composite.ProductsCompositeService;
import com.assignment.store.dto.product.ClothingApparelDTO;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.util.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public List<ProductDTO> getProducts(@RequestParam("type") ProductType type) throws Exception {
        return productsCompositeService.getProducts(1, 1, type);
    }
}
