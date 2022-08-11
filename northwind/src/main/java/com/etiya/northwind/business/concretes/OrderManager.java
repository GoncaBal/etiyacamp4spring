package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.requests.orders.CreateOrderRequest;
import com.etiya.northwind.business.requests.orders.DeleteOrderRequest;
import com.etiya.northwind.business.requests.orders.UpdateOrderRequest;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.business.responses.orders.ReadOrderResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.core.utilities.sortData.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateOrderRequest createOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        this.orderRepository.save(order);
        return new SuccessResult("ORDER.ADDED");

    }

    @Override
    public Result update(UpdateOrderRequest updateOrderRequest) {
        Order orderToUpdate = this.modelMapperService.forRequest().map(updateOrderRequest, Order.class);
        this.orderRepository.save(orderToUpdate);
        return new SuccessResult("ORDER.UPDATED");
    }

    @Override
    public Result delete(DeleteOrderRequest deleteOrderRequest) {
        this.orderRepository.deleteById(deleteOrderRequest.getOrderId());
        return new SuccessResult("ORDER.DELETED");

    }

    @Override
    public DataResult<ReadOrderResponse> getById(int id) {

        Order order = this.orderRepository.findById(id).get();

        ReadOrderResponse readOrderResponse = this.modelMapperService.forResponse().map(order, ReadOrderResponse.class);
        return new SuccessDataResult<>(readOrderResponse);

    }

    @Override
    public DataResult<List<OrderListResponse>> getAll() {
        List<Order> result = this.orderRepository.findAll();
        List<OrderListResponse> response = result.stream().map(order -> this.modelMapperService.forResponse()
                .map(order, OrderListResponse.class)).collect(Collectors.toList());
        for (int i = 0; i < response.size(); i++) {
            response.get(i).setFullName(result.get(i).getEmployee().getFirstName() + " " + result.get(i).getEmployee().getLastName());
        }
        return new SuccessDataResult<>(response);
    }

    public DataResult<Map<String, Object>> getAllPages(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return new SuccessDataResult<>(pageableMap(pageable));
    }

    @Override
    public DataResult<Map<String, Object>> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, SortingEntities.sortType(entity, type));
        return new SuccessDataResult<>(pageableMap(pageable));
    }

    private Map<String, Object> pageableMap(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        Page<Order> orders = orderRepository.findAll(pageable);
        response.put("Total Elements", orders.getTotalElements());
        response.put("Total Pages", orders.getTotalPages());
        response.put("Current Page", orders.getNumber() + 1);
        response.put("Orders", orders.getContent().stream()
                .map(order -> this.modelMapperService.forResponse()
                        .map(order, OrderListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }
}
