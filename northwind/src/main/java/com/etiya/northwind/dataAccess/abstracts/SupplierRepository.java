package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
