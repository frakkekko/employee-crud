package com.luv2code.cruddemo.controller;

import com.luv2code.cruddemo.model.Employee;
import com.luv2code.cruddemo.model.response.EmployeeSuccessResponse;
import com.luv2code.cruddemo.service.EmployeeService;
import com.luv2code.cruddemo.util.EmployeeResponseWrapper;
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
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public HttpEntity<EmployeeSuccessResponse<List<Employee>>> getEmployees() {
        List<Employee> employeeList = employeeService.getAll();
;        return EmployeeResponseWrapper.buildResponse(HttpStatus.OK.value(), HttpMethod.GET, employeeList);
    }

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EmployeeSuccessResponse<Employee>> postEmployee(@Valid @RequestBody Employee employee) {
        employeeService.add(employee);
        return EmployeeResponseWrapper.buildResponse(HttpStatus.CREATED.value(), HttpMethod.POST, employee);
    }

    @GetMapping("/employee/{employeeId}")
    public HttpEntity<EmployeeSuccessResponse<Employee>> getEmployee(@PathVariable int employeeId) {
        Employee employeeFound = employeeService.getById(employeeId);
        return EmployeeResponseWrapper.buildResponse(HttpStatus.OK.value(), HttpMethod.GET, employeeFound);
    }

    @PutMapping("/employee/{employeeId}")
    public HttpEntity<EmployeeSuccessResponse<Employee>> updateEmployee(@PathVariable long employeeId, @Valid @RequestBody Employee employee){
        Employee employeeUpdated = employeeService.update(employeeId, employee);
        return EmployeeResponseWrapper.buildResponse(HttpStatus.OK.value(), HttpMethod.PUT, employeeUpdated);
    }

    @DeleteMapping("/employee/{employeeId}")
    public HttpEntity<EmployeeSuccessResponse<Employee>> deleteEmployeeById(@PathVariable int employeeId){
        Employee employeeDeleted = employeeService.delete(employeeId);
        return EmployeeResponseWrapper.buildResponse(HttpStatus.OK.value(), HttpMethod.DELETE, employeeDeleted);
    }
}
