package com.etiya.northwind.business.requests.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierRequest {

    private int supplierId;
    @NotNull
    @NotBlank
    @Size(min=2,max=30)
    private String companyName;
    @NotNull
    @NotBlank
    @Size(min=2,max=20)
    private String contactName;
    @NotNull
    @NotBlank
    @Size(min=2,max=20)
    private String contactTitle;
}
