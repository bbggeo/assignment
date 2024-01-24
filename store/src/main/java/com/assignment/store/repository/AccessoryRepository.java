package com.assignment.store.repository;

import com.assignment.store.dao.Accessory;
import com.assignment.store.util.enums.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
    Page<Accessory> findByType(ProductType type, Pageable pageable);
}
