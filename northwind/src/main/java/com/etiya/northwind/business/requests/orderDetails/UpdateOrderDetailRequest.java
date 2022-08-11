package com.etiya.northwind.business.requests.orderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDetailRequest {

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
