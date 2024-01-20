package com.assignment.store.repository;

import com.assignment.store.dao.ClothingApparel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingApparelRepository extends JpaRepository<ClothingApparel, Long> {
}
