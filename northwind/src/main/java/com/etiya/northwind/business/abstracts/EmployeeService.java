package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.employees.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employees.DeleteEmployeeRequest;
import com.etiya.northwind.business.requests.employees.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import com.etiya.northwind.business.responses.employees.ReadEmployeeResponse;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void add(CreateEmployeeRequest createEmployeeRequest);
    void update(UpdateEmployeeRequest updateEmployeeRequest);
    void delete(DeleteEmployeeRequest deleteEmployeeRequest);
    List<EmployeeListResponse> getAll();
    ReadEmployeeResponse getById(int id);
    Map<String,Object> getAllPages(int pageNumber, int pageSize);
    Map<String,Object> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
