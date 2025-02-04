package com.luv2code.cruddemo.DAO;

import com.luv2code.cruddemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
