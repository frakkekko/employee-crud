package com.luv2code.cruddemo.controller;

import com.luv2code.cruddemo.model.Employee;
import com.luv2code.cruddemo.model.response.EmployeeSuccessResponse;
import com.luv2code.cruddemo.service.EmployeeService;
import com.luv2code.cruddemo.util.EmployeeResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    public ResponseEntity<EmployeeSuccessResponse<List<Employee>>> getEmployees() {
        List<Employee> employeeList = employeeService.getAll();
;        return EmployeeResponseWrapper.buildSuccessResponse(HttpStatus.OK, HttpMethod.GET, employeeList);
    }

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeSuccessResponse<Employee>> postEmployee(@Valid @RequestBody Employee employee) {
        employeeService.add(employee);
        return EmployeeResponseWrapper.buildSuccessResponse(HttpStatus.CREATED, HttpMethod.POST, employee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeSuccessResponse<Employee>> getEmployee(@PathVariable int id) {
        Employee employeeFound = employeeService.getById(id);
        return EmployeeResponseWrapper.buildSuccessResponse(HttpStatus.OK, HttpMethod.GET, employeeFound);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeSuccessResponse<Employee>> updateEmployee(@PathVariable long id, @Valid @RequestBody Employee employee){
        Employee employeeUpdated = employeeService.update(id, employee);
        return EmployeeResponseWrapper.buildSuccessResponse(HttpStatus.OK, HttpMethod.PUT, employeeUpdated);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeSuccessResponse<Employee>> deleteEmployeeById(@PathVariable int id){
        Employee employeeDeleted = employeeService.delete(id);
        return EmployeeResponseWrapper.buildSuccessResponse(HttpStatus.OK, HttpMethod.DELETE, employeeDeleted);
    }
}
