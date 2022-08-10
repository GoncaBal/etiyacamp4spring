package com.etiya.northwind.business.responses.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadEmployeeResponse {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String title;
}
