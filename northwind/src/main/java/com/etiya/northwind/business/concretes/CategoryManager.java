package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categories.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categories.DeleteCategoryRequest;
import com.etiya.northwind.business.requests.categories.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.core.utilities.sortData.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.CategoryRepository;
import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCategoryRequest createCategoryRequest) {

        checkIfCategoryExistByName(createCategoryRequest.getCategoryName());

        Category category = this.modelMapperService.forRequest().map(createCategoryRequest, Category.class);
        categoryRepository.save(category);

        return new SuccessResult("CATEGORY.ADDED");
    }

    @Override
    public Result update(UpdateCategoryRequest updateCategoryRequest) {

        checkIfCategoryIdExist(updateCategoryRequest.getCategoryId());
        checkIfCategoryExistByName(updateCategoryRequest.getCategoryName());

        Category category = this.modelMapperService.forRequest().map(updateCategoryRequest, Category.class);
        categoryRepository.save(category);

        return new SuccessResult("CATEGORY.UPDATED");
    }

    @Override
    public Result delete(DeleteCategoryRequest deleteCategoryRequest) {

        checkIfCategoryIdExist(deleteCategoryRequest.getId());

        categoryRepository.deleteById(deleteCategoryRequest.getId());

        return new SuccessResult("CATEGORY.DELETED");
    }

    @Override
    public DataResult<ReadCategoryResponse> getById(int id) {

        checkIfCategoryIdExist(id);

        Category category = this.categoryRepository.findById(id).get();

        ReadCategoryResponse readCategoryResponse = this.modelMapperService.forResponse()
                .map(category, ReadCategoryResponse.class);

        return new SuccessDataResult<>(readCategoryResponse);
    }

    @Override
    public DataResult<List<CategoryListResponse>> getAll() {

        List<Category> result = this.categoryRepository.findAll();

        List<CategoryListResponse> responses = result.stream().map(category -> this.modelMapperService.forResponse()
                .map(category, CategoryListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(responses);
    }


    @Override
    public DataResult<Map<String, Object>> getAllPages(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return new SuccessDataResult<>(pageableMap(pageable));
    }

    @Override
    public DataResult<Map<String, Object>> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, SortingEntities.sortType(entity, type));

        return new SuccessDataResult<>(pageableMap(pageable));
    }

    private Map<String, Object> pageableMap(Pageable pageable) {
        Map<String, Object> response = new HashMap<>();
        Page<Category> categories = categoryRepository.findAll(pageable);
        response.put("Total Elements", categories.getTotalElements());
        response.put("Total Pages", categories.getTotalPages());
        response.put("Current Page", categories.getNumber() + 1);
        response.put("Categories", categories.getContent().stream()
                .map(category -> this.modelMapperService.forResponse()
                        .map(category, CategoryListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }

    private void checkIfCategoryExistByName(String categoryName){
        Category currentCategory=this.categoryRepository.findByCategoryName(categoryName);
        if (currentCategory!=null){
            throw new BusinessException("CATEGORY.NAME.ALREADY.EXIST");
        }
    }

    private void checkIfCategoryIdExist(int categoryId){
        Category currentCategory= this.categoryRepository.findById(categoryId).get();
        if (currentCategory==null){
        throw new BusinessException("INVALID.CATEGORY.ID");
        }
    }

}