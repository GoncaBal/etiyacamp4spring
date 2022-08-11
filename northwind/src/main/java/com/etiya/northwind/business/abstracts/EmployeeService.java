package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.employees.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employees.DeleteEmployeeRequest;
import com.etiya.northwind.business.requests.employees.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import com.etiya.northwind.business.responses.employees.ReadEmployeeResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Result add(CreateEmployeeRequest createEmployeeRequest);
    Result update(UpdateEmployeeRequest updateEmployeeRequest);
    Result delete(DeleteEmployeeRequest deleteEmployeeRequest);
    DataResult<List<EmployeeListResponse>> getAll();
    DataResult<ReadEmployeeResponse> getById(int id);
    DataResult<Map<String,Object>> getAllPages(int pageNumber, int pageSize);
    DataResult<Map<String,Object>> getAllPagesOrderByEntity(int pageNumber,int pageSize,String entity,String type);
}
