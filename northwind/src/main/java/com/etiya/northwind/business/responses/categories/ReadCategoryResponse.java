package com.etiya.northwind.business.responses.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadCategoryResponse {
    private int categoryId;
    private String categoryName;
    private String description;
}
