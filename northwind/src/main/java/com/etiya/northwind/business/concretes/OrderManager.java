package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.requests.orders.CreateOrderRequest;
import com.etiya.northwind.business.requests.orders.DeleteOrderRequest;
import com.etiya.northwind.business.requests.orders.UpdateOrderRequest;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.business.responses.orders.ReadOrderResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void add(CreateOrderRequest createOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        this.orderRepository.save(order);
    }

    @Override
    public void update(UpdateOrderRequest updateOrderRequest) {
        Order orderToUpdate=this.modelMapperService.forRequest().map(updateOrderRequest, Order.class);
        this.orderRepository.save(orderToUpdate);
    }

    @Override
    public void delete(DeleteOrderRequest deleteOrderRequest) {
        this.orderRepository.deleteById(deleteOrderRequest.getOrderId());
    }

    @Override
    public ReadOrderResponse getById(int id) {
        Order order= this.orderRepository.findById(id);
        return this.modelMapperService.forResponse().map(order,ReadOrderResponse.class);
    }
    @Override
    public Order findById(int id) {
        Order order= this.orderRepository.findById(id);
        return order;
    }
    @Override
    public List<OrderListResponse> getAll() {
        List<Order> result = this.orderRepository.findAll();
        List<OrderListResponse> response = result.stream().map(order -> this.modelMapperService.forResponse()
                .map(order, OrderListResponse.class)).collect(Collectors.toList());
        for (int i = 0; i < response.size(); i++) {
            response.get(i).setFullName(result.get(i).getEmployee().getFirstName() + " " + result.get(i).getEmployee().getLastName());
        }
        return response;
    }

    @Override
    public Map<String, Object> getAllPages(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        return null;
    }
}
