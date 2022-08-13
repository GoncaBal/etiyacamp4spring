package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.orderDetails.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.DeleteOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailListResponse;
import com.etiya.northwind.business.responses.orderDetails.ReadOrderDetailResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailId;
import com.etiya.northwind.entities.concretes.Product;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {
    Result add(CreateOrderDetailRequest createOrderDetailRequest);
    Result update(UpdateOrderDetailRequest updateOrderDetailRequest);
    Result delete(DeleteOrderDetailRequest deleteOrderDetailRequest);
    DataResult<ReadOrderDetailResponse> getById (int orderId, int productId);
    DataResult<List<OrderDetailListResponse>> getAll();

    DataResult<Map<String,Object>> getAllPages(int pageNumber, int pageSize);
    DataResult<Map<String,Object>> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);

    OrderDetail getOrderDetailById(int orderId,int productId);
}
