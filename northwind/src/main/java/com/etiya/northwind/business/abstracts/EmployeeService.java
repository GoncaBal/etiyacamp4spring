package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.responses.EmployeeListResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListResponse> getAll();
}
