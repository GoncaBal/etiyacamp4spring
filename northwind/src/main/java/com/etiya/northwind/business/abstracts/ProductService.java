package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.ProductListResponse;

import java.util.List;

public interface ProductService {
    List<ProductListResponse> getAll();
}
