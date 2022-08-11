package com.etiya.northwind.api.controllers;


import com.etiya.northwind.business.abstracts.SupplierService;

import com.etiya.northwind.business.requests.suppliers.CreateSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.DeleteSupplierRequest;
import com.etiya.northwind.business.requests.suppliers.UpdateSupplierRequest;

import com.etiya.northwind.business.responses.suppliers.ReadSupplierResponse;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SuppliersController {
    private SupplierService supplierService;

    @Autowired
    public SuppliersController(SupplierService supplierService) {

        this.supplierService = supplierService;
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateSupplierRequest createSupplierRequest){
       return this.supplierService.add(createSupplierRequest);

    }

    @PutMapping("/update")
    public Result update(@Valid @RequestBody UpdateSupplierRequest updateSupplierRequest){
        return this.supplierService.update(updateSupplierRequest);

    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteSupplierRequest deleteSupplierRequest){
       return this.supplierService.delete(deleteSupplierRequest);
    }


    @GetMapping("/getAll")
    public DataResult<List<SupplierListResponse>> getAll() {

        return this.supplierService.getAll();
    }
    @GetMapping("/getbyid")
    public DataResult<ReadSupplierResponse> getById(@RequestParam int id){

        return this.supplierService.getById(id);
    }

    @GetMapping("/getallpages")
    public DataResult<Map<String,Object>> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize){

        return this.supplierService.getAllPages(pageNumber,pageSize);

    }

    @GetMapping("/getallpagesorderbyentity")
    public DataResult<Map<String,Object>> getAllPagesOrderByEntity(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String entity,@RequestParam Optional<String> type){

        return this.supplierService.getAllPagesOrderByEntity(pageNumber,pageSize, entity,type.orElse(""));

    }

}
