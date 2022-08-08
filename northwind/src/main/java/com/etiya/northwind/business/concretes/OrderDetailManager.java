package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderDetailService;
import com.etiya.northwind.business.responses.OrderDetailListResponse;
import com.etiya.northwind.dataAccess.abstracts.OrderDetailRepository;
import com.etiya.northwind.entities.concretes.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderDetailManager implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;
    @Autowired
    public OrderDetailManager(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetailListResponse> getAll() {
        List<OrderDetail> result = this.orderDetailRepository.findAll();
        List<OrderDetailListResponse> response = new ArrayList<>();

        for (OrderDetail orderDetail : result) {
            OrderDetailListResponse orderDetailListResponse = new OrderDetailListResponse();
            orderDetailListResponse.setOrderId(orderDetail.getOrder().getOrderId());
            orderDetailListResponse.setOrderDate(orderDetail.getOrder().getOrderDate());
            orderDetailListResponse.setDiscount(orderDetail.getDiscount());
            orderDetailListResponse.setQuantity(orderDetail.getQuantity());
            orderDetailListResponse.setUnitPrice(orderDetail.getUnitPrice());
            orderDetailListResponse.setProductName(orderDetail.getProduct().getProductName());
            orderDetailListResponse.setProductId(orderDetail.getProduct().getProductId());
            response.add(orderDetailListResponse);

        }
        return response;
    }
}
