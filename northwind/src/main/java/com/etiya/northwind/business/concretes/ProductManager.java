package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.responses.ProductListResponse;
import com.etiya.northwind.dataAccess.abstracts.ProductRepository;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductListResponse> getAll() {
        List<Product> result= this.productRepository.findAll();
        List<ProductListResponse> response=new ArrayList<ProductListResponse>();

        for (Product product:result){
            ProductListResponse productListResponse =new ProductListResponse();
            productListResponse.setCategoryId(product.getCategory().getCategoryId());
            productListResponse.setProductId(product.getProductId());
            productListResponse.setProductName(product.getProductName());
            productListResponse.setCategoryName(product.getCategory().getCategoryName());
            productListResponse.setUnitPrice(product.getUnitPrice());
            productListResponse.setUnitsInStock(product.getUnitsInStock());

            response.add(productListResponse);
        }
        return response;
    }
}

//ToDo: categories customers employees order_ details orders products suppliers