package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.products.CreateProductRequest;
import com.etiya.northwind.business.requests.products.DeleteProductRequest;
import com.etiya.northwind.business.requests.products.UpdateProductRequest;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.business.responses.products.ReadProductResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.core.utilities.sortData.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.ProductRepository;
import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public ProductManager(ProductRepository productRepository, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateProductRequest createProductRequest) {

        checkIfCategoryLimitExceeds(createProductRequest.getCategoryId());
        checkIfProductExistByName(createProductRequest.getProductName());

        Product product = this.modelMapperService.forRequest().map(createProductRequest, Product.class);
        this.productRepository.save(product);
        return new SuccessResult("PRODUCT.ADDED");
    }

    @Override
    public Result update(UpdateProductRequest updateProductRequest) {

        checkIfProductIdExist(updateProductRequest.getProductId());
        checkIfCategoryLimitExceeds(updateProductRequest.getCategoryId());
        checkIfProductExistByName(updateProductRequest.getProductName());

        Product productToUpdate = this.modelMapperService.forRequest().map(updateProductRequest, Product.class);
        this.productRepository.save(productToUpdate);
        return new SuccessResult("PRODUCT.UPDATED");
    }

    @Override
    public Result delete(DeleteProductRequest deleteProductRequest) {

        checkIfProductIdExist(deleteProductRequest.getProductId());
        this.productRepository.deleteById(deleteProductRequest.getProductId());
        return new SuccessResult("PRODUCT.DELETED");
    }

    @Override
    public DataResult<List<ProductListResponse>> getAll() {
        List<Product> result = this.productRepository.findAll();
        List<ProductListResponse> response = result.stream().map(product -> this.modelMapperService.forResponse()
                .map(product, ProductListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult(response);
    }

    @Override
    public DataResult<ReadProductResponse> getById(int id) {

        checkIfProductIdExist(id);

        Product product = this.productRepository.findById(id).get();
        ReadProductResponse readProductResponse= this.modelMapperService.forResponse().map(product, ReadProductResponse.class);
        return new SuccessDataResult<>(readProductResponse);
    }


    public DataResult<Map<String, Object>> getAllPages(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return new SuccessDataResult<>( pageableMap(pageable));
    }

    @Override
    public DataResult<Map<String, Object>> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, SortingEntities.sortType(entity, type));
        return new SuccessDataResult<>( pageableMap(pageable));
    }

    @Override
    public Product getProductById(int productId) {
        checkIfProductIdExist(productId);
        return this.productRepository.findById(productId).get();
    }

    private Map<String, Object> pageableMap(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        Page<Product> products = productRepository.findAll(pageable);
        response.put("Total Elements", products.getTotalElements());
        response.put("Total Pages", products.getTotalPages());
        response.put("Current Page", products.getNumber() + 1);
        response.put("Products", products.getContent().stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, ProductListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }

    private void checkIfProductIdExist(int productId){

        Product currentProduct=this.productRepository.findById(productId).get();

        if (currentProduct==null){
            throw new BusinessException("INVALID.PRODUCT.ID");
        }
    }

    private void checkIfCategoryLimitExceeds(int categoryId) {

        List<Product> products = productRepository.findByCategoryCategoryId(categoryId);
        if (products.size() >= 15) {
            throw new BusinessException("Category limit exceed");
        }
    }
    private void checkIfProductExistByName(String productName){

//        Product currentProduct=this.productRepository.findByProductName(productName);
//        if (currentProduct!=null){
//            throw new BusinessException("PRODUCT.ALREADY.EXIST");
//        }

        for(Product product:this.productRepository.findAll()){
            if (product.getProductName().toUpperCase().equals(productName.toUpperCase())){
                throw new BusinessException("PRODUCT.NAME.ALREADY.EXIST");
            }
       }
    }
}
