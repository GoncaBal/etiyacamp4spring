package com.etiya.northwind.api.controllers;
import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import com.etiya.northwind.business.responses.employees.ReadEmployeeResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.requests.employees.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employees.DeleteEmployeeRequest;
import com.etiya.northwind.business.requests.employees.UpdateEmployeeRequest;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
     return   this.employeeService.add(createEmployeeRequest);

    }

    @PutMapping("/update")
    public Result update(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest){
       return this.employeeService.update(updateEmployeeRequest);

    }
    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteEmployeeRequest deleteEmployeeRequest){
        return this.employeeService.delete(deleteEmployeeRequest);
    }


    @GetMapping("/getAll")
    public DataResult<List<EmployeeListResponse>> getAll() {

        return this.employeeService.getAll();
    }
    @GetMapping("/getbyid")
    public DataResult<ReadEmployeeResponse> getById(@RequestParam int id){

        return this.employeeService.getById(id);
    }

    @GetMapping("/getallpages")
    public DataResult<Map<String,Object>> getAllPages(@RequestParam int pageNumber, @RequestParam int pageSize){

        return this.employeeService.getAllPages(pageNumber,pageSize);

    }

    @GetMapping("/getallpagesorderbyentity")
    public DataResult<Map<String,Object>> getAllPagesOrderByEntity(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String entity,@RequestParam Optional<String> type){

        return this.employeeService.getAllPagesOrderByEntity(pageNumber,pageSize, entity,type.orElse(""));

    }



}
