package com.assignment.store.repository;

import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.util.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingApparelRepository extends JpaRepository<ClothingApparel, Long> {
    List<ClothingApparel> findByType(ProductType type);

    List<ClothingApparel> findByName(String name);
}
