package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.categories.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categories.DeleteCategoryRequest;
import com.etiya.northwind.business.requests.categories.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Result add(CreateCategoryRequest createCategoryRequest) ;
    Result update(UpdateCategoryRequest updateCategoryRequest);
    Result delete(DeleteCategoryRequest deleteCategoryRequest);
    DataResult<List<CategoryListResponse>> getAll();
    DataResult<ReadCategoryResponse> getById(int id);
    DataResult<Map<String,Object>> getAllPages(int pageNumber,int pageSize);
    DataResult<Map<String,Object>> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
