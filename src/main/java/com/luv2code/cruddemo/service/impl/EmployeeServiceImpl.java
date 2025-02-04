package com.luv2code.cruddemo.service.impl;

import com.luv2code.cruddemo.DAO.EmployeeRepository;
import com.luv2code.cruddemo.exception.custom.EmployeeNotFoundException;
import com.luv2code.cruddemo.model.Employee;
import com.luv2code.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(long id) {
        Optional<Employee> employeeFound = employeeRepository.findById(id);

        if(!employeeFound.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }
        return employeeFound.get();
    }

    @Override
    public void add(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee update(long id, Employee employee) {
        Optional<Employee> employeeFound = employeeRepository.findById(id);

        if(!employeeFound.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }

        employee.setId(employeeFound.get().getId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(long id) {
        Optional<Employee> employeeFound = employeeRepository.findById(id);

        if(!employeeFound.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }

        employeeRepository.delete(employeeFound.get());

        return employeeFound.get();
    }
}
