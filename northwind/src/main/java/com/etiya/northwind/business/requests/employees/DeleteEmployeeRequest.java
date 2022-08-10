package com.etiya.northwind.business.requests.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEmployeeRequest {
    private int employeeId;
}
