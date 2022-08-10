package com.etiya.northwind.business.requests.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
private int categoryId;
private String categoryName;
private String description;
}
