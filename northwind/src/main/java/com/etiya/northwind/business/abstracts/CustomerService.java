package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.CustomerListResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerListResponse> getAll();
}
