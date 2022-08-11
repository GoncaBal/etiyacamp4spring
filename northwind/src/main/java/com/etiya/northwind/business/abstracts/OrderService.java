package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.orderDetails.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orders.CreateOrderRequest;
import com.etiya.northwind.business.requests.orders.DeleteOrderRequest;
import com.etiya.northwind.business.requests.orders.UpdateOrderRequest;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.business.responses.orders.ReadOrderResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.entities.concretes.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Result add(CreateOrderRequest createOrderRequest);
    Result update(UpdateOrderRequest updateOrderRequest);
    Result delete(DeleteOrderRequest deleteOrderRequest);
    DataResult<ReadOrderResponse> getById(int id);

    DataResult<List<OrderListResponse>> getAll();
    DataResult<Map<String,Object>> getAllPages(int pageNumber, int pageSize);
    DataResult<Map<String,Object>> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
