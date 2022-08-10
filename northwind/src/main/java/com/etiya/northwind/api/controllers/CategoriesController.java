package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categories.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categories.DeleteCategoryRequest;
import com.etiya.northwind.business.requests.categories.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<CategoryListResponse> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/getbyid")
    public ReadCategoryResponse getById(@RequestParam int id) {
        return this.categoryService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCategoryRequest createCategoryRequest) {
        this.categoryService.add(createCategoryRequest);
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        this.categoryService.update(updateCategoryRequest);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody DeleteCategoryRequest deleteCategoryRequest) {
        this.categoryService.delete(deleteCategoryRequest);
    }

    @GetMapping("/getallpages")
    public Map<String, Object> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return this.categoryService.getAllPages(pageNumber, pageSize);
    }

    @GetMapping("/getallpagesortbyentity")
    public Map<String,Object> getAllPagesOrderByEntity(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String entity, @RequestParam Optional<String> type){
        return this.categoryService.getAllPagesOrderByEntity(pageNumber,pageSize,entity,type.orElse(" "));
    }
}

