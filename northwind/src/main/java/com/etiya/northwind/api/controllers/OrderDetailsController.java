package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.requests.orderDetails.CreateOrderDetailRequest;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailListResponse;
import com.etiya.northwind.business.responses.orderDetails.ReadOrderDetailResponse;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailsController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/getall")
    List<OrderDetailListResponse> getAll() {
        return this.orderDetailService.getAll();
    }

    @GetMapping("/getbyid")
   public ReadOrderDetailResponse getById(int orderId, int productId){
        return this.orderDetailService.getById(orderId,productId);
    }
    @PostMapping("/add")
    public void add(CreateOrderDetailRequest createOrderDetailRequest){
        this.orderDetailService.add(createOrderDetailRequest);
    }
}