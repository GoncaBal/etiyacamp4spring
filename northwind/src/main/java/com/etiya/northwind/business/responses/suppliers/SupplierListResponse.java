package com.etiya.northwind.business.responses.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierListResponse {
    private int supplierId;
    private String companyName;
    private String contactName;
    private String contactTitle;
}
