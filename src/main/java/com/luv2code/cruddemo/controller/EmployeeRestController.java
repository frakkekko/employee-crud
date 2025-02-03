package com.luv2code.cruddemo.controller;

import com.luv2code.cruddemo.exception.custom.EmployeeNotFoundException;
import com.luv2code.cruddemo.model.Employee;
import com.luv2code.cruddemo.DAO.impl.EmployeeDAOImpl;
import com.luv2code.cruddemo.model.response.EmployeeSuccessResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeDAOImpl employeeDAOImpl;

    @Autowired
    public EmployeeRestController(EmployeeDAOImpl employeeDAOImpl){
        this.employeeDAOImpl = employeeDAOImpl;
    }

    @GetMapping("/employee")
    public HttpEntity<EmployeeSuccessResponse> getEmployees() {
        List<Employee> employeeList = employeeDAOImpl.getAllEmployees();
        EmployeeSuccessResponse<List<Employee>> response = new EmployeeSuccessResponse<>(HttpStatus.OK.value(), HttpMethod.GET, employeeList)
;        return new HttpEntity<>(response);
    }

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeeSuccessResponse> postEmployee(@Valid @RequestBody Employee employee) {
        employeeDAOImpl.add(employee);
        EmployeeSuccessResponse<Employee> response = new EmployeeSuccessResponse<>(HttpStatus.CREATED.value(), HttpMethod.POST, employee);
        return new HttpEntity<>(response);
    }

    @GetMapping("/employee/{employeeId}")
    public HttpEntity<EmployeeSuccessResponse> getEmployees(@PathVariable int employeeId) {
        Employee employeeFound = employeeDAOImpl.getEmployee(employeeId);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found");
        }

        EmployeeSuccessResponse<Employee> response = new EmployeeSuccessResponse<>(HttpStatus.OK.value(), HttpMethod.GET, employeeFound);

        return new HttpEntity<>(response);
    }

    @PutMapping("/employee/{employeeId}")
    public HttpEntity<EmployeeSuccessResponse> updateEmployee(@PathVariable long employeeId, @Valid @RequestBody Employee employee){
        Employee employeeFound = employeeDAOImpl.getEmployee(employeeId);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found");
        }

        employeeDAOImpl.update(employeeId, employee);
        Employee employeeUpdated = employeeDAOImpl.getEmployee(employeeId);

        EmployeeSuccessResponse<Employee> response = new EmployeeSuccessResponse<>(HttpStatus.OK.value(), HttpMethod.PUT, employeeUpdated);

        return new HttpEntity<>(response);
    }

    @DeleteMapping("/employee/{employeeId}")
    public HttpEntity<EmployeeSuccessResponse> deleteEmployeeById(@PathVariable int employeeId){
        Employee employeeFound = employeeDAOImpl.getEmployee(employeeId);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found");
        }

        employeeDAOImpl.delete(employeeId);
        EmployeeSuccessResponse<Employee> response = new EmployeeSuccessResponse<>(HttpStatus.OK.value(), HttpMethod.DELETE, employeeFound);
        return new HttpEntity<>(response);
    }
}
