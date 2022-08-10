package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.customers.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customers.DeleteCustomerRequest;
import com.etiya.northwind.business.requests.customers.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.business.responses.customers.ReadCustomerResponse;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    void add(CreateCustomerRequest createCustomerRequest);
    void update(UpdateCustomerRequest updateCustomerRequest);
    void delete(DeleteCustomerRequest deleteCustomerRequest);
    ReadCustomerResponse getById(String id);
    List<CustomerListResponse> getAll();
    Map<String,Object> getAllPages(int pageNumber,int pageSize);
    Map<String,Object> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
