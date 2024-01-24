package com.assignment.store.controller;

import com.assignment.store.composite.ProductsCompositeService;
import com.assignment.store.dto.product.ClothingApparelDTO;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.util.enums.ProductType;
import com.assignment.store.util.exception.FieldValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Page<ProductDTO> getProducts(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber, @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @RequestParam("type") ProductType type) throws Exception {
        return productsCompositeService.getProducts(pageNumber, pageSize, type);
    }
}
