package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.requests.customers.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customers.DeleteCustomerRequest;
import com.etiya.northwind.business.requests.customers.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.business.responses.customers.ReadCustomerResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.sortData.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CustomerManager(CustomerRepository customerRepository,ModelMapperService modelMapperService) {
        this.customerRepository = customerRepository;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public void add(CreateCustomerRequest createCustomerRequest) {
        Customer customer= this.modelMapperService.forRequest().map(createCustomerRequest,Customer.class);
        this.customerRepository.save(customer);
    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customerToUpdate=this.modelMapperService.forRequest().map(updateCustomerRequest,Customer.class);
        this.customerRepository.save(customerToUpdate);
    }

    @Override
    public void delete(DeleteCustomerRequest deleteCustomerRequest) {
        this.customerRepository.deleteById(deleteCustomerRequest.getCustomerId());
    }

    @Override
    public ReadCustomerResponse getById(String id) {
        Customer customer = this.customerRepository.findById(id).get();
       return this.modelMapperService.forResponse().map(customer,ReadCustomerResponse.class);

    }

    @Override
    public List<CustomerListResponse> getAll() {
        List<Customer> result=this.customerRepository.findAll();
        List<CustomerListResponse> response=result.stream().map(customer -> this.modelMapperService.forResponse()
                .map(customer,CustomerListResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public Map<String, Object> getAllPages(int pageNumber, int pageSize) {
        Pageable pageable=PageRequest.of(pageNumber-1,pageSize);
        return pageableMap(pageable);
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        Pageable pageable= PageRequest.of(pageNumber-1,pageSize, SortingEntities.sortType(entity,type));
        return  pageableMap(pageable);
    }
    private Map<String, Object> pageableMap(Pageable pageable){
        Map<String,Object> response = new HashMap<>();
        Page<Customer> customers = customerRepository.findAll(pageable);
        response.put("Total Elements",customers.getTotalElements());
        response.put("Total Pages",customers.getTotalPages());
        response.put("Current Page",customers.getNumber()+1);
        response.put("Categories",customers.getContent().stream()
                .map(category -> this.modelMapperService.forResponse()
                        .map(category, CustomerListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }


}
