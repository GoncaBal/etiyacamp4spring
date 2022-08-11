package com.etiya.northwind.business.requests.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {

    private String customerId;
    @NotBlank
    @NotNull
    @Size(min = 2, max = 30)
    private String companyName;
    @NotBlank
    @NotNull
    @Size(min = 2, max = 25)
    private String contactName;
    @NotBlank
    @NotNull
    @Size(min = 2, max = 25)
    private String contactTitle;
}
