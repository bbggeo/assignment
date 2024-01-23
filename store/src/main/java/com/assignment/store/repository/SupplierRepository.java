package com.assignment.store.repository;

import com.assignment.store.dao.thirdparty.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Supplier findByName(String name);
}
