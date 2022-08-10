package com.etiya.northwind.business.responses.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadOrderResponse {
    private int orderId;
    private LocalDate orderDate;
    private String fullName;
    private String customerCompanyName;
    private String customerContactName;
}
