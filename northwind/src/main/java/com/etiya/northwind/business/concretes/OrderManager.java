package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.responses.OrderListResponse;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;
    @Autowired
    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderListResponse> getAll() {
        List<Order> result=this.orderRepository.findAll();
        List<OrderListResponse> response=new ArrayList<>();
        for (Order order:result){
            OrderListResponse orderListResponse=new OrderListResponse();
            orderListResponse.setOrderId(order.getOrderId());
            orderListResponse.setOrderDate(order.getOrderDate());
            orderListResponse.setCustomerCompanyName(order.getCustomer().getCompanyName());
            orderListResponse.setCustomerContactName(order.getCustomer().getContactName());
            orderListResponse.setEmployeeFirstName(order.getEmployee().getFirstName());
            orderListResponse.setEmployeeLastName(order.getEmployee().getLastName());

            response.add(orderListResponse);
        }
        return response;
    }
}
