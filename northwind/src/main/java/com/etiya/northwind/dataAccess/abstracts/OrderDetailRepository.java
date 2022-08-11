package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

    OrderDetail getByOrder_OrderIdAndProduct_ProductId(int orderId,int productId);
}
