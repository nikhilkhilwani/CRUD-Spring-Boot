package com.example.CRUD_API.dao;

import com.example.CRUD_API.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();
    Employee findById(int empId);
    Employee save(Employee employee);
    Employee deleteById(int empId);
}
