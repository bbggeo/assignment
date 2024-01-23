package com.assignment.store.service;

import com.assignment.store.dao.thirdparty.Supplier;
import com.assignment.store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier findByName(String name) {
        return supplierRepository.findByName(name);
    }
}
