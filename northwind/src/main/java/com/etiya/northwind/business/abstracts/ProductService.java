package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.products.CreateProductRequest;
import com.etiya.northwind.business.requests.products.DeleteProductRequest;
import com.etiya.northwind.business.requests.products.UpdateProductRequest;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.business.responses.products.ReadProductResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Result add(CreateProductRequest createProductRequest);

    Result update(UpdateProductRequest updateProductRequest);

    Result delete(DeleteProductRequest deleteProductRequest);

    DataResult<List<ProductListResponse>> getAll();

    DataResult<ReadProductResponse> getById(int id);

    DataResult<Map<String, Object>> getAllPages(int pageNumber, int pageSize);

    DataResult<Map<String, Object>> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type);
}
