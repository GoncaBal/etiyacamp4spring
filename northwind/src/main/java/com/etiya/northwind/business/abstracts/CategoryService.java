package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.CategoryListResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryListResponse> getAll();
}
