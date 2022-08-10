package com.etiya.northwind.business.responses.orderDetails;

import com.etiya.northwind.entities.concretes.OrderDetailId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadOrderDetailResponse {
    private int orderId;
    private int productId;
    private String productName;
    private String contactName;
    private LocalDate orderDate;
    private double unitPrice;
    private int quantity;
    private double discount;
}
