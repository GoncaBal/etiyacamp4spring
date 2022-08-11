package com.etiya.northwind.business.requests.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {

    private int orderId;

    private String customerId;

    private int employeeId;
    @PastOrPresent
    private LocalDate orderDate;
}
