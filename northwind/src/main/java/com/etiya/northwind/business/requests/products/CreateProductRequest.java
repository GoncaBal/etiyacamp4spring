package com.etiya.northwind.business.requests.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private int productId;
    @NotBlank
    @NotNull
    @Size(min = 1, max = 10)
    private String productName;
    @Positive
    private double unitPrice;
    @Positive
    private int unitsInStock;
    private int categoryId;
    private int supplierId;
    @PositiveOrZero
    private int discontinued;
}
