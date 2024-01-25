package com.assignment.store.repository;

import com.assignment.store.dao.Accessory;
import com.assignment.store.util.enums.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
    Page<Accessory> findByType(ProductType type, Pageable pageable);

    @Modifying
    @Query("update Accessory a set a.discount = :discount where a.type < :type")
    void applyDiscountForProducts(@Param("discount") BigDecimal discount, @Param("type") ProductType type);
}
