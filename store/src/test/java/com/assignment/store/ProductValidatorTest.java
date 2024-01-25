package com.assignment.store;

import com.assignment.store.dao.staticdata.Material;
import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.repository.MaterialRepository;
import com.assignment.store.repository.SupplierRepository;
import com.assignment.store.util.exception.MaterialNotFoundException;
import com.assignment.store.util.exception.SupplierNotFoundException;
import com.assignment.store.util.validator.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductValidatorTest {

    private ProductDTO productDTO;

    private BindingResult bindingResult = new BeanPropertyBindingResult(productDTO, ProductDTO.class.getName());

    private Supplier supplier = new Supplier();

    @InjectMocks
    ProductValidator productValidator;

    @Mock
    SupplierRepository supplierRepository;

    @Mock
    MaterialRepository materialRepository;

    @BeforeEach
    public void initialize() {
        this.productDTO = new ProductDTO();
        productDTO.setName("Test product name");
        productDTO.setMaterial("Gold");
        productDTO.setSupplier("Bershka");
        productDTO.setType("Ring");
        productDTO.setPrice(BigDecimal.TEN);
        productDTO.setStock(10);
        productDTO.setMaterialId(1L);
        supplier.setId(1L);
    }

    @Test
    public void testValidProduct() {
        when(materialRepository.findById(any())).thenReturn(Optional.of(new Material()));
        when(supplierRepository.findByName(any())).thenReturn(this.supplier);
        productValidator.validate(productDTO, bindingResult);
        verify(materialRepository, times(1)).findById(this.productDTO.getMaterialId());
        verify(supplierRepository, times(1)).findByName(this.productDTO.getSupplier());
        assert !bindingResult.hasErrors();
    }

    @Test()
    public void testMaterialNotFoundException() {
        when(materialRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        assertThrows(MaterialNotFoundException.class,
                ()-> productValidator.validate(productDTO, bindingResult));
    }

    @Test()
    public void testSupplierNotFoundException() {
        when(materialRepository.findById(any())).thenReturn(Optional.of(new Material()));
        when(supplierRepository.findByName(any())).thenReturn(null);
        assertThrows(SupplierNotFoundException.class,
                ()-> productValidator.validate(productDTO, bindingResult));
    }

    @Test()
    public void testInvalidFieldsErrors() {
        ProductDTO invalidProduct = productDTO;
        invalidProduct.setName("s");
        invalidProduct.setPrice(null);
        invalidProduct.setDiscount(BigDecimal.valueOf(200L));
        when(materialRepository.findById(any())).thenReturn(Optional.of(new Material()));
        when(supplierRepository.findByName(any())).thenReturn(supplier);

        productValidator.validate(invalidProduct, bindingResult);

        verify(materialRepository, times(1)).findById(this.productDTO.getMaterialId());
        verify(supplierRepository, times(1)).findByName(this.productDTO.getSupplier());
        assert bindingResult.hasErrors();
        assert bindingResult.getErrorCount() == 3;
        assert bindingResult.getAllErrors().get(0).getDefaultMessage().equals("Minimum length for name is 5.");
        assert bindingResult.getAllErrors().get(1).getDefaultMessage().equals("Required field price can not be empty.");
        assert bindingResult.getAllErrors().get(2).getDefaultMessage().equals("Maximum allowed value for discount is 99.");
    }
}
