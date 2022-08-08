package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
