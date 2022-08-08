package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailsController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/getAll")
    List<OrderDetailListResponse> getAll() {
        return this.orderDetailService.getAll();
    }
}