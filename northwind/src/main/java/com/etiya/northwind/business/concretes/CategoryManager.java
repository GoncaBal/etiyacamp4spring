package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categories.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categories.DeleteCategoryRequest;
import com.etiya.northwind.business.requests.categories.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.categories.ReadCategoryResponse;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.CategoryRepository;
import com.etiya.northwind.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public CategoryManager(CategoryRepository categoryRepository,ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public void add(CreateCategoryRequest createCategoryRequest) {
        Category category=this.modelMapperService.forRequest()
                .map(createCategoryRequest,Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public void update(UpdateCategoryRequest updateCategoryRequest) {
        Category categoryToUpdate=this.modelMapperService.forRequest()
                .map(updateCategoryRequest,Category.class);
        this.categoryRepository.save(categoryToUpdate);

    }

    @Override
    public void delete(DeleteCategoryRequest deleteCategoryRequest) {
            this.categoryRepository.deleteById(deleteCategoryRequest.getId());
    }

    @Override
    public List<CategoryListResponse> getAll() {
        List<Category> result= this.categoryRepository.findAll();
        List<CategoryListResponse> response= result.stream().map(category -> this.modelMapperService.forResponse()
                .map(category,CategoryListResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public ReadCategoryResponse getById(int id) {
        Category category=this.categoryRepository.findById(id);
        return this.modelMapperService.forResponse().map(category,ReadCategoryResponse.class);
    }

    @Override
    public Map<String, Object> getAllPages(int pageNumber, int pageSize) {
        Pageable pageable= PageRequest.of(pageNumber-1,pageSize);
       return  pageableMap(pageable);
    }

    @Override
    public Map<String, Object> getAllPagesOrderByEntity(int pageNumber, int pageSize, String entity, String type) {
        Pageable pageable=PageRequest.of(pageNumber-1,pageSize,sortType(entity,type));
        return  pageableMap(pageable);
    }
    private Map<String, Object> pageableMap(Pageable pageable){
        Map<String,Object> response = new HashMap<>();
        Page<Category> categories = categoryRepository.findAll(pageable);
        response.put("Total Elements",categories.getTotalElements());
        response.put("Total Pages",categories.getTotalPages());
        response.put("Current Page",categories.getNumber()+1);
        response.put("Categories",categories.getContent().stream()
                .map(category -> this.modelMapperService.forResponse()
                        .map(category,CategoryListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }

    private Sort sortType(String property, String type){
        if (type.equals("desc"))
            return Sort.by(property).descending();
        else return Sort.by(property).ascending();
    }
}
