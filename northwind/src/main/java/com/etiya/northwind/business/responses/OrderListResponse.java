package com.etiya.northwind.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {
    private int orderId;
    private LocalDate orderDate;
    private String employeeFirstName;
    private String employeeLastName;
    private String customerCompanyName;
    private String customerContactName;

}
