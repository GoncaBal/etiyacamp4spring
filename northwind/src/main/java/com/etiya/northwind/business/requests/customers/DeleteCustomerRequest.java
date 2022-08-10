package com.etiya.northwind.business.requests.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCustomerRequest {
    private String customerId;
}
