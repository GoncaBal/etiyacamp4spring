package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.orderDetails.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.DeleteOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailListResponse;
import com.etiya.northwind.business.responses.orderDetails.ReadOrderDetailResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.core.utilities.sortData.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.OrderDetailRepository;
import com.etiya.northwind.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDetailManager implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapperService modelMapperService;


    @Autowired //final dediğimizde autowired gerekmez
    public OrderDetailManager(OrderDetailRepository orderDetailRepository, ModelMapperService modelMapperService) {
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapperService = modelMapperService;

    }

    @Override
    public Result add(CreateOrderDetailRequest createOrderDetailRequest) {
        OrderDetail orderDetail = this.modelMapperService.forRequest().map(createOrderDetailRequest, OrderDetail.class);
        this.orderDetailRepository.save(orderDetail);
        return new SuccessResult("ORDER.DETAIL.ADDED");
    }

    @Override
    public Result update(UpdateOrderDetailRequest updateOrderDetailRequest) {
        OrderDetail orderDetailToUpdate = this.modelMapperService.forRequest().map(updateOrderDetailRequest, OrderDetail.class);
        this.orderDetailRepository.save(orderDetailToUpdate);
        return new SuccessResult("ORDER.DETAIL.UPDATED");

    }

    @Override
    public Result delete(DeleteOrderDetailRequest deleteOrderDetailRequest) {
        this.orderDetailRepository.deleteById(deleteOrderDetailRequest.getOrderDetailId());
        return new SuccessResult("ORDER.DETAIL.DELETED");

    }

    @Override
    public DataResult<ReadOrderDetailResponse> getById(int orderId, int productId) {
        OrderDetail orderDetail = this.orderDetailRepository.getByOrder_OrderIdAndProduct_ProductId(orderId, productId);
        ReadOrderDetailResponse readOrderDetailResponse = this.modelMapperService.forResponse()
                .map(orderDetail, ReadOrderDetailResponse.class);
        return new SuccessDataResult<>(readOrderDetailResponse);
    }

    @Override
    public DataResult<List<OrderDetailListResponse>> getAll() {
        List<OrderDetail> result = this.orderDetailRepository.findAll();
        List<OrderDetailListResponse> response = result.stream()
                .map(orderDetail -> this.modelMapperService.forResponse()
                        .map(orderDetail, OrderDetailListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(response);
    }


    public DataResult<Map<String, Object>> getAllPages(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return  new SuccessDataResult<>( pageableMap(pageable));
    }

    @Override
    public DataResult<Map<String, Object>> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, SortingEntities.sortType(entity, type));
        return new SuccessDataResult<>(pageableMap(pageable));
    }

    private Map<String, Object> pageableMap(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        Page<OrderDetail> orderDetails = orderDetailRepository.findAll(pageable);
        response.put("Total Elements", orderDetails.getTotalElements());
        response.put("Total Pages", orderDetails.getTotalPages());
        response.put("Current Page", orderDetails.getNumber() + 1);
        response.put("OrderDetails", orderDetails.getContent().stream()
                .map(orderDetail -> this.modelMapperService.forResponse()
                        .map(orderDetail, OrderDetailListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }

}
