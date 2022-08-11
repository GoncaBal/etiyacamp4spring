package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.products.CreateProductRequest;
import com.etiya.northwind.business.requests.products.DeleteProductRequest;
import com.etiya.northwind.business.requests.products.UpdateProductRequest;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.business.responses.products.ReadProductResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public DataResult<List<ProductListResponse>> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<ReadProductResponse> getById(@RequestParam int id) {
        return this.productService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateProductRequest createProductRequest) throws Exception {
      return  this.productService.add(createProductRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteProductRequest deleteProductRequest) {
        return this.productService.delete(deleteProductRequest);
    }

    @PutMapping("/update")
    public Result update(@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return this.productService.update(updateProductRequest);
    }
    @GetMapping("/getallpages")
    public DataResult<Map<String,Object>> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize){

        return this.productService.getAllPages(pageNumber,pageSize);

    }

    @GetMapping("/getallpagesorderbyentity")
    public DataResult<Map<String,Object>> getAllPagesOrderByEntity(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String entity,@RequestParam Optional<String> type){

        return this.productService.getAllPagesOrderByEntity(pageNumber,pageSize, entity,type.orElse(""));

    }

}
