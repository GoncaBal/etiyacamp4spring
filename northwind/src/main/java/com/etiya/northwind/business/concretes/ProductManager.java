package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.products.CreateProductRequest;
import com.etiya.northwind.business.requests.products.DeleteProductRequest;
import com.etiya.northwind.business.requests.products.UpdateProductRequest;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.business.responses.products.ReadProductResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.ProductRepository;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;
    @Autowired
    public ProductManager(ProductRepository productRepository,ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public void add(CreateProductRequest createProductRequest) {
        Product product=this.modelMapperService.forRequest().map(createProductRequest,Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
    Product productToUpdate=this.modelMapperService.forRequest().map(updateProductRequest,Product.class);
    this.productRepository.save(productToUpdate);
    }

    @Override
    public void delete(DeleteProductRequest deleteProductRequest) {
        this.productRepository.deleteById(deleteProductRequest.getProductId());
    }

    @Override
    public List<ProductListResponse> getAll() {
        List<Product> result= this.productRepository.findAll();
        List<ProductListResponse> response=result.stream().map(product -> this.modelMapperService.forResponse()
                .map(product,ProductListResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public ReadProductResponse getById(int id) {
        Product product=this.productRepository.findById(id);
        return this.modelMapperService.forResponse().map(product,ReadProductResponse.class);
    }
    @Override
    public Product findById(int id) {
        return this.productRepository.findById(id);

    }
    @Override
    public Map<String, Object> getAllPages(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        return null;
    }
}
