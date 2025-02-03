package com.luv2code.cruddemo.controller;

import com.luv2code.cruddemo.exception.custom.EmployeeNotFoundException;
import com.luv2code.cruddemo.model.Employee;
import com.luv2code.cruddemo.DAO.impl.EmployeeDAOImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Employee> getEmployees() {
        return employeeDAOImpl.getAllEmployees();
    }

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void postEmployee(@Valid @RequestBody Employee employee) {
        employeeDAOImpl.add(employee);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployees(@PathVariable int employeeId) {
        Employee employeeFound = employeeDAOImpl.getEmployee(employeeId);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found");
        }

        return employeeFound;
    }

    @PutMapping("/employee/{employeeId}")
    public void updateEmployee(@PathVariable long employeeId, @Valid @RequestBody Employee employee){
        Employee employeeFound = employeeDAOImpl.getEmployee(employeeId);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found");
        }
        
        employeeDAOImpl.update(employeeId, employee);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteEmployeeById(@PathVariable int employeeId){
        employeeDAOImpl.delete(employeeId);
    }
}
