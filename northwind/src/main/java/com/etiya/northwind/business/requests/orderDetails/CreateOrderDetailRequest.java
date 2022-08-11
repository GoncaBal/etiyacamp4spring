package com.etiya.northwind.business.requests.orderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailRequest {

    private int orderId;

    private int productId;
    @NotNull
    @Positive
    private double unitPrice;
    @NotNull
    @PositiveOrZero
    private int quantity;
    @NotNull
    @Positive
    private double discount;
}
