package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.orderDetails.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orders.CreateOrderRequest;
import com.etiya.northwind.business.requests.orders.DeleteOrderRequest;
import com.etiya.northwind.business.requests.orders.UpdateOrderRequest;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.business.responses.orders.ReadOrderResponse;
import com.etiya.northwind.entities.concretes.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void add(CreateOrderRequest createOrderRequest);
    void update(UpdateOrderRequest updateOrderRequest);
    void delete(DeleteOrderRequest deleteOrderRequest);
    ReadOrderResponse getById(int id);

    Order findById(int id);

    List<OrderListResponse> getAll();
    Map<String,Object> getAllPages(int pageNumber, int pageSize);
    Map<String,Object> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
