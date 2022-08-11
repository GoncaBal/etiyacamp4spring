package com.etiya.northwind.business.requests.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

    private int categoryId;
    @NotBlank
    @NotNull
    @Size(min = 2, max =20)
    private String categoryName;
    @Size(min = 2, max = 50)
    private String description;
}
