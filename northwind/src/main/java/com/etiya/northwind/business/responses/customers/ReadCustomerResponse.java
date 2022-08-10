package com.etiya.northwind.business.responses.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadCustomerResponse {
    private String companyName;
    private String contactName;
    private String contactTitle;
}
