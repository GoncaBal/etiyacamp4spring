package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.products.CreateProductRequest;
import com.etiya.northwind.business.requests.products.DeleteProductRequest;
import com.etiya.northwind.business.requests.products.UpdateProductRequest;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.business.responses.products.ReadProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public List<ProductListResponse> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/getbyid")
    public ReadProductResponse getById(@RequestParam int id) {
        return this.productService.getById(id);
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody CreateProductRequest createProductRequest) {
        this.productService.add(createProductRequest);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody DeleteProductRequest deleteProductRequest) {
        this.productService.delete(deleteProductRequest);
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateProductRequest updateProductRequest) {
        this.productService.update(updateProductRequest);
    }
}
