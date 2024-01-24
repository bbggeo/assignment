package com.assignment.store;

import com.assignment.store.repository.MaterialRepository;
import com.assignment.store.repository.SupplierRepository;
import com.assignment.store.util.validator.ProductValidator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductValidatorTest {

    @InjectMocks
    ProductValidator productValidator;

    @Mock
    SupplierRepository supplierRepository;

    @Mock
    MaterialRepository materialRepository;

}
