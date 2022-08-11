package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.requests.orderDetails.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.DeleteOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetails.UpdateOrderDetailRequest;
import com.etiya.northwind.business.requests.orders.UpdateOrderRequest;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailListResponse;
import com.etiya.northwind.business.responses.orderDetails.ReadOrderDetailResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailId;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailsController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateOrderDetailRequest createOrderDetailRequest){
        return this.orderDetailService.add(createOrderDetailRequest);
    } @PutMapping("/update")
    public Result update(@Valid @RequestBody UpdateOrderDetailRequest updateOrderDetailRequest){
        return this.orderDetailService.update(updateOrderDetailRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteOrderDetailRequest deleteOrderDetailRequest){
        return this.orderDetailService.delete(deleteOrderDetailRequest);
    }
    @GetMapping("/getall")
    DataResult<List<OrderDetailListResponse>> getAll() {
        return this.orderDetailService.getAll();
    }

    @GetMapping("/getbyid")
   public DataResult<ReadOrderDetailResponse> getById(int orderId, int productId){
        return this.orderDetailService.getById(orderId,productId);
    }
    @GetMapping("/getallpages")
    public DataResult<Map<String,Object>> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize){

        return this.orderDetailService.getAllPages(pageNumber,pageSize);

    }

    @GetMapping("/getallpagesorderbyentity")
    public DataResult<Map<String,Object>> getAllPagesOrderByEntity(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String entity,@RequestParam Optional<String> type){

        return this.orderDetailService.getAllPagesOrderByEntity(pageNumber,pageSize, entity,type.orElse(""));

    }

}