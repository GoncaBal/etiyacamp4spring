package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.requests.categories.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categories.DeleteCategoryRequest;
import com.etiya.northwind.business.requests.categories.UpdateCategoryRequest;
import com.etiya.northwind.business.requests.customers.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customers.DeleteCustomerRequest;
import com.etiya.northwind.business.requests.customers.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.business.responses.customers.ReadCustomerResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    private CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getall")
    DataResult<List<CustomerListResponse>> getAll() {
        return this.customerService.getAll();
    }
    @GetMapping("/getbyid")
    public DataResult<ReadCustomerResponse> getById(@RequestParam String id) {
        return this.customerService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
      return  this.customerService.add(createCustomerRequest);
    }

    @PutMapping ("/update")
    public Result update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
       return this.customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/delete")
    public Result delete( @RequestBody DeleteCustomerRequest deleteCustomerRequest) {
      return  this.customerService.delete(deleteCustomerRequest);
    }

    @GetMapping("/getallpages")
    public DataResult<Map<String, Object>> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return this.customerService.getAllPages(pageNumber, pageSize);
    }

    @GetMapping("/getallpagesortbyentity")
    public DataResult<Map<String,Object>> getAllPagesOrderByEntity(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String entity, @RequestParam Optional<String> type){
        return this.customerService.getAllPagesOrderByEntity(pageNumber,pageSize,entity,type.orElse(" "));
    }

}
