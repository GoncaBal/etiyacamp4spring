package com.etiya.northwind.business.requests.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {

    private int employeeId;
    @NotNull
    @NotBlank
    @Size(min=2,max = 20)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(min=2,max = 20)
    private String lastName;
    @Size(min=2,max = 20)
    private String title;
    private int reportsTo;
}