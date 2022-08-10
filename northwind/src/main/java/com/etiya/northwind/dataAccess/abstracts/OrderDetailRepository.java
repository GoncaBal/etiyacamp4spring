package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailId;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

//   @Query("SELECT o.orderId,p.productId FROM OrderDetail od JOIN Product p " +
//           "on od.product.productId =p.productId JOIN Order o ON od.order.orderId = o.orderId")
//   OrderDetail findByOrderDetailId(int orderId,int productId);
//   @Query(value = "SELECT u FROM User u ORDER BY id")
//   Page<User> findAllUsersWithPagination(Pageable pageable);

//   @Query(
//           value = "SELECT * FROM Users ORDER BY id",
//           countQuery = "SELECT count(*) FROM Users",
//           nativeQuery = true)
//   Page<User> findAllUsersWithPagination(Pageable pageable);
}
