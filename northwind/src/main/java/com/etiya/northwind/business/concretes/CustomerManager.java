package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.requests.customers.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customers.DeleteCustomerRequest;
import com.etiya.northwind.business.requests.customers.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.business.responses.customers.ReadCustomerResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
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
    public CustomerManager(CustomerRepository customerRepository, ModelMapperService modelMapperService) {
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCustomerRequest createCustomerRequest) {

        Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        this.customerRepository.save(customer);

        return new SuccessResult("CUSTOMER.ADDED");
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {

        checkIfCustomerIdExist(updateCustomerRequest.getCustomerId());

        Customer customerToUpdate = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
        this.customerRepository.save(customerToUpdate);

        return new SuccessResult("CUSTOMER.UPDATED");
    }

    @Override
    public Result delete(DeleteCustomerRequest deleteCustomerRequest) {

        checkIfCustomerIdExist(deleteCustomerRequest.getCustomerId());

        this.customerRepository.deleteById(deleteCustomerRequest.getCustomerId());

        return new SuccessResult("CUSTOMER.DELETED");
    }

    @Override
    public DataResult<ReadCustomerResponse> getById(String customerId) {

        checkIfCustomerIdExist(customerId);

        Customer customer = this.customerRepository.findById(customerId).get();

        ReadCustomerResponse readCustomerResponse = this.modelMapperService.forResponse()
                .map(customer, ReadCustomerResponse.class);

        return new SuccessDataResult<>(readCustomerResponse);
    }

    @Override
    public DataResult<List<CustomerListResponse>> getAll() {

        List<Customer> result = this.customerRepository.findAll();

        List<CustomerListResponse> response = result.stream().map(customer -> this.modelMapperService.forResponse()
                .map(customer, CustomerListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<Map<String, Object>> getAllPages(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return new SuccessDataResult<>(pageableMap(pageable));
    }

    @Override
    public DataResult<Map<String, Object>> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, SortingEntities.sortType(entity, type));

        return new SuccessDataResult<>(pageableMap(pageable));
    }

    @Override
    public Customer getCustomerById(String customerId) {
        checkIfCustomerIdExist(customerId);
        return this.customerRepository.findById(customerId).get();
    }

    private Map<String, Object> pageableMap(Pageable pageable) {

        Map<String, Object> response = new HashMap<>();
        Page<Customer> customers = customerRepository.findAll(pageable);

        response.put("Total Elements", customers.getTotalElements());
        response.put("Total Pages", customers.getTotalPages());
        response.put("Current Page", customers.getNumber() + 1);

        response.put("Customers", customers.getContent().stream()
                .map(category -> this.modelMapperService.forResponse()
                        .map(category, CustomerListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }

    private void checkIfCustomerIdExist(String customerId){

        Customer currentCustomer= this.customerRepository.findById(customerId).get();

        if (currentCustomer==null){
            throw new BusinessException("INVALID.CUSTOMER.ID");
        }
    }
}
