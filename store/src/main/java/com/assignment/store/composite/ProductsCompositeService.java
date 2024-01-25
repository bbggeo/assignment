package com.assignment.store.composite;

import com.assignment.store.dao.Accessory;
import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.dao.Product;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.dto.product.DiscountDTO;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.service.MaterialService;
import com.assignment.store.service.ProductsService;
import com.assignment.store.service.SupplierService;
import com.assignment.store.util.enums.ProductProperty;
import com.assignment.store.util.enums.ProductType;
import com.assignment.store.util.exception.FieldValidationException;
import com.assignment.store.util.exception.InvalidDiscountException;
import com.assignment.store.util.mapper.ProductMapper;
import com.assignment.store.util.validator.ProductValidator;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Transactional
@Component
public class ProductsCompositeService {

    Logger logger = LoggerFactory.getLogger(ProductsCompositeService.class);
    @Autowired
    private ProductsService productsService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ProductValidator productValidator;

    public ProductDTO saveProduct(ProductDTO productDTO) {
        validate(productDTO);
        Supplier supplier = supplierService.findByName(productDTO.getSupplier());
        Product toSave = ProductMapper.mapToProduct(productDTO);
        toSave.setSupplierId(supplier.getId());
        toSave.setSupplier(supplier);
        return ProductMapper.mapToProductDto(productsService.saveProduct(toSave));
    }

    public Page<ProductDTO> getProducts(Integer pageNo, Integer pageSize, ProductType type) throws Exception {
        Page<?> products;
        Page<ProductDTO> entities = null;
        ModelMapper mapper = new ModelMapper();

        if (type.getCorrespondingClass().equals(ClothingApparel.class)) {
            products = productsService.searchClothing(type, pageNo, pageSize);
            entities = products.map(prod -> ProductMapper.mapProductDTO(mapper, (ClothingApparel) prod));
        } else if (type.getCorrespondingClass().equals(Accessory.class)) {
            products = productsService.searchAccessory(type, pageNo, pageSize);
            entities = products.map(prod -> ProductMapper.mapToProductDto((Product) prod));

        } else {
            throw new Exception();
        }
        return entities;
    }

    public void applyDiscount(DiscountDTO discountDTO) throws InvalidDiscountException {
        ProductProperty.validateField(ProductProperty.DISCOUNT, discountDTO.getDiscountValue());
        productsService.applyDiscount(discountDTO);
    }

    private void validate(ProductDTO productDTO) {
        BindingResult bindingResult = new BeanPropertyBindingResult(productDTO, ProductDTO.class.getName());
        productValidator.validate(productDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.error("Validation failed for " + productDTO.getClass().getName());
            String errorMessage = "The following validation rules failed: ";
            for (ObjectError err : bindingResult.getAllErrors()) {
                errorMessage = errorMessage.concat(err.getCode());
            }
            logger.error(errorMessage);
            throw new FieldValidationException(errorMessage);
        }
    }
}
