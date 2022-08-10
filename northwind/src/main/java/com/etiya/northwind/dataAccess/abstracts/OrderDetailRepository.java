package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailId;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {


}
