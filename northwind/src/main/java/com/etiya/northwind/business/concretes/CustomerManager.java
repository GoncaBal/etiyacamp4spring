package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.responses.CustomerListResponse;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerListResponse> getAll() {
        List<Customer> result=this.customerRepository.findAll();
        List<CustomerListResponse> response=new ArrayList<>();
        for (Customer customer: result){
            CustomerListResponse customerListResponse=new CustomerListResponse();
            customerListResponse.setCompanyName(customer.getCompanyName());
            customerListResponse.setContactName(customer.getContactName());
            customerListResponse.setContactTitle(customer.getContactTitle());

            response.add(customerListResponse);
        }
        return response;
    }
}
