package com.luv2code.cruddemo.service.impl;

import com.luv2code.cruddemo.DAO.EmployeeDAO;
import com.luv2code.cruddemo.exception.custom.EmployeeNotFoundException;
import com.luv2code.cruddemo.model.Employee;
import com.luv2code.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getById(long id) {
        Employee employeeFound = employeeDAO.getEmployee(id);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        return employeeFound;
    }

    @Override
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Override
    public Employee update(long id, Employee employee) {
        Employee employeeFound = employeeDAO.getEmployee(id);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }

        return employeeDAO.update(id, employee);
    }

    @Override
    public Employee delete(long id) {
        Employee employeeFound = employeeDAO.getEmployee(id);

        if(employeeFound == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }

        employeeDAO.delete(id);

        return employeeFound;
    }
}
