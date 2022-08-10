package com.etiya.northwind.business.requests.products;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UpdateProductRequest {
    @NotBlank
    @NotNull
    @Size(min=1,max=10)
    private String productName;
    @Positive
    private double unitPrice;
    @Positive
    private int unitsInStock;
    private int categoryId;
    @NotBlank
    @NotNull
    private int discontinued;
}
