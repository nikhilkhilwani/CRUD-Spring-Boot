package com.example.CRUD_API.service;

import com.example.CRUD_API.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(int empId);
    Employee save(Employee employee);
    Employee deleteById(int empId);
}
