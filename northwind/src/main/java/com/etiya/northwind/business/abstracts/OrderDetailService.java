package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.OrderDetailListResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailListResponse> getAll();
}
