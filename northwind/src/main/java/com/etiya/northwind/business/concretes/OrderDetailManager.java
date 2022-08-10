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
import com.etiya.northwind.dataAccess.abstracts.OrderDetailRepository;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetail;
import com.etiya.northwind.entities.concretes.OrderDetailId;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderDetailManager implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapperService modelMapperService;
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired //final dediğimizde autowired gerekmez
    public OrderDetailManager(OrderDetailRepository orderDetailRepository, ModelMapperService modelMapperService, OrderService orderService, ProductService productService) {
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapperService = modelMapperService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void add(CreateOrderDetailRequest createOrderDetailRequest) {
        OrderDetail orderDetail = this.modelMapperService.forRequest().map(createOrderDetailRequest, OrderDetail.class);
        this.orderDetailRepository.save(orderDetail);
    }

    @Override
    public void update(UpdateOrderDetailRequest updateOrderDetailRequest) {
//        OrderDetail orderDetailToUpdate=
    }

    @Override
    public void delete(DeleteOrderDetailRequest deleteOrderDetailRequest) {

    }

    @Override
    public ReadOrderDetailResponse getById(int orderId, int productId) {
        OrderDetail orderDetail = this.orderDetailRepository.findById(createOrderDetailId(orderId, productId)).get();
        return orderDetailMapping(orderId, productId, orderDetail);
    }


    private ReadOrderDetailResponse orderDetailMapping(int orderId, int productId, OrderDetail orderDetail) {
        ReadOrderDetailResponse readOrderDetailResponse = this.modelMapperService.forResponse().map(orderDetail, ReadOrderDetailResponse.class);
        Order order = this.orderService.findById(orderId);
        Product product = this.productService.findById(productId);
        readOrderDetailResponse.setContactName(order.getCustomer().getContactName());
        readOrderDetailResponse.setOrderDate(order.getOrderDate());
        readOrderDetailResponse.setProductName(product.getProductName());
        return readOrderDetailResponse;
    }
    private OrderDetailListResponse orderDetailMappingList(int orderId, int productId, OrderDetail orderDetail) {
        OrderDetailListResponse orderDetailListResponse = this.modelMapperService.forResponse()
                .map(orderDetail, OrderDetailListResponse.class);
        Order order = this.orderService.findById(orderId);
        Product product = this.productService.findById(productId);
        orderDetailListResponse.setContactName(order.getCustomer().getContactName());
        orderDetailListResponse.setOrderDate(order.getOrderDate());
        orderDetailListResponse.setProductName(product.getProductName());
        return orderDetailListResponse;
    }

    @Override
    public List<OrderDetailListResponse> getAll() {
        List<OrderDetail> result = this.orderDetailRepository.findAll();
        List<OrderDetailListResponse> response =new ArrayList<>();

        for (int i=0;i< result.size();i++){

            response.add( orderDetailMappingList(result.get(i).getOrderId(),result.get(i).getProductId(),result.get(i)));
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


    private OrderDetailId createOrderDetailId(int orderId, int productId) {
        OrderDetailId orderDetailId = new OrderDetailId();
        orderDetailId.setOrderId(orderId);
        orderDetailId.setProductId(productId);
        return orderDetailId;
    }
}
