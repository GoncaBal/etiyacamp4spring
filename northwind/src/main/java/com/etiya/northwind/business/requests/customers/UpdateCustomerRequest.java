package com.etiya.northwind.business.requests.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    private String customerId;
    private String companyName;
    private String contactName;
    private String contactTitle;
}
