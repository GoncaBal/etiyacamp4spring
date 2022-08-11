package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.requests.employees.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employees.DeleteEmployeeRequest;
import com.etiya.northwind.business.requests.employees.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import com.etiya.northwind.business.responses.employees.ReadEmployeeResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.utilities.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.core.utilities.results.SuccessDataResult;
import com.etiya.northwind.core.utilities.results.SuccessResult;
import com.etiya.northwind.core.utilities.sortData.SortingEntities;
import com.etiya.northwind.dataAccess.abstracts.EmployeeRepository;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Employee;
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
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository, ModelMapperService modelMapperService) {

        this.employeeRepository = employeeRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateEmployeeRequest createEmployeeRequest) {

        checkIfReportsToExceed(createEmployeeRequest.getReportsTo());

        Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
        this.employeeRepository.save(employee);

        return new SuccessResult("EMPLOYEE.ADDED");
    }
    @Override
    public Result update(UpdateEmployeeRequest updateEmployeeRequest) {

        checkIfEmployeeIdExist(updateEmployeeRequest.getEmployeeId());
        checkIfReportsToExceed(updateEmployeeRequest.getReportsTo());

        Employee employeeToUpdate = this.modelMapperService.forRequest().map(updateEmployeeRequest, Employee.class);
        this.employeeRepository.save(employeeToUpdate);

        return new SuccessResult("EMPLOYEE.UPDATED");

    }

    @Override
    public Result delete(DeleteEmployeeRequest deleteEmployeeRequest) {

        checkIfReportsToExceed(deleteEmployeeRequest.getEmployeeId());

        this.employeeRepository.deleteById(deleteEmployeeRequest.getEmployeeId());

        return new SuccessResult("EMPLOYEE.DELETED");

    }

    @Override
    public DataResult<List<EmployeeListResponse>> getAll() {

        List<Employee> result = this.employeeRepository.findAll();

        List<EmployeeListResponse> response = result.stream().map(employee -> this.modelMapperService.forResponse()
                .map(employee, EmployeeListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<ReadEmployeeResponse> getById(int id) {

        checkIfReportsToExceed(id);

        Employee employee = this.employeeRepository.findById(id).get();
        ReadEmployeeResponse readEmployeeResponse = this.modelMapperService.forResponse()
                .map(employee, ReadEmployeeResponse.class);

        return new SuccessDataResult<>(readEmployeeResponse);
    }

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
        Page<Employee> employees = employeeRepository.findAll(pageable);

        response.put("Total Elements", employees.getTotalElements());
        response.put("Total Pages", employees.getTotalPages());
        response.put("Current Page", employees.getNumber() + 1);
        response.put("Employees", employees.getContent().stream()
                .map(employee -> this.modelMapperService.forResponse()
                        .map(employee, EmployeeListResponse.class))
                .collect(Collectors.toList()));

        return response;
    }

    private void checkIfReportsToExceed(int reportsTo){

        List<Employee> employees= this.employeeRepository.findByReportsTo(reportsTo);

        if (employees.size()>9){
            throw new BusinessException("EMPLOYEE.REPORTS.EXCEED");
        }
    }

    private void checkIfEmployeeIdExist(int employeeId){

        Employee currentEmployee= this.employeeRepository.findById(employeeId).get();

        if (currentEmployee==null){
            throw new BusinessException("INVALID.EMPLOYEE.ID");
        }
    }
}
