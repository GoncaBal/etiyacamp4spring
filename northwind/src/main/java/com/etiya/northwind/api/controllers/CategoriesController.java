package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categories.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categories.DeleteCategoryRequest;
import com.etiya.northwind.business.requests.categories.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public DataResult<List<CategoryListResponse>> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<ReadCategoryResponse> getById(@RequestParam int id) {
        return this.categoryService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateCategoryRequest createCategoryRequest)  {
       return this.categoryService.add(createCategoryRequest);
    }

    @PutMapping ("/update")
    public Result update(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return this.categoryService.update(updateCategoryRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCategoryRequest deleteCategoryRequest) {
        return this.categoryService.delete(deleteCategoryRequest);
    }

    @GetMapping("/getallpages")
    public DataResult<Map<String, Object>> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return this.categoryService.getAllPages(pageNumber, pageSize);
    }

    @GetMapping("/getallpagesortbyentity")
    public DataResult<Map<String,Object>> getAllPagesOrderByEntity(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String entity, @RequestParam Optional<String> type){
        return this.categoryService.getAllPagesOrderByEntity(pageNumber,pageSize,entity,type.orElse(" "));
    }
}

