package com.example.CRUD_API.dao;

import com.example.CRUD_API.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll()
    {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int empId)
    {
        return entityManager.find(Employee.class, empId);
    }

    @Override
    public Employee save(Employee employee)
    {
        //Get next available ID
        Object result = entityManager.createNativeQuery("SELECT IFNULL(MAX(id), 0) + 1 FROM Employee").getSingleResult();
        int nextId = ((Number) result).intValue();

        //Set AUTO_INCREMENT to next ID
        entityManager.createNativeQuery("ALTER TABLE Employee AUTO_INCREMENT = " + nextId).executeUpdate();

        return entityManager.merge(employee);
    }

    @Override
    public Employee deleteById(int empId)
    {
        Employee employee = entityManager.find(Employee.class, empId);
        entityManager.remove(employee);
        return employee;
    }

}
