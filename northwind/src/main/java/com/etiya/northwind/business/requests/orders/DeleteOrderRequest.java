package com.etiya.northwind.business.requests.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderRequest {
    private int orderId;
}
