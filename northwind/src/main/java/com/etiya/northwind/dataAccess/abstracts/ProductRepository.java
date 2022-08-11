package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductName(String productName);
    List<Product> findByCategoryCategoryId(int categoryId);


}
