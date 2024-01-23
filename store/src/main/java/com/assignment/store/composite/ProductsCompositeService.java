package com.assignment.store.composite;

import com.assignment.store.dao.Product;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.service.ProductsService;
import com.assignment.store.service.SupplierService;
import com.assignment.store.util.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductsCompositeService {
    @Autowired
    private ProductsService productsService;


    @Autowired
    private SupplierService supplierService;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Supplier supplier = supplierService.findByName(productDTO.getSupplier());
        Product toSave = ProductMapper.mapToProduct(productDTO);
        toSave.setSupplierId(supplier.getId());
        toSave.setSupplier(supplier);
        return ProductMapper.mapToProductDto(productsService.saveProduct(toSave));
    }
}
