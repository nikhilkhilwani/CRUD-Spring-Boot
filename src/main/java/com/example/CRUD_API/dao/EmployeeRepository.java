package com.example.CRUD_API.dao;

import com.example.CRUD_API.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> { }
