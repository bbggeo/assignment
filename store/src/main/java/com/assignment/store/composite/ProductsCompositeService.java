package com.assignment.store.composite;

import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.service.ProductsService;
import com.assignment.store.util.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductsCompositeService {
    @Autowired
    private ProductsService productsService;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        return ProductMapper.mapToProductDto(productsService.saveProduct(ProductMapper.mapToProduct(productDTO)));
    }
}
