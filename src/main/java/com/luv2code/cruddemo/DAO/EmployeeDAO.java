package com.luv2code.cruddemo.DAO;

import com.luv2code.cruddemo.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeDAO {
    void add(Employee employee);
    void delete(long employeeId);
    Employee update(long employeeId, Employee employee);
    Employee getEmployee(long employeeId);
    List<Employee> getAllEmployees();
}
