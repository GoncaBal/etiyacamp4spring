package com.etiya.northwind.api.controllers;
import com.etiya.northwind.business.abstracts.OrderService;

import com.etiya.northwind.business.requests.orders.CreateOrderRequest;
import com.etiya.northwind.business.requests.orders.DeleteOrderRequest;
import com.etiya.northwind.business.requests.orders.UpdateOrderRequest;

import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.business.responses.orders.ReadOrderResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateOrderRequest createOrderRequest){
        return this.orderService.add(createOrderRequest);

    }

    @PutMapping("/update")
    public Result update(@Valid @RequestBody UpdateOrderRequest updateOrderRequest){
       return  this.orderService.update(updateOrderRequest);

    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteOrderRequest deleteOrderRequest){
       return this.orderService.delete(deleteOrderRequest);
    }


    @GetMapping("/getAll")
    public DataResult<List<OrderListResponse>> getAll() {

        return this.orderService.getAll();
    }
    @GetMapping("/getbyid")
    public DataResult<ReadOrderResponse> getById(@RequestParam int id){

        return this.orderService.getById(id);
    }

    @GetMapping("/getallpages")
    public DataResult<Map<String,Object>> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize){

        return this.orderService.getAllPages(pageNumber,pageSize);

    }

    @GetMapping("/getallpagesorderbyentity")
    public DataResult<Map<String,Object>> getAllPagesOrderByEntity(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String entity,@RequestParam Optional<String> type){

        return this.orderService.getAllPagesOrderByEntity(pageNumber,pageSize, entity,type.orElse(""));

    }

}
