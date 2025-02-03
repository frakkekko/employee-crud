package com.luv2code.cruddemo.DAO.impl;

import com.luv2code.cruddemo.model.Employee;
import com.luv2code.cruddemo.DAO.EmployeeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EmployeeDAOImpl implements EmployeeDAO {
    EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    @Transactional
    public void delete(long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        entityManager.remove(employee);
    }

    @Override
    @Transactional
    public void update(long employeeId, Employee employee) {
        Employee exsistingEmployee = entityManager.find(Employee.class, employeeId);

        exsistingEmployee.setFirstName(employee.getFirstName());
        exsistingEmployee.setLastName(employee.getLastName());
        exsistingEmployee.setEmail(employee.getEmail());

        entityManager.merge(exsistingEmployee);
    }

    @Override
    public Employee getEmployee(long employeeId) {
        return entityManager.find(Employee.class, employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        Query query = entityManager.createQuery("FROM Employee");
        return query.getResultList();
    }
}
