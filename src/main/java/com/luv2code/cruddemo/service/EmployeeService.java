package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(long id);
    void add(Employee employee);
    Employee update(long id, Employee employee);
    Employee delete(long id);

}
