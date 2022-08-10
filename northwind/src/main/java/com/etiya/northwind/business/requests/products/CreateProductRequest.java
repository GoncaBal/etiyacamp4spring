package com.etiya.northwind.business.requests.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
   @NotBlank
   @NotNull
   @Size(min=1,max=10)
    private String productName;
   @Positive
    private double unitPrice;
   @Positive
    private int unitsInStock;
    private int categoryId;
    @NotNull
    @NotBlank
    private int discontinued;
}
