package com.assignment.store.service;

import com.assignment.store.dao.staticdata.Material;
import com.assignment.store.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }
}
