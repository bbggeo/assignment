package com.assignment.store.repository;

import com.assignment.store.dao.ClothingApparel;
import com.assignment.store.util.enums.ProductType;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;import org.springframework.data.domain.Pageable;

@Repository
public interface ClothingApparelRepository extends JpaRepository<ClothingApparel, Long> {
    Page<ClothingApparel> findByType(ProductType type, Pageable pageable);

    List<ClothingApparel> findByName(String name);
}
