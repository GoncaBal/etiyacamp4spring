package com.etiya.northwind.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailListResponse {
    private int orderId;
    private int productId;
    private String productName;
    private LocalDate orderDate;
    private double unitPrice;
    private int quantity;
    private double discount;

}
