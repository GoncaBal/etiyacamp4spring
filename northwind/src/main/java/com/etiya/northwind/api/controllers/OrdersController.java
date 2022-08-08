package com.etiya.northwind.api.controllers;
import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.responses.OrderListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getall")
    List<OrderListResponse> getAll() {
        return this.orderService.getAll();
    }
}
