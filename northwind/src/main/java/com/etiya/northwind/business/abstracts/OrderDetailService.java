package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.orderDetails.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.DeleteOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailListResponse;
import com.etiya.northwind.business.responses.orderDetails.ReadOrderDetailResponse;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailId;
import com.etiya.northwind.entities.concretes.Product;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    void add(CreateOrderDetailRequest createOrderDetailRequest);
    void update(UpdateOrderDetailRequest updateOrderDetailRequest);
    void delete(DeleteOrderDetailRequest deleteOrderDetailRequest);
    ReadOrderDetailResponse getById (int orderId, int productId);
    List<OrderDetailListResponse> getAll();

    Map<String,Object> getAllPages(int pageNumber, int pageSize);
    Map<String,Object> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
