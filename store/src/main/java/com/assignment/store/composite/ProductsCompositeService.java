package com.assignment.store.composite;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.Product;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.service.ProductsService;
import com.assignment.store.service.SupplierService;
import com.assignment.store.util.enums.ProductType;
import com.assignment.store.util.mapper.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProductDTO> getProducts(Integer pageNo, Integer pageSize, ProductType type) throws Exception {
        List<? extends Product> products = new ArrayList<>();
        List<ProductDTO> retrievedItems = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        if (type.getCorrespondingClass().equals(ClothingApparel.class)) {
            products = productsService.searchClothing(type);
            products.forEach(product -> retrievedItems.add(ProductMapper.mapClothingApparelToProductDTO(mapper, (ClothingApparel) product)));
        } else if (type.getCorrespondingClass().equals(Accessory.class)) {

        } else {
            throw new Exception();
        }
        return retrievedItems;
    }
}
