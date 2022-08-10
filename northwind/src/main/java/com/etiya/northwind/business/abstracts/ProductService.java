package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.products.CreateProductRequest;
import com.etiya.northwind.business.requests.products.DeleteProductRequest;
import com.etiya.northwind.business.requests.products.UpdateProductRequest;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.business.responses.products.ReadProductResponse;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void add(CreateProductRequest createProductRequest);
    void update(UpdateProductRequest updateProductRequest);
    void delete(DeleteProductRequest deleteProductRequest);
    List<ProductListResponse> getAll();
    ReadProductResponse getById(int id);

    Product findById(int id);

    Map<String,Object> getAllPages(int pageNumber, int pageSize);
    Map<String,Object> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
